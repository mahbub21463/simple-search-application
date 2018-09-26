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
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class ProgrammingLanguageService {
    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;
    
    @Autowired
    DeveloperRepository developerRepository;

    public Iterable<ProgrammingLanguage> findAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return programmingLanguageRepository.findAll();
    }

    public List<ProgrammingLanguage> searchNotUsedByDevelopers() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Iterable<Developer> developerList = developerRepository.findAll();
       List<ProgrammingLanguage> usedProgrammingLanguages =  programmingLanguageRepository.findDistinctByDevelopersIn(developerList);
       List<ProgrammingLanguage> allProgrammingLanguages = (List<ProgrammingLanguage>) programmingLanguageRepository.findAll();
       allProgrammingLanguages.removeAll(usedProgrammingLanguages);
       return allProgrammingLanguages;
    }

    public ProgrammingLanguage findOne(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Optional<ProgrammingLanguage> programmingLanguage = programmingLanguageRepository.findById(id);
       if(programmingLanguage.isPresent())
       {
           return programmingLanguage.get();
       }
       else{
           throw new ResourceNotFoundException("Programming Language not found with id - " + id);
         
       }
    }

    public ProgrammingLanguage addLanguage(ProgrammingLanguage programmingLanguage) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        programmingLanguage = programmingLanguageRepository.save(programmingLanguage);
       return programmingLanguage;
    }

    public ProgrammingLanguage editLanguage(ProgrammingLanguage programmingLanguage) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         programmingLanguage = programmingLanguageRepository.save(programmingLanguage);
         return programmingLanguage;
    }

    public void deleteProgrammingLanguage(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Optional<ProgrammingLanguage> programmingLanguage = programmingLanguageRepository.findById(id);
       if(!programmingLanguage.isPresent())
       {
           throw new ResourceNotFoundException("Programming Language not found with id - " + id);
          
       }
      
       
       programmingLanguageRepository.deleteById(id);
    }
}
