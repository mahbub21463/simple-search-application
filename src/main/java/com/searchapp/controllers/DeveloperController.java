package com.searchapp.controllers;

import com.searchapp.dao.DeveloperRepository;
import com.searchapp.exceptions.ResourceNotFoundException;
import com.searchapp.models.Developer;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    @Autowired
    DeveloperRepository developerRepository;
    
   @GetMapping()
   public Iterable<Developer> findAll() {
       return developerRepository.findAll();
   }
   @GetMapping("/{id}")
   public Developer findOne(@PathVariable("id") Integer id) {
       Optional<Developer> developer = developerRepository.findById(id);
       if(developer.isPresent())
       {
           return developer.get();
       }
       else{
           throw new ResourceNotFoundException("Developer not found with id - " + id);
       }
   }
    
}