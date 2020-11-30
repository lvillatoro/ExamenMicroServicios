package com.example.examenmicro.controllers;

import com.example.examenmicro.CSVHelper;
import com.example.examenmicro.services.UploadCSVService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/csv")
public class UploadCSVController {
    
    @Autowired
    UploadCSVService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.saveData(file);

                message = "Archivo cargado correctamente" + file.getOriginalFilename();

                return ResponseEntity.status(HttpStatus.OK).body(message);

            } catch (Exception e) {
                message = "No se pudo leer el archivo: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Tipo de archivo incorrecto");
        
    }

    @PostMapping("/upload-to-dynamo")
    public ResponseEntity<Object> uploadToDynamo(@RequestParam("file") MultipartFile file){
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                //fileService.saveData(file);
                
                fileService.saveDataDynamo(file.getInputStream());
                message = "Archivo cargado correctamente" + file.getOriginalFilename();

                return ResponseEntity.status(HttpStatus.OK).body(message);

            } catch (Exception e) {
                message = "No se pudo leer el archivo: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Tipo de archivo incorrecto");

    }
}
