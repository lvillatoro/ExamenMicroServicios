package com.example.examenmicro;

import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.example.examenmicro.models.Auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppServiceStart implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
	AmazonDynamoDB amazonDynamoDB;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event){
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        List<String> result = amazonDynamoDB.listTables().getTableNames();

        if(!result.contains("repoxls")){
            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Auto.class);
            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            amazonDynamoDB.createTable(tableRequest);
        }
    }
}