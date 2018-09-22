package com.searchapp.seeders;

import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.LanguageRepository;
import com.searchapp.dao.ProgrammingLanguageRepository;
import com.searchapp.models.Developer;
import com.searchapp.models.Language;
import com.searchapp.models.ProgrammingLanguage;
import com.searchapp.service.BeanUtil;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;


public class DatabaseSeeder {

    DeveloperRepository developerRepository =BeanUtil.getBean(DeveloperRepository.class);
    LanguageRepository languageRepository =BeanUtil.getBean(LanguageRepository.class);
    ProgrammingLanguageRepository programmingLanguageRepository =BeanUtil.getBean(ProgrammingLanguageRepository.class);

    

//    @Autowired
//    public DatabaseSeeder(
//            DeveloperRepository developerRepository,
//            LanguageRepository languageRepository,
//            ProgrammingLanguageRepository programmingLanguageRepository) {
//        this.developerRepository = developerRepository;
//        this.languageRepository = languageRepository;
//        this.programmingLanguageRepository = programmingLanguageRepository;
//    }

    public void seed() {
        languageRepository.deleteAll();
        programmingLanguageRepository.deleteAll();
        developerRepository.deleteAll();
        List<Language> languageList = seedLanguagesTable();
        List<ProgrammingLanguage> programmingLanguageList = seedProgrammingLanguagesTable();
        seedDevelopersTable(100, languageList, programmingLanguageList);
       
    }

    private void seedDevelopersTable(int recordCount, List<Language> languageList, List<ProgrammingLanguage> programmingLanguageList) {
        List<Developer> developerList = getDeveloperList(recordCount);
        Random random = new Random();
        for(Developer developer: developerList)
        {
            List<Language> languages = new ArrayList<>();
            int languageIndex = random.nextInt(languageList.size());
            languages.add(languageList.get(languageIndex));
            languageIndex = random.nextInt(languageList.size());
            languages.add(languageList.get(languageIndex));
            
            List<ProgrammingLanguage> programmingLanguages = new ArrayList<>();
            int programmingLanguageIndex = random.nextInt(programmingLanguageList.size());
            programmingLanguages.add(programmingLanguageList.get(programmingLanguageIndex));
            programmingLanguageIndex = random.nextInt(programmingLanguageList.size());
            programmingLanguages.add(programmingLanguageList.get(programmingLanguageIndex));
            programmingLanguageIndex = random.nextInt(programmingLanguageList.size());
            programmingLanguages.add(programmingLanguageList.get(programmingLanguageIndex));
            
            
            developer = developerRepository.save(developer);
            developer.getLanguages().addAll(languages);
            developer.getProgrammingLanguages().addAll(programmingLanguages);
            developerRepository.save(developer);
        }
       
    }

    private List<Language> seedLanguagesTable() {
        List<Language> languageList = getLanguageList();
        List<Language> storedLanguageList = new ArrayList<>();
        for(Language language: languageList)
        {
            language = languageRepository.save(language);
            storedLanguageList.add(language);
        }
        return storedLanguageList;  
           
    }

    private List<ProgrammingLanguage> seedProgrammingLanguagesTable() {
        List<ProgrammingLanguage> programmingLanguageList = getProgrammingLanguageList();
        
        List<ProgrammingLanguage> storedProgrammingLanguageList = new ArrayList<>();
        for(ProgrammingLanguage programmingLanguage: programmingLanguageList)
        {
            programmingLanguage = programmingLanguageRepository.save(programmingLanguage);
            storedProgrammingLanguageList.add(programmingLanguage);
        }
        return storedProgrammingLanguageList;
    }

    private List<Developer> getDeveloperList(int recordCount) {
        List<Developer> developerList = new ArrayList<>();
        for (int i=0;i<recordCount;i++)
        {
            String email = RandomStringUtils.randomAlphabetic(6)+"@gmail.com";
            developerList.add(new Developer(email));
            
        }
        return developerList;
    }

    private List<Language> getLanguageList() {
        String [] languages = {"bd", "vn", "en", "ja"};
        List<Language> languageList = new ArrayList<>();
        for(int i=0; i<languages.length;i++)
        {
            languageList.add(new Language(languages[i]));
        }
        return languageList;
    }

    private List<ProgrammingLanguage> getProgrammingLanguageList() {
        String [] programmingLanguages = {"php", "ruby", "JavaScript", "python", "Scala", "kotlin", "swift"};
        List<ProgrammingLanguage> programmingLanguageList = new ArrayList<>();
        for(int i=0; i<programmingLanguages.length;i++)
        {
            programmingLanguageList.add(new ProgrammingLanguage(programmingLanguages[i]));
        }
        return programmingLanguageList;
    }

}
