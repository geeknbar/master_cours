/* Copyright (c) 2004-2006 ActiveState Software Inc. */


/* These are the parameters to define the appearance of the ToC. */
var showNumbers = false;        // display the ordering strings: yes=true | no=false
var backColor = "#FFFFFF";      // background color of the ToC - jen sez this doesn't work
var normalColor = "#000000";    // text color of the ToC headlines
var currentColor = "#B82619";   // text color of the actual line just clicked on
var titleColor = "#000000";     // text color of the title "Table of Contents"
var mLevel = 5;                 // number of levels minus 1 the headlines of
                                // which are presented with large and bold
                                // fonts 

// font-size factors for: [0] the title "Table of Contents", 
// [1] larger and bold fonts [2] smaller fonts if MS Internet Explorer 
// [3] larger and bold fonts [4] smaller fonts if Netscape Navigator.
//var textSizes = new Array(1, 0.6, 0.6, 0.8, 0.7);
var textSizes = new Array(1, 1, 1, 1, 1);
var fontTitle = "Verdana,Arial"; // font-family of the title "Table of Contents"
var fontLines = "Verdana,Arial"; // font-family of the headlines  