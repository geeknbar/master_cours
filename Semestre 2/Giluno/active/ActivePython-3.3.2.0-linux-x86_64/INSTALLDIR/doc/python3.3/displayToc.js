/* Copyright (c) 2004-2006 ActiveState Software Inc.

 *
 * Based on:
 * ------------------------------------------------------------------------
 * Author's Statement:
 * 
 * This script is based on ideas of the author. You may copy, modify and
 * use it for any purpose. The only condition is that if you publish web
 * pages that use this script you point to its author at a suitable place
 * and don't remove this Statement from it. It's your responsibility to
 * handle possible bugs even if you didn't modify anything. I cannot
 * promise any support.
 * 
 * Dieter Bungers
 * 
 * GMD (www.gmd.de) and infovation (www.infovation.de)
 * ------------------------------------------------------------------------
 */


//---- logging support

var _LOGGING_ENABLED = false;

function _log(msg) {
    if (!_LOGGING_ENABLED) return;
    try {
        if (typeof(window._log_initialized) == "undefined")
        {
            //XXX Should make this robust for multiple 0 or >1 body tags. And
            //    add support for namespaced HTML tags.
            var body = document.getElementsByTagName("body")[0];
            if (typeof(body) == "undefined") { // in some frames
                window._log_initialized = false;
            } else {
                var textarea = document.createElement("textarea");
                textarea.setAttribute("rows", "10");
                textarea.setAttribute("id", "_log");
                textarea.setAttribute("readonly", "true");
                //XXX This style doesn't work in IE. Great. At some point
                //    change to something other than a textbox, allow markup,
                //    and manually do the scrollbar thing, if possible.
                textarea.setAttribute("style", "width: 100%;");
                body.insertBefore(textarea, body.firstChild);
                window._log_initialized = true;
            }
        }
        if (window._log_initialized) {
            var statusArea = document.getElementById("_log");
            statusArea.value += msg + "\n";
        }
    } catch(ex) {
        alert("error in _log(): "+ex);
    }
}


//---- mainline

var mdi;
if (typeof(textSizes) != 'undefined') {
    if (navigator.appName.toLowerCase().indexOf("explorer") > -1) {
        mdi=textSizes[1], sml=textSizes[2];
    } else {
        mdi=textSizes[3], sml=textSizes[4];
    }
}

function getCurrentTargetHref(anchorName) {
    // Find the entry in tocTab that corresponds to the named anchor
    // The anchor doesn't have the file-specific bit, so we need to
    // look at the current file's href to figure out what URL
    // we're currently in, add the anchor, and then look for that.
    var currentHref;
    currentHref = self.location.href;
    if (currentHref.indexOf('#') != -1) {
        // strip current anchor
        currentHref = currentHref.slice(0, currentHref.indexOf('#'));
    }
    if (currentHref.indexOf('/') != -1) {
        // strip leading path bits
        currentHref = currentHref.slice(currentHref.lastIndexOf('/')+1,
                                        currentHref.length);
    }
    if (currentHref.indexOf('\\') != -1) {
        // strip leading path bits
        currentHref = currentHref.slice(currentHref.lastIndexOf('\\')+1,
                                        currentHref.length);
    }
    return currentHref + '#' + anchorName;
}

function getTocNodeId(targetHref) {
    var nodeId = '0';
    for (var i = 0; i < tocTab.length; i++) {
        if (tocTab[i][2] == targetHref) {
            nodeId = tocTab[i][0];
            break
        }
    }
    _log("getTocNodeId(targetHref='"+targetHref+"'): nodeId='"+nodeId+"'");
    return nodeId;
}


// Split the given URL at the root of this document set.
//
//  "path" is the full current URL.
//  "docPage" is the relative doc page URL.
//  "depth" is the directory depth of the docPage in the document set.
//
// Returns an object with "dirname" and "docPage" attributes. The "docPage"
// is escaped for inclusion in a URL query fragment.
function docPageFromPath(path, docPage, depth) {
    _log("docPageFromPath(path='"+path+"', docPage='"+docPage+
         "', depth="+depth+"):");
    path = path.replace(/\\/g, '/'); // Normalize path seps in IE on Windows.
    var dirname = path.substring(0, path.lastIndexOf('/'));
    for (var i=0; i < depth; i++) {
        docPage = dirname.substring(dirname.lastIndexOf('/')+1) + '/' + docPage;
        dirname = dirname.substring(0, dirname.lastIndexOf('/'));
    }
    var ret = new Object();
    ret.dirname = dirname;
    ret.docPage = docPage;
    _log("   dirname: '"+dirname+"'");
    _log("   docPage: '"+docPage+"'");
    return ret;
}


// Return an appropriate URL for showing/hiding the TOC.
//
//  "docPage" is the relative product doc page (and anchor) for the content
//      frame.
//
// To show the TOC we want a URL of the form:
//      <url to product's index.html>?page=<docPage>
// To hide the TOC we want a URL of the form:
//      <url to base product's doc dir>/<docPage>
//
// We want to show the TOC when we are not in a frameset and vice versa.
function toggleFrameURL(docPage, depth /* =0 */)
{
    if (typeof(depth) == 'undefined') depth=0;
    _log("toggleFrameURL(docPage='"+docPage+"', depth="+depth+")");
    
    var url, path, dirname;
    if (top == window) {
        // We're not in a frameset: want to show the TOC.
        var info = docPageFromPath(self.location.pathname, docPage, depth);
        var escapedDocPage = info.docPage.replace(/\//g, "%2F"); // bug 32870
        url = self.location.protocol + '//' + self.location.host +
              info.dirname + '/index.html?page=' + escapedDocPage;
    } else {
        // We're in a frameset: want to hide the TOC.
        path = window.location.pathname;
        path = path.replace(/\\/g, '/'); // Normalize path seps in IE on Windows.
        dirname = path.substring(0, path.lastIndexOf('/'));
        url = unescape(dirname+'/'+docPage);
    }
    return url;
}


// Return an appropriate URL for syncing the TOC to the current page and
// anchor.
//
//  "docPage" is relative product doc page (and anchor) for the content
//      frame.
function syncFrameURL(docPage, anchorName, depth) {
    if (typeof(depth) == 'undefined') depth = 0;
    var url;
    if (top == window) {
        // We are not in a frameset (i.e. the TOC is not showing), therefore
        // we want to do the same thing as "Show/Hide TOC".
        url = toggleFrameURL(docPage, depth);
    } else {
        // The TOC is showing: we want to re-display the TOC at the current
        // doc page.
        var info = docPageFromPath(unescape(self.location.pathname),
                                   unescape(docPage), depth);
        var nodeId = getTocNodeId(info.docPage);
        url = 'javascript:top.reDisplay(\''+nodeId+'\',true);';
    }
    return url;
}

function writelinks(anchorName, depth) {
    _log("---- writelinks(anchorName='"+anchorName+ "', depth='"+depth+"')");
    if (typeof(depth) == 'undefined') depth = 0;
    var targetHref = escape(self.getCurrentTargetHref(anchorName));
    var toggleUrl = toggleFrameURL(targetHref, depth);
    _log("-> Show/Hide TOC: "+toggleUrl);
    var syncUrl = syncFrameURL(targetHref, anchorName, depth);
    _log("-> Sync TOC:      "+syncUrl);
    document.write('<a href="'+toggleUrl+'" target="_top">'+
                   '<div class="buttonShowHideTOC">'+
                   '<img src="img/x.gif" width="1" height="1" border="0" />'+
                   '</div></a>');
    document.write('<a href="'+syncUrl+'" target="_top">'+
                   '<div class="buttonSyncTOC">'+
                   '<img src="img/x.gif" width="1" height="1" border="0" />'+
                   '</div></a>');
}


// Re-display the TOC and possibly change the content frame.
//
//  "changeContent" is a boolean (default true) indicating if this
//      re-display should change the content frame URL. This is set to
//      false for the +/- icons so that the TOC can be manipulated without
//      changing the content.
//
function reDisplay(currentNumber, currentIsExpanded,
                   changeContent /* =true */)
{
    if (typeof(changeContent) == 'undefined') changeContent = true;
    if (typeof(textSizes) == 'undefined') return;

    toc.document.open();
    toc.document.write(
        "<html>\n"+
        "<head>\n"+
        "<title>ActivePython Docs - Table on Contents</title>\n"+
        "<link rel='stylesheet' type='text/css' href='aspn.css'>\n"+
        "<link rel='stylesheet' type='text/css' href='tocHeader.css'>\n"+  //XXX use UG's style.css?
        "</head>\n"+
        "<body marginheight='0' marginwidth='0' topmargin='0' leftmargin='0' rightmargin='0'>\n"+
        //"<a target='toc' href='javaScript:parent.reDisplay('',false);'><div class='tocHeaderSel'>Table of Contents</div></a>\n"+
        "<div class='tocContent'>\n"+
        "<table border=0 cellspacing=1 cellpadding=1 >\n"+
        "<tr>\n");
    var currentNumArray = currentNumber.split(".");
    var currentLevel = currentNumArray.length-1;
    var scrollY=0, addScroll=true, theHref="";
    for (i=0; i<tocTab.length; i++) {
        thisNumber = tocTab[i][0];
        var isCurrentNumber = (thisNumber == currentNumber);
        if (isCurrentNumber) theHref=tocTab[i][2];
        var thisNumArray = thisNumber.split(".");
        var thisLevel = thisNumArray.length-1;
        var toDisplay = true;
        if (thisLevel > 0) {
            for (j=0; j<thisLevel; j++) {
                toDisplay = (j>currentLevel)?false:toDisplay && (thisNumArray[j] == currentNumArray[j]);
            }
        }
        thisIsExpanded = toDisplay && (thisNumArray[thisLevel] == currentNumArray[thisLevel])
        if (currentIsExpanded
            // Never collapse a node when clicking on its name.
            && !changeContent)
        {
            toDisplay = toDisplay && (thisLevel<=currentLevel);
            if (isCurrentNumber) thisIsExpanded = false;
        }
        
        if (toDisplay) {
            if (isCurrentNumber) addScroll=false;
            if (i==0) {
                toc.document.writeln("\n<td valign=top colspan=" + (nCols+1) +
                    "><a href=\"javaScript:\parent.reDisplay('" +
                    thisNumber + "'," + thisIsExpanded +
                    ")\" style=\"font-family: " +
                    fontTitle + "; font-weight:normal; font-size:"
                    + textSizes[0] +
                    "em; color: " + titleColor +
                    "; text-decoration:none\">" +
                    tocTab[i][1] + "</a></td></tr>");
                for (k=0; k<nCols; k++) {
                    toc.document.write("<td>&nbsp;</td>");
                }
                toc.document.write("<td width=240>&nbsp;</td></tr>");
                }
            else {
                if (addScroll) scrollY+=((thisLevel<2)?mdi:sml)*25;
                var isLeaf = (i==tocTab.length-1) || (thisLevel >= tocTab[i+1][0].split(".").length-1);
                img = (isLeaf)?"leaf":(thisIsExpanded)?"minus":"plus";
                toc.document.writeln("<tr>");
                for (k=1; k<=thisLevel; k++) {
                    toc.document.writeln("<td>&nbsp;</td>");
                }
                toc.document.writeln(
                    "<td><a href=\"javaScript:parent.reDisplay('" +
                    thisNumber + "'," + thisIsExpanded +
                    ", false);\"><img src=\"img/" +
                    img + ".gif\" width=9 height=9 border=0></a></td> <td colspan=" + 
                    (nCols-thisLevel) +
                    "><a href=\"javaScript:\parent.reDisplay('" +
                    thisNumber + "'," +
                    thisIsExpanded + ")\" style=\"font-family: " +
                    fontLines + ";" +
                    ((thisLevel<=mLevel)?"font-weight:normal":"") +
                    "; font-size:" + ((thisLevel<=mLevel)?mdi:sml) +
                    "em; color: " + ((changeContent && isCurrentNumber)?currentColor:normalColor) +
                    "; text-decoration:none\">" +
                    ((showNumbers)?(thisNumber+" "):"") +
                                     tocTab[i][1] + "</a></td></tr>");
            }
        }
    }
    toc.document.writeln("</table>\n<br>\n</body>\n</html>");
    toc.document.close();
    toc.scroll(0,scrollY);
    
    if (changeContent && theHref != "") {
        top.content.location.href = theHref;
    }
}


function getPage() {
    var string = top.location.search.substring(1);
    var parm = 'page';
    var pageLink = null;
    // returns value of parm from string
    var startPos = string.indexOf(parm + "=");
    if (startPos > -1) {
        startPos = startPos + parm.length + 1;
        var endPos = string.indexOf("&",startPos);
        if (endPos == -1)
            endPos = string.length;
        pageLink = unescape(string.substring(startPos,endPos));
    }
    if (!pageLink) {
        top.reDisplay('0',true);
    } else {
        var nodeId = getTocNodeId(pageLink);
        top.reDisplay(nodeId, true);
    }
}


