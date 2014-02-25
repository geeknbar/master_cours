=============================================
ActivePython FAQ (Frequently Asked Questions)
=============================================

This document contains frequently asked questions (and answers) regarding
ActivePython. More FAQs are available at `community.activestate.com`_. Please
send suggestions for new FAQ entries to `activepython-feedback`_.

.. _community.activestate.com: http://community.activestate.com/faq-list?filter0=54

.. contents:: **Table of Contents**
.. sectnum::


Mac OS X
========

Does ActivePython collide with other Python distributions on Mac OS X?
----------------------------------------------------------------------

There are many Python distributions for Mac OS X: Apple's system Python
installation, MacPython, Python from the Fink and Darwin ports projects and
ActivePython. In general, ActivePython can co-exist with these other Python
installations. However, there are some things of which to be aware.

1. Only one ``python`` can be first on your ``PATH`` environment variable at the
same time. This determines what Python is run when you type ``python`` at the
terminal. Likewise for ``pythonw``. ActivePython on Mac OS X sets up symlinks
for ``python``, ``pythonw``, and versioned ones (e.g.  ``python2.4`` and
``pythonw2.4``) in ``/usr/local/bin``. You can ensure that ActivePython is first
on your ``PATH`` in the terminal by including the following in your
``~/.bashrc`` file (if you use Bash as your shell, the default on Mac OS X 10.3
and later)::

  export PATH=/usr/local/bin:$PATH

or the following in your ``~/csh.cshrc`` (if you use the ``tcsh`` shell)::

  setenv PATH /usr/local/bin:$PATH

Note that this will only effect your ``PATH`` in the shell. To change your
system path (e.g. if you need you environment changes to exist for GUI apps) you
must modify your ``~/.MacOSX/environment.plist`` file. See Apple's Technical Q&A
`QA1067`_ document for details.

.. _QA1067: http://developer.apple.com/mac/library/qa/qa2001/qa1067.html

2. Both ActivePython and MacPython install to the same location on disk
(``/Library/Frameworks/Python.framework/...`` with some links in
``/usr/local/bin``), therefore ActivePython and MacPython installations of the
same versions can collide. The ActivePython installer will properly install over
a MacPython installation of the same version.  However it is recommended that
you first uninstall MacPython $(pp.PyVer) before installing ActivePython
$(pp.PyVer).


How do I open the ActivePython help on Mac OS X?
------------------------------------------------

On Mac OS X, ActivePython registers its documentation set with the Apple
Help System. You can open the ActivePython help in "Help Viewer" as follows:

* Open "Help Viewer" with any of the following methods:

  * Click "Help | *Application* Help" in any GUI app; or
  * browse for and double-click on ``/System/Library/CoreServices/Help
    Viewer.app`` in Finder; or
  * run the following at the terminal: ``open -a "Help Viewer"``

* Select "ActivePython $(pp.PyVer) Help" from Help Viewer's "Library" menu.

ActivePython's help documentation is physically located in::

  /Library/Frameworks/Python.framework/Versions/$(pp.PyVer)/Resources/Python.app/Contents/Resources/English.lproj/Help

with a shorter symlink to that directory at::

  /Library/Documentation/Help/ActivePython-$(pp.PyVer)

If "ActivePython $(pp.PyVer) Help" does not appear in the Help Viewer's
"Library" menu, this means that the ActivePython help book has not been
registered. You may need to log out and log back in. You can always view the
ActivePython documentation in your browser (instead of in Help Viewer) by
browsing to ``index.html`` in the mentioned folders or by running a command like
the following::

  open /Library/Documentation/Help/ActivePython-$(pp.PyVer)/index.html
  
  
How do I run ActivePython in 32-bit mode?
-----------------------------------------

Beginning 2.6.6.15, 2.7, 3.1.2.4 and 3.2, ActivePython for Mac is built with
2-way architecture: i386 and x86_64. By default, if your processor architecture
supports it, Python will run in 64-bit mode. To launch the interpreter in 32-bit
mode, run::

  $ arch -i386 python
  
Python 2.6 and 3.1 do not support architecture selection via the ``arch``
command on OSX 10.6. For this reason, architecture-specific binaries are
provided::

  $ python3.1-32
  $ python2.6-32
  
  

PyPM
====

*See also*: `PyPM Index FAQ <http://code.activestate.com/help/faq/#pypm-index>`_

How do I install a package that is not available in PyPM?
---------------------------------------------------------

While you can install most packages registered in PyPI_ using the PyPM client,
some packages may not yet be available in the ActiveState repository. You can
either download these package manually and build them yourself or, if the
package is registered in PyPI, try using ``pip`` (pip is included in
ActivePython) ::

  $ pip install twisted

.. _PyPI: http://pypi.python.org/
.. _pip: http://code.activestate.com/pypm/pip/

Can both 32-bit and 64-bit packages co-exist on the same machine?
-----------------------------------------------------------------

By default, PyPM installs packages in to the user site directory, which -
according to PEP 370 - is the same for 32-bit and 64-bit Python. Therefore it
is not recommended to mix 32-bit and 64-bit packages. To workaround this
limitation, always install packages into your global ActivePython installation;
this can be done using the ``-g`` option, for example::

  $ pypm -g install pycrpto
