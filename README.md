
## Setup

1.创建项目

```
mvn archetype:generate -DgroupId=com.aliyun.osscli -DartifactId=osscli -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

2.编译和打包

```
mvn compile
mvn package
```

3.测试Hello

```
java -cp osscli-1.0-SNAPSHOT.jar com.aliyun.osscli.App
Hello World!
```

4.测试访问oss

···

···

