package com.searchapp.controllers;

import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.ProgrammingLanguageRepository;
import com.searchapp.exceptions.ErrorDetails;
import com.searchapp.exceptions.ResourceNotFoundException;
import com.searchapp.models.Developer;
import com.searchapp.models.Language;
import com.searchapp.models.ProgrammingLanguage;
import com.searchapp.services.DeveloperService;
import java.util.ArrayList;
import java.util.Arrays;
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
    DeveloperService developerService;
    
   @GetMapping()
   public Iterable<Developer> findAll() {
       return developerService.findAll();
   }
   @GetMapping("/search-who-write-two-programming-language-speak-one-language")
   public Iterable<Developer> searchWhoWriteTwoProgrammingLanguageSpeakOneLanguage(@RequestParam(value="language") String language, @RequestParam(value="programmingLanguage") String[] programmingLanguages)
   {
       
       if(language == null || programmingLanguages == null || programmingLanguages.length<2)
       {
           throw new RuntimeException("Invalid parameters");
       }
       return developerService.searchWhoWriteTwoProgrammingLanguageSpeakOneLanguage(language, programmingLanguages);
       
   }
   @GetMapping("/search")
   public Iterable<Developer> search(@RequestParam(value="language", required=false) String[] languages, @RequestParam(value="programmingLanguage", required=false) String[] programmingLanguages)
   {
       return developerService.search(languages, programmingLanguages);
       
       
   }

   @GetMapping("/{id}")
   public Developer findOne(@PathVariable("id") Integer id) {
       return developerService.findOne(id);
       
   }
   
   @PostMapping("/add")
   public Developer postDeveloper(@RequestBody Developer developer){
       return developerService.addDeveloper(developer);
       
   }
   @PutMapping("/edit")
   public Developer putDeveloper(@RequestBody Developer developer){
       return developerService.editDeveloper(developer);
       
   }
   @DeleteMapping("/{id}")
   public void deleteDeveloper(@PathVariable Integer id)
   {
       developerService.deleteDeveloper(id);
       
       
   }
    
}