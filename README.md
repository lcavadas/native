# native

This project is a PoC for measuring the performance of native-compiling a spring boot server.

## Flavours

There are versions in Groovy, Java & Kotlin that use MongoDB and one Java-JPA that uses java & JPA (connecting to postgres).

## Conclusions

This was tested with graalvm 21 and showed that while it had impressive boot time, JIT compilation ended up rendering better performance after the code is exercised a bit. This was measured using a JMeter test file to make requests to the running server.
