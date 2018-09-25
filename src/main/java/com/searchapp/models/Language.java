/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchapp.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author user
 */
@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToMany(fetch = FetchType.LAZY,
           
            mappedBy = "languages")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Developer> developers = new HashSet<>();
//    
    public Language(){
    }
    public Language(String code){
        this.code = code;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public Set<Developer> getDevelopers() {
//        return developers;
//    }
//
//    public void setDevelopers(Set<Developer> developers) {
//        this.developers = developers;
//    }
//    
    

}
