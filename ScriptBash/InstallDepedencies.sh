#!/bin/sh
# Install calimero depedencie
mvn install:install-file -DgroupId=calimero -DartifactId=calimero -Dversion=2.04 -Dpackaging=jar -Dfile=ExternalJars/calimero-2.0.4.jar
#Install mysql 
mvn install:install-file -DgroupId=mysql.connector.jdbc -DartifactId=mysql.connector.jdbc -Dversion=1.9.0 -Dpackaging=jar -Dfile=ExternalJars/mysql-connector-java-5.1.22-bin.jar