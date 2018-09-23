package com.searchapp.controllers;

import com.searchapp.dao.DeveloperRepository;
import com.searchapp.exceptions.ResourceNotFoundException;
import com.searchapp.models.Developer;
import com.searchapp.models.Language;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    @Autowired
    DeveloperRepository developerRepository;
    
   @GetMapping()
   public Iterable<Developer> findAll() {
       return developerRepository.findAll();
   }
   @PostMapping("/search")
   public Iterable<Developer> search(@RequestBody Developer criteria)
   {
       
       return developerRepository.findByLanguages(criteria.getLanguages());
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
   
   @PostMapping("/add")
   public Developer postDeveloper(@RequestBody Developer developer){
       Developer newDeveloper = new Developer(developer.getEmail());
       newDeveloper = developerRepository.save(newDeveloper);
       newDeveloper.setLanguages(developer.getLanguages());
       newDeveloper.setProgrammingLanguages(developer.getProgrammingLanguages());
       newDeveloper = developerRepository.save(newDeveloper);
       return newDeveloper;
   }
   @PutMapping("/edit")
   public Developer putDeveloper(@RequestBody Developer developer){
       developer = developerRepository.save(developer);
       return developer;
   }
   @DeleteMapping("/{id}")
   public void deleteDeveloper(@PathVariable Integer id)
   {
       developerRepository.deleteById(id);
   }
    
}