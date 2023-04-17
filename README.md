clj.java
========
The clj.java library contains various functions for Clojure/Java integration.
clj.java only requires classes from the JDK. It does not have external dependencies.
The functions in this library are expected to provide an idiomatic API for Clojure and some additional value over plain Java interop.

clj.java contains the following namespaces
* beaninfo - functions to set Java bean properties via property descriptors
* beans- functions for method-based reflective access to Java bean style objects
* codec- functions to encode/decode to Hex and Base64
* file - functions for working with files, search paths and file search
* i18n - functions for internationalization via Java resource bundles
* message-digest - functions to create message digests
* net - functions for network access
* properties - function to create Java Properties from a Clojure map
* reflection - functions for reflective introspection of Java classes
* system - functions for interacting with java.lang.System
* type-conversion - Java type coercion/conversion functions
* zip - functions for handling zip files (unzipping for now)


[![Clojars Project](https://img.shields.io/clojars/v/org.soulspace.clj/clj.java.svg)](https://clojars.org/org.soulspace.clj/clj.java)
[![cljdoc badge](https://cljdoc.org/badge/org.soulspace.clj/clj.java)](https://cljdoc.org/d/org.soulspace.clj/clj.java)

Copyright
---------
© 2011-2021 Ludger Solbach

License
-------
[Eclipse Public License 1.0](http://www.eclipse.org/legal/epl-v10.html)

Code Repository
---------------
[CljBase on GitHub](https://github.com/soulspace-org/clj.java)
