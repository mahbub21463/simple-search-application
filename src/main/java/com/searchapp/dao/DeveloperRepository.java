/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchapp.dao;

import com.searchapp.models.Developer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("developerRepository")
public interface DeveloperRepository extends CrudRepository<Developer, Integer> {
    
}
