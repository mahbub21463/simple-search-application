/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchapp.services;

import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.ProgrammingLanguageRepository;
import com.searchapp.exceptions.ResourceNotFoundException;
import com.searchapp.models.Developer;
import com.searchapp.models.ProgrammingLanguage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class DeveloperService {
    @Autowired
    DeveloperRepository developerRepository;
    
    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;
    
    public Iterable<Developer> findAll()
    {
        return developerRepository.findAll();
    }

    public Iterable<Developer> searchWhoWriteTwoProgrammingLanguageSpeakOneLanguage(String language, String[] programmingLanguages) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public Iterable<Developer> search(String[] languages, String[] programmingLanguages) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public Developer findOne(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Optional<Developer> developer = developerRepository.findById(id);
       if(developer.isPresent())
       {
           return developer.get();
       }
       else{
           throw new ResourceNotFoundException("Developer not found with id - " + id);
           
       }
    }

    public Developer addDeveloper(Developer developer) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Developer newDeveloper = new Developer(developer.getEmail());
       newDeveloper = developerRepository.save(newDeveloper);
       newDeveloper.setLanguages(developer.getLanguages());
       newDeveloper.setProgrammingLanguages(developer.getProgrammingLanguages());
       newDeveloper = developerRepository.save(newDeveloper);
       return newDeveloper;
    }

    public Developer editDeveloper(Developer developer) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        developer = developerRepository.save(developer);
       return developer;
    }

    public void deleteDeveloper(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Optional<Developer> developer = developerRepository.findById(id);
       if(!developer.isPresent())
       {
           throw new ResourceNotFoundException("Developer not found with id - " + id);
          
       }
     
       developerRepository.deleteById(id);
    }
}
