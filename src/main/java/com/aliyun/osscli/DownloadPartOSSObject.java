package com.aliyun.osscli;

import com.aliyun.oss.*;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.DownloadFileResult;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.IOException;

public class DownloadPartOSSObject {
    private static String endpoint = "<endpoint, http://oss-cn-hangzhou.aliyuncs.com>";
    private static String accessKeyId = "<accessKeyId>";
    private static String accessKeySecret = "<accessKeySecret>";
    private static String bucketName = "<bucketName>";
    private static String key = "<downloadKey>";
    private static String downloadFile = "<downloadFile>";
    private static long start = 0;
    private static long end = -1;


    public static void main(String[] args) throws IOException {

        endpoint = System.getenv("ENDPOINT");
        accessKeyId = System.getenv("KEY_ID");
        accessKeySecret = System.getenv("KEY_SECRET");


        if (args.length < 2){
            System.out.println("Please input the bucket name and key.");
            return;
        }

        bucketName=args[0];
        key=args[1];

        try{
            start=Integer.parseInt(args[2]);
            System.out.println("Use start: " + start);
        }catch(Throwable e){
            System.out.println("Use default start: 0");
        }

        try{
            end=Integer.parseInt(args[3]);
            System.out.println("Use end: " + end);
        }catch(Throwable e){
            System.out.println("Use default end: -1");
        }

        int num = 5;

        try{
            num=Integer.parseInt(args[4]);
            System.out.println("Use num: " + num);
        }catch(Throwable e){
            System.out.println("Use default num 5");
        }

        long partSize = 1;

        try{
            partSize=Long.parseLong(args[5]);
            System.out.println("Use part size: " + partSize);
        }catch(Throwable e){
            System.out.println("Use default part size: 64MB");
        }

        ClientBuilderConfiguration config = new ClientBuilderConfiguration();
        config.setCrcCheckEnabled(false);

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, config);



        try {
            DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, key);
            // Sets the local file to download to
            downloadFileRequest.setDownloadFile(downloadFile);
            // Sets the concurrent task thread count 5. By default it's 1.
            downloadFileRequest.setTaskNum(num);
            // Sets the part size, by default it's 100K.
            downloadFileRequest.setPartSize(1024 * 1024 * partSize);

            downloadFileRequest.setRange(start, end);
            // Set the download file
            downloadFileRequest.setDownloadFile(key);
            // Enable checkpoint. By default it's false.
            downloadFileRequest.setEnableCheckpoint(false);

            long start = System.currentTimeMillis();
            DownloadFileResult downloadResult = ossClient.downloadFile(downloadFileRequest);
            System.out.println("Download time in ms = "+(System.currentTimeMillis()-start));

            ObjectMetadata objectMetadata = downloadResult.getObjectMetadata();
            System.out.println(objectMetadata.getETag());
            System.out.println(objectMetadata.getLastModified());
            System.out.println(objectMetadata.getUserMetadata().get("meta"));

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
}
