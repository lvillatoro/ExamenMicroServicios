package com.example.examenmicro.dao;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.examenmicro.models.Auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AutoDao {
    
    
    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    

    public Auto getAuto(String autoId) {
        return dynamoDBMapper.load(Auto.class, autoId);
    }

    public Auto createAuto(Auto auto) {
        dynamoDBMapper.save(auto);
        return auto;
    }

    public String createListAuto(List<Auto> autos){
        dynamoDBMapper.batchSave(autos);
        return "LISTA GUARDADA";
    }
}
