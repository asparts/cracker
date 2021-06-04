README FILE

1.0 How this software works
1.1 Cracker
1.2 PortScanner
1.3 BruteForce
1.4 Menus
2.0 Derby Database

1.0 How this software works

GUI of this software is made with java swing, and is supposed to be as simple as possible. There's 3 tabs, that contains
different parts of the software. 

1.1 First tab is HashCracker, which has connection to local derby database, where's stored
2 lists of words, 1 list in finnish and 1 list in english at the moment. Total count of words is about 140000, however,
all the words are lowercase. The cracker tool works in a way, that user inputs SHA-256 hash to the hash field, and software
querys the database with given hash to find matching password for it.

1.2 Second tab is port scanner, which scans ports. You can give the port scanner host address and range for searching ports.
Port scanner is very slow on this application, so I recommend that you don't scan for all 65k+ ports.

1.3 Third tab is simple bruteforce tool, where you can give hash, and the bruteforce tool starts to hash all possible options from given
minimum lenght, and returns the answer. This tool is under development, so it has some bugs in it.

1.4 From menus, you can add more words to database (File -> Add words to database). In inputfile there should be one word in each line.
From preferences menu, you can adjust the bruteforce tool to use different preset charactersets, or give your own characterset for it.

2.0 Derby Database

Apache Derby, an Apache DB subproject, is an open source relational database implemented entirely in Java and available under the Apache License, Version 2.0. Some key advantages include:

Derby has a small footprint -- about 3.5 megabytes for the base engine and embedded JDBC driver.
Derby is based on the Java, JDBC, and SQL standards.
Derby provides an embedded JDBC driver that lets you embed Derby in any Java-based solution.
Derby also supports the more familiar client/server mode with the Derby Network Client JDBC driver and Derby Network Server.
Derby is easy to install, deploy, and use.

Link to the Apache Derby website https://db.apache.org/derby/

