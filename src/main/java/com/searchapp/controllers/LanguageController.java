package com.searchapp.controllers;

import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.LanguageRepository;
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
@RequestMapping("/api/languages")
public class LanguageController {

    @Autowired
    LanguageRepository languageRepository;
    
   @GetMapping()
   public Iterable<Language> findAll() {
       return languageRepository.findAll();
   }
   @GetMapping("/{id}")
   public Language findOne(@PathVariable("id") Integer id) {
       Optional<Language> language = languageRepository.findById(id);
       if(language.isPresent())
       {
           return language.get();
       }
       else{
           throw new ResourceNotFoundException("Language not found with id - " + id);
       }
   }
   @PostMapping("/add")
   public Language postLanguage(@RequestBody Language language){
       language = languageRepository.save(language);
       return language;
   }
   @PutMapping("/edit")
   public Language putLanguage(@RequestBody Language language){
       language = languageRepository.save(language);
       return language;
   }
   @DeleteMapping("/{id}")
   public void deleteLanguage(@PathVariable Integer id)
   {
       languageRepository.deleteById(id);
   }
    
}