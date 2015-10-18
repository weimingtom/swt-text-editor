Source code Import

You need to have Apache Ant >= 1.8 and Java SE (Not JRE) >= 1.6.

From command line run "ant retrieve" from directory where you have downloaded source code. It'll download all dependencies into lib

After that you could import project into your IDE.

After importing mark "src" directory as source root, "resources" as source root/resources diretory(depends on your IDE). Add All Jar's from lib directory into your classpath. Add one jar from lib/swt/swt-${platform}-${architecture}.jar into your classpath (depends on your OS and Architecture). Now you should be able to start playing with code in your IDE.