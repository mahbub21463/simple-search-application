/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchapp.dao;

import com.searchapp.models.ProgrammingLanguage;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author user
 */
public interface ProgrammingLanguageRepository extends CrudRepository<ProgrammingLanguage, Integer> {
    
}
