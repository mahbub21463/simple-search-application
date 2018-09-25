/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchapp.dao;

import com.searchapp.models.Developer;
import com.searchapp.models.Language;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("developerRepository")
public interface DeveloperRepository extends CrudRepository<Developer, Integer> {
    
    public Developer findByEmail(String email);
    public List<Developer> findByLanguages_CodeAndProgrammingLanguages_Name(String code, String name);
    public List<Developer> findByLanguages_CodeIn(List<String> codes);
    public List<Developer> findByProgrammingLanguages_NameIn(List<String> names);
    public List<Developer> findByLanguages_CodeInAndProgrammingLanguages_NameIn(List<String> codes, List<String> names);

    
}
