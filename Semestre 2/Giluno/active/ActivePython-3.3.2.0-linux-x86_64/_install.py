#!/usr/bin/env python
# Copyright (c) 2003-2013 ActiveState Software Inc.

#
#********************************************************************
# WARNING: Do not run this script directly. Run the main "./install.sh"
#          which will launch this script properly.
#********************************************************************

"""
    ./install.sh - ActivePython install script

    Usage:
        ./install.sh [options...]

    General Options:
        -h, --help          print this help and exit
        -v, --verbose       verbose output

        -I, --install-dir <dir>     specify install directory

    When called without arguments this script will interactively install
    ActivePython. If the install dir is specified then ActivePython will
    be installed without interaction.
"""


import os
import sys
import getopt
import re
import stat
import logging
if sys.platform.startswith("win"):
    try:
        import winreg
    except ImportError:
        import _winreg as winreg # python2
if sys.version_info[0] < 3:
    input = raw_input

import sh2
import activestate


#---- exceptions

class Error(Exception):
    pass



#---- global data

gDefaultInstallDir = r"/opt/ActivePython-3.3"

_version_ = (0, 1, 0)
log = logging.getLogger("install")



#---- internal routines and classes

if sys.platform.startswith("win"):
    def _getSystemDrive():
        try:
            return os.environ["SystemDrive"]
        except KeyError:
            raise Error("'SystemDrive' environment variable is not set")

def _getDefaultInstallDir():
    default = gDefaultInstallDir
    if sys.platform.startswith("win") and\
       default.lower().find("%systemdrive%") != -1:
        default = re.compile("%SystemDrive%", re.I).sub(_getSystemDrive(),
                                                        default)
    return default

def _askYesNo(question, default="yes"):
    """Ask the user the given question and their answer.
    
    "question" is a string that is presented to the user.
    "default" is the presumed answer if the user just hits <Enter>.
        It must be "yes" (the default), "no" or None (meaning
        an answer is required of the user).

    The "answer" return value is one of "yes" or "no".
    """
    valid = {"yes":"yes",   "y":"yes",  "ye":"yes",
             "no":"no",     "n":"no"}
    if default == None:
        prompt = " [y/n] "
    elif default == "yes":
        prompt = " [Y/n] "
    elif default == "no":
        prompt = " [y/N] "
    else:
        raise Error("invalid default answer: '%s'" % default)

    while 1:
        sys.stdout.write(question + prompt)
        choice = input().lower()
        #sys.stdout.write('\n')
        if default is not None and choice == '':
            return default
        elif choice in list(valid.keys()):
            return valid[choice]
        else:
            sys.stdout.write("Please repond with 'yes' or 'no' "\
                             "(or 'y' or 'n').\n")

def _validateInstallDir(installDir):
    if os.path.exists(installDir) and not os.path.isdir(installDir):
        raise Error("cannot install to '%s': exists and is not a directory"
                    % installDir)


# Adapted from pywin32/pywin32_postinstall.py::LoadSystemModule()
def _loadPyWin32Module(modname, dirname):
    # See if this is a debug build.
    import imp
    for suffix_item in imp.get_suffixes():
        if suffix_item[0] == '_d.pyd':
            suffix = '_d'
            break
    else:
        suffix = ""
    basename = "%s%d%d%s.dll" % \
               (modname, sys.version_info[0], sys.version_info[1], suffix)
    path = os.path.join(dirname, basename)
    mod = imp.load_module(modname, None, path, 
                          ('.dll', 'rb', imp.C_EXTENSION))


# Adapted from pywin32/pywin32_postinstall.py::RegisterCOMObjects()
def _registerCOMObject(module, klass_name, register=1):
    import win32com.server.register
    if register:
        func = win32com.server.register.RegisterClasses
    else:
        func = win32com.server.register.UnregisterClasses
    flags = {}
    __import__(module)
    mod = sys.modules[module]
    flags["finalize_register"] = getattr(mod, "DllRegisterServer", None)
    flags["finalize_unregister"] = getattr(mod, "DllUnregisterServer", None)
    klass = getattr(mod, klass_name)
    func(klass, **flags)

def _getPyRootRegKeyName():
    ver = "%d.%d" % (sys.version_info[0], sys.version_info[1])
    return "Software\\Python\\PythonCore\\" + ver

# Adapted from pywin32/pywin32_postinstall.py::get_root_key()
def _getPyRootRegKey():
    root_key_name = _getPyRootRegKeyName()
    try:
        winreg.OpenKey(winreg.HKEY_LOCAL_MACHINE,
                        root_key_name, winreg.KEY_CREATE_SUB_KEY)
        return winreg.HKEY_LOCAL_MACHINE
    except OSError as details:
        # Either not exist, or no permissions to create subkey means
        # must be HKCU
        return winreg.HKEY_CURRENT_USER

# Adapted from pywin32/pywin32_postinstall.py::SetPyKeyVal()
def _setPyRegKeyVal(key_name, value_name, value):
    root_key_name = _getPyRootRegKeyName()
    root_hkey = _getPyRootRegKey()
    root_key = winreg.OpenKey(root_hkey, root_key_name)
    try:
        my_key = winreg.CreateKey(root_key, key_name)
        try:
            winreg.SetValueEx(my_key, value_name, 0, winreg.REG_SZ, value)
        finally:
            my_key.Close()
    finally:
        root_key.Close()
    #print("-> %s\\%s[%s]=%r" % (root_key_name, key_name, value_name, value))




#---- public module interface

def interactiveInstall():
    default = _getDefaultInstallDir()
    sys.stdout.write("""\
Enter directory in which to install ActivePython. Leave blank and
press 'Enter' to use the default [%s].
Install directory: """ % default)
    installDir = input().strip()
    if not installDir:
        installDir = default

    norm = os.path.normpath(os.path.expanduser(installDir))
    if os.path.isdir(norm):
        sys.stdout.write("""
'%s' already exists. Installing over existing
Python installations may have unexpected results. Are you
sure you would like to proceed with the installation?
""" % installDir)
        choice = _askYesNo("Proceed?", default="no")
        if choice == "yes":
            pass
        elif choice == "no":
            print("Aborting install.")
            return
    elif os.path.exists(norm):
        raise Error("'%s' exists and is not a directory" % installDir)

#    sys.stdout.write("""
#ActivePython requires 144.0 MB of disk space. Please make sure
#enough free space is available before continuing. Would you like to
#proceed with the installation?
#""")
#    choice = _askYesNo("Proceed?")
#    if choice == "yes":
#        pass
#    elif choice == "no":
#        print("Aborting install.")
#        return

    print()
    install(installDir)


def install(installDir=None):
    absInstallDir = os.path.abspath(os.path.normpath(
        os.path.expanduser(installDir)))
    os.chdir(os.path.dirname(__file__) or os.curdir) # change to unpack dir
    print("Installing ActivePython to '%s'..." % absInstallDir)
    _validateInstallDir(absInstallDir)
    
    if not os.path.exists(absInstallDir):
        os.makedirs(absInstallDir)
    if sys.platform.startswith("win"):
        preserve = False # Fails on WinNT, at least, with Permission Denied
    else:
        preserve = True
    sh2.cp(os.path.join("INSTALLDIR", "*"),
           dstdir=absInstallDir, preserve=preserve,
           recursive=True)

    if sys.platform.startswith("win"):
        if activestate.version_info["pywin32_ver"] is not None:
            # Bootstrap the PyWin32 modules so we can use them for some PyWin32
            # setup.
            print("Removing old PyWin32 registry 'Modules' entries...")
            # Directly from pywin32/pywin32_postinstall.py::install():
            for name in "pythoncom pywintypes".split():
                keyname = "Software\\Python\\PythonCore\\" + sys.winver + "\\Modules\\" + name
                for root in winreg.HKEY_LOCAL_MACHINE, winreg.HKEY_CURRENT_USER:
                    try:
                        winreg.DeleteKey(root, keyname + "\\Debug")
                    except WindowsError:
                        pass
                    try:
                        winreg.DeleteKey(root, keyname)
                    except WindowsError:
                        pass
            
            scriptDir = os.path.dirname(__file__) or os.curdir
            pySystemFilesDir = os.path.join(scriptDir, "INSTALLDIR")
            _loadPyWin32Module("pywintypes", pySystemFilesDir)
            _loadPyWin32Module("pythoncom", pySystemFilesDir)
    
            # Register PyWin32 COM modules.
            # Must keep this in sync with pywin32_postinstall.py.
            print("Registering PyWin32 COM modules...")
            com_modules = [
                # module_name,                      class_names
                ("win32com.servers.interp",         "Interpreter"),
                ("win32com.servers.dictionary",     "DictionaryPolicy"),
                ("win32com.axscript.client.pyscript","PyScript"),
            ]
            import win32api
            for module, class_name in com_modules:
                try:
                    _registerCOMObject(module, class_name)
                except win32api.error as ex:
                    if ex[0] == 5: # ERROR_ACCESS_DENIED
                        log.warn("could not register sample PyWin32 COM modules: "
                                 "you do not have permission to install COM objects")
                    else:
                        log.warn("unexpected error registering sample PyWin32 "
                                 "COM modules: %s", ex)
    
            # Register Pythonwin help file in registry.
            chm = os.path.join(absInstallDir, "Doc",
                               "ActivePython%s%s.chm" % sys.version_info[:2])
            try:
                _setPyRegKeyVal("Help\\Pythonwin Reference", None, chm)
            except winreg.error as ex:
                log.info("could not set PythonWin help reference registry key "
                         "(this is not serious): %s", ex)
    
            # Create win32com\gen_py directory.
            genPy = os.path.join(absInstallDir, "Lib", "site-packages",
                                 "win32com", "gen_py")
            if not os.path.exists(genPy):
                print("Creating '%s'..." % genPy)
                os.makedirs(genPy)

        #TODO:
        # - Warn if there are potentially conflicting py*.dll's in
        #   the system directory and/or just implement copying those over.
        # - Handle any Windows integration. Add options for this.
        #   - program group and shortcuts
        #   - PATH and PATHEXT mods
        #   - file associations

        doc = os.path.join(absInstallDir, "Doc",
                           "ActivePython%s%s.chm" % sys.version_info[:2])
        print("""
ActivePython has been successfully installed to:

    %s

The documentation is available here:

    %s
    web: http://docs.activestate.com/activepython/

Please send us any feedback you might have or log bugs here:

    activepython-feedback@ActiveState.com
    http://bugs.activestate.com/ActivePython/

Thank you for using ActivePython.""" % (absInstallDir, doc))

    else:
        print("Relocating dir-dependent files...")
        activestate.relocate_python(install_prefix=absInstallDir,
                                    verbose=log.isEnabledFor(logging.DEBUG))

        print("Pre-compiling .py files in the standard library...")
        libDir = os.path.join(absInstallDir, "lib",
                              "python%s.%s" % sys.version_info[:2])
        cmds = ['%s -W "ignore:hex/oct constants" %s/compileall.py -q -x "test(s)?/" %s'\
                    % (sys.executable, libDir, libDir),
                '%s -W "ignore:hex/oct constants" -O %s/compileall.py -q -x "test(s)?/" %s'\
                    % (sys.executable, libDir, libDir)]
        for cmd in cmds:
            log.debug("running '%s'" % cmd)
            retval = os.system(cmd)
            if retval:
                log.warn("error running '%s'" % cmd)

        docDir = os.path.join(absInstallDir, "doc",
                              "python%s.%s" % sys.version_info[:2],
                              "index.html")
        webdoc = 'http://docs.activestate.com/activepython/%s.%s' % \
            sys.version_info[:2]
        print("""
ActivePython has been successfully installed to:

    %s
    
You can add the following to your .bashrc (or equivalent)
to put ActivePython on your PATH:

    export PATH=%s/bin:$PATH

The documentation is available here:

    %s
    web: %s

Please send us any feedback you might have or log bugs here:

    activepython-feedback@ActiveState.com
    http://bugs.activestate.com/ActivePython/

Thank you for using ActivePython.
""" % (absInstallDir, absInstallDir, docDir, webdoc))


#---- mainline

def main(argv):
    logging.basicConfig()

    # Parse options.
    try:
        opts, args = getopt.getopt(argv[1:], "VvhI:",
            ["version", "verbose", "help", "install-dir="])
    except getopt.GetoptError as ex:
        log.error(str(ex))
        log.error("Try `./install.sh --help'.")
        return 1
    installDir = None
    for opt, optarg in opts:
        if opt in ("-h", "--help"):
            sys.stdout.write(__doc__)
            return
        elif opt in ("-V", "--version"):
            ver = '.'.join([str(part) for part in _version_])
            print("install %s" % ver)
            return
        elif opt in ("-v", "--verbose"):
            log.setLevel(logging.DEBUG)
        elif opt in ("-I", "--install-dir"):
            installDir = optarg

    try:
        if installDir is None:
            interactiveInstall()
        else:
            install(installDir)
    except (EnvironmentError, Error) as ex:
        log.error(str(ex))
        #XXX help blurb???
        if log.isEnabledFor(logging.DEBUG):
            print()
            import traceback
            traceback.print_exception(*sys.exc_info())
        return 1
    except KeyboardInterrupt:
        log.debug("user abort")
        pass


if __name__ == "__main__":
    __file__ == sys.argv[0]
    sys.exit( main(sys.argv) )

