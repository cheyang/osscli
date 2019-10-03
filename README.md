
## Setup

1.

```
mvn archetype:generate -DgroupId=com.aliyun.osscli -DartifactId=osscli -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

2.

```
mvn package
```

3.

```
java -cp osscli-1.0-SNAPSHOT.jar com.aliyun.osscli.App
Hello World!
```

