/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.searchapp.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(
            mappedBy = "developer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Interview> interviewList = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(name = "developer_languages",
            joinColumns = {
                @JoinColumn(name = "developer_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "language_id")})
    private Set<Language> languages = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(name = "developer_programming_languages",
            joinColumns = {
                @JoinColumn(name = "developer_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "programming_language_id")})
    private Set<ProgrammingLanguage> programmingLanguages = new HashSet<>();

    public Developer(){
        
    }
    public Developer(String email)
    {
        this.email = email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Interview> getInterviewList() {
        return interviewList;
    }

    public void setInterviewList(Set<Interview> interviewList) {
        this.interviewList = interviewList;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<ProgrammingLanguage> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(Set<ProgrammingLanguage> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }
    
    

}
