#Proving Grounds
####Setting up environment
We are using [Eclipse Kepler SR2 w/ Java8 patch](http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/kepler/SR2-with-Java8-patches/eclipse-jee-kepler-SR2-Java8-win32.zip), [JDK7u55](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html), and [Git for Windows](http://git-scm.com/download/win). The file structure shown in the repository represents the project folder inside your designated Eclipse workspace. For example >> C:\eclipse_workspace\project_folder\git repo here

Java 7 requires the jfxrt.jar to be explicitly added to the program's class path therefore the path for your JDK needs to be "C:/Program Files/Java/jdk1.7.0_55/" to use the default .classpath file. Otherwise, you can alter the .classpath file manually every time you pull in order to locate "/Java/jdk1.7.0_55/jre/lib/jfxrt.jar".
