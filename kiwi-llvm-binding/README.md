Prerequisites
=============


### SWIG

[SWIG](http://www.swig.org/) is used to automatically generate JNI bindings to the LLVM Core C-Interface. Therefore SWIG
must be installed on your system. Please make sure that the swig executable is part of the executable search path. On
Linux systems this should be no problem on Windows systems you usually have to append the installation path to your 
%PATH% variable, e.g. 

    E:\Programme\swigwin-3.0.12


### GCC

#### Linux

The GCC toolchain has to be installed on your system. On Linux systems please install them using your package manager.
On Debian/Ubuntu based systems this should be done by using the following command line

    sudo apt-get update
    sudo apt-get install build-essential

On RHEL/CentOS based systems please use the following command line

    su -    
    yum install make automake gcc gcc-c++ kernel-devel

#### Windows

Download the MinGW-w64 installer from this [URL](https://sourceforge.net/projects/mingw-w64/files/Toolchains%20targetting%20Win32/Personal%20Builds/mingw-builds/installer/mingw-w64-install.exe/download).
Then start the installer and set it up like this

![](../doc/MinGW-W64-installer.png)

After that append the installation path to your %PATH% variable, e.g.

    E:\Programme\mingw-w64\x86_64-7.3.0-posix-seh-rt_v5-rev0\mingw64\bin


### LLVM

Install or build LLVM for your system. It is recommended to build LLVM for your system. Please take a look at this
[document](../doc/BuildLLVM.md) how to build LLVM. After a successful build/installation please add the installation
binary path to your executable search path, e.g.

    G:\temp\LLVM\llvm-8.0.0-install-win64\bin

### JDK

 Install a recent version of the Java Development Kit. Both Oracle and OpenJDK should work. Java 8 is the recommended
 version. After successful installation please add the JAVA_HOME environment variable to your system, e.g.
 
    JAVA_HOME=E:\Programme\Java\jdk1.8.0_45
