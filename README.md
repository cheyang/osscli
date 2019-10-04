
## Setup

1. Setup

```
mvn archetype:generate -DgroupId=com.aliyun.osscli -DartifactId=osscli -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

2. Build package

```
mvn compile
mvn assembly:assembly
mvn package
mvn  -T 2C clean install
```

3.Test Hello world

```
java -cp osscli-1.0-SNAPSHOT.jar com.aliyun.osscli.App
Hello World!
```

4.Test OSS client

···
export 
java -jar osscli-1.0-SNAPSHOT-jar-with-dependencies.jar
···

