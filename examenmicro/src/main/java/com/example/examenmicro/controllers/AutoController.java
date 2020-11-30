package com.example.examenmicro.controllers;

import java.util.List;
import java.util.Optional;

import com.example.examenmicro.models.Auto;
import com.example.examenmicro.repositories.AutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auto")
public class AutoController {
    
    @Autowired
    AutoRepository repository;

    @GetMapping
    public List<Auto> getAllAutos(){
        return this.repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Auto> getAuto(@PathVariable Integer id){
        return this.repository.findById(id);
    }

    @PostMapping()
    public Object addAuto(@RequestBody Auto entity) {
        
        return this.repository.save(entity);
    }
    
}
