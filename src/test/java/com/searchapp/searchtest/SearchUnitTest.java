package com.searchapp.searchtest;


import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.LanguageRepository;
import com.searchapp.dao.ProgrammingLanguageRepository;
import com.searchapp.models.Developer;
import com.searchapp.models.Language;
import com.searchapp.models.ProgrammingLanguage;
import com.searchapp.seeders.DatabaseSeeder;
import com.searchapp.services.DeveloperService;
import com.searchapp.services.ProgrammingLanguageService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class SearchUnitTest {
 

    
    @TestConfiguration
    static class SearchUnitTestContextConfiguration {
  
        @Bean
        public DeveloperService developerService() {
            return new DeveloperService();
        }
        
        @Bean
        public ProgrammingLanguageService programmingLanguageService() {
            return new ProgrammingLanguageService();
        }
        
 
    }
    
    @Autowired
    private DeveloperService developerService;
 
    
    @Autowired
    private ProgrammingLanguageService programmingLanguageService;
    
 
    @Test
    public void searchWhoWriteRubyTest()
    {
     
        String[] languages = new String[]{};
        String[] programmingLanguages = new String[]{"ruby"};
        Iterable<Developer> developers = developerService.search(languages, programmingLanguages);
        for(Developer d: developers)
        {
            
            assertThat(getProgrammingLanguageNames(d.getProgrammingLanguages())).contains("ruby");
        }
    }
    private List<String> getProgrammingLanguageNames(Set<ProgrammingLanguage> languageSet)
    {
        List<String> results = new ArrayList<>();
        languageSet.forEach(language -> results.add(language.getName()));
        return results;
    }
    private List<String> getLanguageCodes(Set<Language> languageSet)
    {
        List<String> results = new ArrayList<>();
        languageSet.forEach(language -> results.add(language.getCode()));
        return results;
    }
    
    @Test
    public void searchWhoWriteRubySpeakJapanesetest()
    {
        String[] languages = new String[]{"ja"};
        String[] programmingLanguages = new String[]{"ruby"};
        Iterable<Developer> developers = developerService.search(languages, programmingLanguages);
        for(Developer d: developers)
        {
            
            assertThat(getProgrammingLanguageNames(d.getProgrammingLanguages())).contains("ruby");
            assertThat(getLanguageCodes(d.getLanguages())).contains("ja");
        }
    }
    
    @Test
    public void searchWhoWriteRubyAndJavascriptSpeakJapaneseTest()
    {
        String language = "ja";
        String[] programmingLanguages = new String[]{"ruby", "JavaScript"};
        Iterable<Developer> developers = developerService.searchWhoWriteTwoProgrammingLanguageSpeakOneLanguage(language, programmingLanguages);
        for(Developer d: developers)
        {
            
            assertThat(getProgrammingLanguageNames(d.getProgrammingLanguages())).contains("ruby", "JavaScript");
            assertThat(getLanguageCodes(d.getLanguages())).contains("ja");
        }
    }
    
    @Test
    public void searchNotUsedProgrammingLanguagesTest(){
         List<ProgrammingLanguage> notUsedList = programmingLanguageService.searchNotUsedByDevelopers();
         String[] notUsedLanguageNames = new String[notUsedList.size()];
         for(int i=0;i<notUsedList.size();i++)
         {
             notUsedLanguageNames[i]= notUsedList.get(i).getName();
         }
         Iterable<Developer> developerList = developerService.findAll();
         
         for(Developer d: developerList)
        {
            if(notUsedLanguageNames.length>0)
            {
                assertThat(getProgrammingLanguageNames(d.getProgrammingLanguages())).doesNotContain(notUsedLanguageNames);
            }
            
            
        }
    }
    
    
 
}