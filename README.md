
## Setup

1. Setup

```
mvn archetype:generate -DgroupId=com.aliyun.osscli -DartifactId=osscli -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

2. Build package

```
mvn clean
mvn compile
mvn assembly:assembly
mvn package
mvn  -T 2C clean install
```

3. Download jar

```
wget http://kubeflow.oss-cn-beijing.aliyuncs.com/osscli-1.0-SNAPSHOT-jar-with-dependencies.jar
```

4. Expose environment variables

```
export ENDPOINT=oss-cn-huhehaote-internal.aliyuncs.com
export KEY_ID=key
export KEY_SECRET=secret
```

4. Test Hello world

```
java -cp osscli-1.0-SNAPSHOT.jar com.aliyun.osscli.App
Hello World!
```

5. Test OSS client to get Object in a single thread

``` 
java -jar osscli-1.0-SNAPSHOT-jar-with-dependencies.jar imagenet-huabei5 images/train-00760-of-01024
Download the metadata: false
Getting Started with OSS SDK for Java

Downloading an object
Download time in ms = 196
Content-Type: application/octet-stream
Size: 136977679
```

6. Test OSS client to download Object in multiple threads

``` 
java -cp osscli-1.0-SNAPSHOT-jar-with-dependencies.jar com.aliyun.osscli.DownloadOSSObject imagenet-huabei5 images/train-00760-of-01024
Use default num 5
Use default part size: 64MB
Download time in ms = 974
D8F69CE8F96710BB6FB620B99D17E717-54
Sun Oct 06 23:26:17 GMT 2019
null


java -cp osscli-1.0-SNAPSHOT-jar-with-dependencies.jar com.aliyun.osscli.DownloadOSSObject imagenet-huabei5 images/train-00760-of-01024 8 16
Use num: 8
Use part size: 16
Download time in ms = 455
D8F69CE8F96710BB6FB620B99D17E717-54
Sun Oct 06 23:26:17 GMT 2019
null
```

