.. The following guide is included in ActivePython documentation.  So if you
.. make any changes, please update a copy of this file in the ActivePython
.. source repo: src/docs/pypm-tutorial.rst

PyPM tutorial
=============

Welcome to the PyPM tutorial.

.. contents:: Table of Contents
   :depth: 3


What is PyPM?
-------------

PyPM is the package management utility for ActivePython. It simplifies the task
of locating, installing, upgrading and removing Python modules. The PyPM client
accesses PyPM repositories (collections of packages) on the internet or on a
local network.

Setup
~~~~~

PyPM is installed automatically with ActivePython. Currently only Windows,
Linux and MacOSX builds include PyPM.

To use PyPM, your computer must be connected to the internet (to access the
ActiveState PyPM repository), or have access to a PyPM repository on a local
hard drive or network share.

If you connect to the internet through a firewall or proxy, you may need to set
the ``http_proxy`` environment variable (see `Proxies and Firewalls`_).

All PyPM operations are performed at the command line.


Package Management
------------------

Finding packages
~~~~~~~~~~~~~~~~

Use the the ``search`` command to find the package you are looking for. This
returns substring matches in the name or description::

  C:\> pypm search django url
   django-localeurl A Django application that 
   django-shorturls A short URL (rev=cannonical) 
   django-auto-urls Django app that try to load 
  ...

Installing packages
~~~~~~~~~~~~~~~~~~~

Installing packages is simple::

  C:\> pypm install pyramid

This command will download ``pyramid`` and its depdendencies automatically.
Unless the ``-g`` option is specified, packages are not installed into the
ActivePython installation directory. Instead, PyPM follows the `PEP 370`__
specification and installs packages by default in ``~/.local`` on Unix and Mac
and ``%APPDATA%\Python`` on Windows; and the Python interpreter automatically
picks up the local packages. To find where the package was installed, use the
``show`` or ``files`` command.

.. __: http://www.python.org/dev/peps/pep-0370/

.. note::

  The ``install`` command automatically upgrades packages to newly available
  versions.


Uninstalling packages
~~~~~~~~~~~~~~~~~~~~~

To uninstall packages::

  C:\> pypm uninstall mako

Uninstalling a package will automatically uninstall other packages that depend
upon that package.

Upgrading packages
~~~~~~~~~~~~~~~~~~

To upgrade all installed packages to the latest version from the repository::

  C:\> pypm upgrade

Showing package details
~~~~~~~~~~~~~~~~~~~~~~~

The 'list' command shows the currently installed packages::

  C:\> pypm list
  jsontools  0.1-1   jsontools - Useful utilities for JSON
  simplejson 2.0.9-1 Simple, fast, extensible JSON encoder/decoder for Python
  paste      1.7.2-1 Tools for using a Web Server Gateway Interface stack
  ...

The 'show' command displays information about a specified package in the
repository::

  C:\> pypm show mako
  Name: mako
  Version: 0.2.5-1
  Author: Mike Bayer <mike@zzzcomputing.com>
  Description: A super-fast templating language that borrows the
  best ideas from the existing templating languages.
  Home Page: http://www.makotemplates.org/
  License: MIT
  Dependencies: Beaker>=1.1
  Status: Not installed
  
The 'files' command displays all files installed by a specified package::

  $ pypm files mako
  ~/.local/bin
  ~/.local/bin/mako-render
  ~/.local/lib
  ...
  
Other commands
~~~~~~~~~~~~~~

The 'help' command provides details on specific sub commands. Abbreviations for
the command is shown in parentheses::

  C:\> pypm help list
  list (l): List the currently installed packages

  Usage:
      pypm list

The 'info' command shows the PyPM version, Python version, platform and
repositories::

  $ pypm info
  PyPM 1.1.1 (ActivePython 2.6)
  Installation target: ~\Application Data\Python (2.6) (win32-x86)
  (type "pypm info --full" for detailed information)
  

Virtualenv support
------------------

PyPM supports virtualenv_ (a tool for creating isolated Python
environments) with the ``-E`` option::

  C:\> virtualenv C:\myvirtualenv
  C:\> pypm -E C:\myvirtualenv install pyramid

.. _virtualenv: http://pypi.python.org/pypi/virtualenv

.. note::

  To use virtualenv on Mac OS X, you will need to have Xcode_ installed.
  
.. _Xcode: http://developer.apple.com/technology/xcode.html


Repository management
---------------------

Documentation for the following is coming soon:

  1) specifying additional repositories using ``pypm -R``
  2) Custom configuration file for client


Proxies and Firewalls
---------------------

If you connect to the internet through a proxy server, you may need to
set the ``http_proxy`` environment variable.

Set the http_proxy variable with the hostname or IP address of the proxy
server::

  http_proxy=http://proxy.example.org

If the proxy server requires a user name and password, include them in the
following form::

  http_proxy=http://username:password@proxy.example.org

If the proxy server uses a port other than 80, include the port number::

  http_proxy=http://username:password@proxy.example.org:8080


Web frontend
------------

Python packages, including those not in PyPM repositories yet, can be browsed
at `PyPM Index`__.

  http://code.activestate.com/pypm/
  
.. __: http://code.activestate.com/pypm/

If a package is missing in the PyPM repository, you may use this resource to
diagnose what went wrong and/or contact the author (or ActiveState)
accordingly.


Roadmap
-------

PyPM currently does not have a GUI frontend.

Almost all modules are available in the ActiveState repositories, and we're
working to make the important unavailable ones available as soon as possible.

We have plans for several improvements in the upcoming releases.


Credits
-------

PyPM would not be possible without the generous support of the following
open source software:

- appdirs - http://github.com/ActiveState/appdirs
- applib - http://github.com/ActiveState/applib
- cmdln - http://code.google.com/p/cmdln/
- fabric - http://docs.fabfile.org/
- httplib2 - http://code.google.com/p/httplib2/
- Hudson - http://hudson-ci.org/
- Jinja2 - http://jinja.pocoo.org/2/
- mailer - http://pypi.python.org/pypi/mailer
- pkginfo - http://pypi.python.org/pypi/pkginfo
- py.test - http://pytest.org/
- pyyaml - http://pyyaml.org/
- setuptools - http://pythonhosted.org/setuptools/
- six - http://packages.python.org/six/
- SQLAlchemy - http://www.sqlalchemy.org/
- Sphinx - http://sphinx.pocoo.org/
- tox - http://codespeak.net/tox/
- virtualenv - http://virtualenv.openplans.org/
- zc.lockfile - http://pypi.python.org/pypi/zc.lockfile

