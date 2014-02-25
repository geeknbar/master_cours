#!/bin/sh
# Copyright (c) 2003-2006 ActiveState Software Inc.

#
# ActivePython "AS package" simple install script
#
# To install ActivePython, run:
#     ./install.sh
# To see additional install options, run:
#     ./install.sh -h
#

dname=`dirname $0`
LD_LIBRARY_PATH=$dname/INSTALLDIR/lib
export LD_LIBRARY_PATH
$dname/INSTALLDIR/bin/python3.3 -E $dname/_install.py $*

