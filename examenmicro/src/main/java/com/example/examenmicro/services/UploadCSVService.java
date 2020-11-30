package com.example.examenmicro.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.examenmicro.CSVHelper;
import com.example.examenmicro.dao.AutoDao;
import com.example.examenmicro.models.Auto;
import com.example.examenmicro.repositories.AutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadCSVService {
    
    @Autowired
    AutoRepository repository;

    @Autowired
    AutoDao autoDao;
    
    public void saveData(MultipartFile file){
        try{
            List<Auto> autos = CSVHelper.csvToAutos(file.getInputStream());
            repository.saveAll(autos);
            System.out.println("Autos: " + autos);

        }catch(IOException e){
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void saveDataDynamo(InputStream file){
        try {
            List<Auto> autos = CSVHelper.csvToAutos(file);
            autoDao.createListAuto(autos);
        } catch (Exception e) {
            System.out.println("ERROR AL GUARDAR EN DYNAMO");
        }
    }
}
