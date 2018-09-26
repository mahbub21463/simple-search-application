package com.searchapp.controllers;

import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.LanguageRepository;
import com.searchapp.dao.ProgrammingLanguageRepository;
import com.searchapp.exceptions.ResourceNotFoundException;
import com.searchapp.models.Developer;
import com.searchapp.models.Language;
import com.searchapp.models.ProgrammingLanguage;
import com.searchapp.services.ProgrammingLanguageService;
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
@RequestMapping("/api/programming-languages")
public class ProgrammingLanguageController {

    @Autowired
    ProgrammingLanguageService programmingLanguageService;
    
    
    
   @GetMapping()
   public Iterable<ProgrammingLanguage> findAll() {
       return programmingLanguageService.findAll();
   }
   @GetMapping("/search-not-used-programming-languages")
   public List<ProgrammingLanguage> searchNotUsedByDevelopers()
   {
       return programmingLanguageService.searchNotUsedByDevelopers();
       
   
   }
   @GetMapping("/{id}")
   public ProgrammingLanguage findOne(@PathVariable("id") Integer id) {
       return programmingLanguageService.findOne(id);
       
   }
   
   @PostMapping("/add")
   public ProgrammingLanguage postLanguage(@RequestBody ProgrammingLanguage programmingLanguage){
       return programmingLanguageService.addLanguage(programmingLanguage);
       
   }
   @PutMapping("/edit")
   public ProgrammingLanguage putLanguage(@RequestBody ProgrammingLanguage programmingLanguage){
       return programmingLanguageService.editLanguage(programmingLanguage);
      
   }
   
   @DeleteMapping("/{id}")
   public void deleteProgrammingLanguage(@PathVariable Integer id)
   {
       programmingLanguageService.deleteProgrammingLanguage(id);
       
   }
    
}