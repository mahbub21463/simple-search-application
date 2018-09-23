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
    
    public List<Developer> findByLanguagesIn(Set<Language> languages);
//    public List<Developer> searchDevelopers(List<Language> languages, List<ProgrammingLanguage> programmingLanguages) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//CriteriaQuery<Profile> criteria = cb.createQuery(Profile.class);
//Root<Profile> root = criteria.from(Profile.class);
//
//// Profile_.categories is a collection of Category objects
//// categories is collection of Long (category ids) that i want to search in
//// but is not working
//criteria.where(root.get(Profile_.categories).in(categories))
//
//List<Profile> results = em.createQuery(criteria).getResultList();

    
}
