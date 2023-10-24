# Simple test-app to verify T-Mobile tracing propagation headers in internal service call

Useful links:

* https://gitlab.cindy.t-mobile.pl/libs/logging/tracing-spring-boot-v2

### Branches:

* main - tests for rest-template, webclient, feign, reactive-feign solutions
* spring/3.1 - spring-boot 3.1.1 with trace library in 3.0.1 version
* spring/3.0 - downgrade spring-boot (3.0.5 - which corresponds to this from tracing lib)
* spring/2.7 - downgrade spring-boot (2.7.9) and tracing library (2.0.0)