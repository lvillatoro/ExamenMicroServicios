package com.example.examenmicro.services;


import java.io.InputStream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class HandlerService implements RequestHandler<S3Event, String>{

    @Autowired
    UploadCSVService fileService;

    @Override
    public String handleRequest(S3Event s3event, Context context) {
        try{

            S3EventNotificationRecord record = s3event.getRecords().get(0);

            String srcBucket = record.getS3().getBucket().getName();

            // Object key may have spaces or unicode non-ASCII characters.
            String srcCSVFile = record.getS3().getObject().getUrlDecodedKey();


            // Download the image from S3 into a stream
            AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
            S3Object s3Object = s3Client.getObject(new GetObjectRequest(
                    srcBucket, srcCSVFile));
            InputStream objectData = s3Object.getObjectContent().getDelegateStream();
            
            fileService.saveDataDynamo(objectData);
            return "Archivo leido";

        }catch(Exception e){
            System.out.println("Error");
        }
        return null;
    }
    
}
