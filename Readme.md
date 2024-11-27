Créez le répertoire parent et créez le pom.xml avec ce contenu:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <groupId>fr.pantheonsorbonne</groupId>
  <artifactId>parent</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>
  <name>projet-m1</name>
<modules>
<module>accountancy-service</module>
<module>invoice-service</module>
<module>user-service</module>
</modules>
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>io.quarkus.platform</groupId>
        <artifactId>quarkus-bom</artifactId>
        <version>3.15.2</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-install-plugin</artifactId>
        <version>3.1.0</version>
      </plugin>
    </plugins>
  </build>
</project>
```

```bash
quarkus create app fr.pantheonsorbonne:user-service:1.0-SNAPSHOT -P io.quarkus:quarkus-bom:3.15.2 -x quarkus-rest,quarkus-rest-jackson,org.apache.camel.quarkus:camel-quarkus-core:3.15.0,quarkus-jdbc-mariadb,io.quarkus:quarkus-hibernate-orm,quarkus-smallrye-openapi,rest-client-jackson,org.apache.camel.quarkus:camel-quarkus-jacksonxml:3.15.0,org.apache.camel.quarkus:camel-quarkus-sjms2:3.15.0,org.apache.camel.quarkus:camel-quarkus-file:3.15.0,org.apache.camel.quarkus:camel-quarkus-direct:3.15.0,org.apache.camel.quarkus:camel-quarkus-jackson:3.15.0,org.apache.camel.quarkus:camel-quarkus-csv:3.15.0,org.apache.camel.quarkus:camel-quarkus-bean:3.15.0,io.quarkiverse.artemis:quarkus-artemis-jms:3.6.0 --java 21 -c quarkus.http.port=8080 --no-code

cd user-service
mkdir -p src/main/java/fr/pantheonsorbonne/dto
mkdir -p src/main/java/fr/pantheonsorbonne/entity
mkdir -p src/main/java/fr/pantheonsorbonne/dao
mkdir -p src/main/java/fr/pantheonsorbonne/service
mkdir -p src/main/java/fr/pantheonsorbonne/resources
mkdir -p src/main/java/fr/pantheonsorbonne/gateway
mkdir -p src/main/java/fr/pantheonsorbonne/camel
mkdir -p src/main/java/fr/pantheonsorbonne/exception
cd ..

quarkus create app fr.pantheonsorbonne:invoice-service:1.0-SNAPSHOT -P io.quarkus:quarkus-bom:3.15.2 -x quarkus-rest,quarkus-rest-jackson,org.apache.camel.quarkus:camel-quarkus-core:3.15.0,quarkus-jdbc-mariadb,io.quarkus:quarkus-hibernate-orm,quarkus-smallrye-openapi,rest-client-jackson,org.apache.camel.quarkus:camel-quarkus-jacksonxml:3.15.0,org.apache.camel.quarkus:camel-quarkus-sjms2:3.15.0,org.apache.camel.quarkus:camel-quarkus-file:3.15.0,org.apache.camel.quarkus:camel-quarkus-direct:3.15.0,org.apache.camel.quarkus:camel-quarkus-jackson:3.15.0,org.apache.camel.quarkus:camel-quarkus-csv:3.15.0,org.apache.camel.quarkus:camel-quarkus-bean:3.15.0,io.quarkiverse.artemis:quarkus-artemis-jms:3.6.0 --java 21 -c quarkus.http.port=8081 --no-code

cd invoice-service
mkdir -p src/main/java/fr/pantheonsorbonne/dto
mkdir -p src/main/java/fr/pantheonsorbonne/entity
mkdir -p src/main/java/fr/pantheonsorbonne/dao
mkdir -p src/main/java/fr/pantheonsorbonne/service
mkdir -p src/main/java/fr/pantheonsorbonne/resources
mkdir -p src/main/java/fr/pantheonsorbonne/gateway
mkdir -p src/main/java/fr/pantheonsorbonne/camel
mkdir -p src/main/java/fr/pantheonsorbonne/exception
cd ..

quarkus create app fr.pantheonsorbonne:accountancy-service:1.0-SNAPSHOT -P io.quarkus:quarkus-bom:3.15.2 -x quarkus-rest,quarkus-rest-jackson,org.apache.camel.quarkus:camel-quarkus-core:3.15.0,quarkus-jdbc-mariadb,io.quarkus:quarkus-hibernate-orm,quarkus-smallrye-openapi,rest-client-jackson,org.apache.camel.quarkus:camel-quarkus-jacksonxml:3.15.0,org.apache.camel.quarkus:camel-quarkus-sjms2:3.15.0,org.apache.camel.quarkus:camel-quarkus-file:3.15.0,org.apache.camel.quarkus:camel-quarkus-direct:3.15.0,org.apache.camel.quarkus:camel-quarkus-jackson:3.15.0,org.apache.camel.quarkus:camel-quarkus-csv:3.15.0,org.apache.camel.quarkus:camel-quarkus-bean:3.15.0,io.quarkiverse.artemis:quarkus-artemis-jms:3.6.0 --java 21 -c quarkus.http.port=8082 --no-code

cd accountancy-service
mkdir -p src/main/java/fr/pantheonsorbonne/dto
mkdir -p src/main/java/fr/pantheonsorbonne/entity
mkdir -p src/main/java/fr/pantheonsorbonne/dao
mkdir -p src/main/java/fr/pantheonsorbonne/service
mkdir -p src/main/java/fr/pantheonsorbonne/resources
mkdir -p src/main/java/fr/pantheonsorbonne/gateway
mkdir -p src/main/java/fr/pantheonsorbonne/camel
mkdir -p src/main/java/fr/pantheonsorbonne/exception
cd ..
```
