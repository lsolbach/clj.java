ChangeLog
=========

Version 0.9.1
-------------
* added XML serialization for beans
* added additional predicates for beans
* added docstrings to namespaces
* added more type hints
* added certificate retrieval for SSL connections

Version 0.9.0
-------------
* moved file namespace from clj.base to clj.java because of the JVM dependency
* added zip namespace with an unzip function
* added type hints for the return values in beans
* added type hints for the return values in reflection
* added method-name function to reflection
* added type hints and additional coercions in type-conversion
* rewrote codec functions because javax.xml.bind was removed from JDK in Java 11

Version 0.8.0
------------- 
* updated clj.base dependency, added, deps.edn, updated project files
* renamed to clj.java to make the library compatible with clojars
* updated project files accordingly
* made the code compliant to current Clojure coding practice (e.g. require over use)
* updated comments and documentation in the code
* moved some Java specific namespaces from clj.base to clj.java (e.g. codec, message-digest)
* moved some Java only namespaces from clj.app to clj.java (e.g. i18n, system)
* added Leiningen project file

Version 0.4.0
-------------
* updated CljLibrary dependency
* added copyright header in source files
* updated module files
* enhanced type coercions

Version 0.3.0
-------------
* enhanced doc strings
* updated to CljLibrary 0.4.0
* updated module files

Version 0.2.0
-------------
* added new type conversions from Integers to other numeric types
* added license.txt
* updated module files
* initial github import
* initial git import
