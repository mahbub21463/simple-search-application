package com.searchapp.controllers;

import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.ProgrammingLanguageRepository;
import com.searchapp.exceptions.ErrorDetails;
import com.searchapp.exceptions.ResourceNotFoundException;
import com.searchapp.models.Developer;
import com.searchapp.models.Language;
import com.searchapp.models.ProgrammingLanguage;
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
    DeveloperRepository developerRepository;
    
    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;
    
   @GetMapping()
   public Iterable<Developer> findAll() {
       return developerRepository.findAll();
   }
   @GetMapping("/search-who-write-two-programming-language-speak-one-language")
   public Iterable<Developer> searchWhoWriteTwoProgrammingLanguageSpeakOneLanguage(@RequestParam(value="language") String language, @RequestParam(value="programmingLanguage") String[] programmingLanguages)
   {
       if(language == null || programmingLanguages == null || programmingLanguages.length<2)
       {
           throw new RuntimeException("Invalid parameters");
       }
       List<Developer> developers = developerRepository.findByLanguages_CodeAndProgrammingLanguages_Name(language, programmingLanguages[0]);
       List<Developer> resultList = new ArrayList<>();
       ProgrammingLanguage programmingLanguage = programmingLanguageRepository.findByName(programmingLanguages[1]);
       if(programmingLanguage == null)
       {
           throw new RuntimeException("Invalid parameters");
       }
       for(Developer developer : developers)
       {
           if(developer.getProgrammingLanguages().contains(programmingLanguage))
           {
               resultList.add(developer);
           }
       }
       return resultList;
   }
   @GetMapping("/search")
   public Iterable<Developer> search(@RequestParam(value="language", required=false) String[] languages, @RequestParam(value="programmingLanguage", required=false) String[] programmingLanguages)
   {
       if(languages == null && programmingLanguages == null)
       {
           return null;
       }
       else if(languages == null)
       {
           return developerRepository.findByProgrammingLanguages_NameIn(Arrays.asList(programmingLanguages));
       }
       else if(programmingLanguages == null)
       {
                     
           return developerRepository.findByLanguages_CodeIn(Arrays.asList(languages));
 
       }
       else
       {
                  
           return developerRepository.findByLanguages_CodeInAndProgrammingLanguages_NameIn(Arrays.asList(languages), Arrays.asList(programmingLanguages));

       }
       
   }
//   @PostMapping("/search")
//   public Iterable<Developer> search(@RequestBody Developer criteria)
//   {
//       
//       return developerRepository.findByLanguages(criteria.getLanguages());
//   }
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
       Optional<Developer> developer = developerRepository.findById(id);
       if(!developer.isPresent())
       {
           throw new ResourceNotFoundException("Developer not found with id - " + id);
          
       }
     
       developerRepository.deleteById(id);
       
   }
    
}