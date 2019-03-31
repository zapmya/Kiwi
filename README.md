# KIWI -- Green. Code. Commit.

Kiwi is a programming language targeted towards energy reduction. Kiwi tries to accomplish this
by generating code that is as fast as native C/C++ code. Kiwi tries to combine the advantages of C and
Java. Like C/C++ it will compile to native code which results in small and fast executables. But it
has a cleaned up syntax just like Java. This means there will be no explicit pointer arithmetic. Yet
you will still be able to directly address certain regions in memory so making it feasible for developing
hardware drivers.

To support all major platforms and CPUs [LLVM](http://www.llvm.org) is used as Kiwi's compiler backend. 

In addition to that Kiwi is directly supporting [SIMD](https://de.wikipedia.org/wiki/Streaming_SIMD_Extensions) vector operations without having to use intrinsics
or inline assembly. This is achieved by defining its own vector data types similar to the ones known from
shader languages like GLSL / HLSL. These datatypes are not limited to a certain SIMD implementation like SSE
instead LLVM is used to map these datatypes and instructions to the available implementation on a certain
CPU. This way SIMD is also used when compiling Kiwi code for mobile devices. 

In the [PoC folder](https://github.com/zapmya/Kiwi/tree/master/PoC) is a simple
[example](https://github.com/zapmya/Kiwi/blob/master/PoC/src/dotProduct.kiwi) which
demonstrates how vector data types are integrated into the KIWI programming language. There is also an
equivalent [C implementation](https://github.com/zapmya/Kiwi/blob/master/PoC/src/dotProduct.c). As a proof
 of concept the Kiwi code has been compiled for Linux 64bit systems. There are three native executables
 in the [PoC/bin folder](https://github.com/zapmya/Kiwi/tree/master/PoC/bin). All of them were compiled from the
 identical source code but a different CPU target has been specified. So the [kiwiDots_avx](https://github.com/zapmya/Kiwi/blob/master/PoC/bin/kiwiDots_avx)
 is compiled and optimized for CPUs that support the [AVX](https://de.wikipedia.org/wiki/Advanced_Vector_Extensions) extensions.   
