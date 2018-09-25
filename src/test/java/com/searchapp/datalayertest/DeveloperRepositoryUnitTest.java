package com.searchapp.datalayertest;


import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.LanguageRepository;
import com.searchapp.dao.ProgrammingLanguageRepository;
import com.searchapp.models.Developer;
import com.searchapp.models.Language;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeveloperRepositoryUnitTest {
 
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private DeveloperRepository developerRepository;
 
//    @Autowired
//    private LanguageRepository languageRepository;
//    
//    @Autowired
//    private ProgrammingLanguageRepository programmingLanguageRepository;
 
    @Test
    public void whenFindByLanguageCode_thenReturnDeveloperList()
    {
        
    }
    
    @Test
    public void whenFindByProgrammingLanguageName_thenReturnDeveloperList()
    {
        
    }
    
    @Test
    public void whenFindByLanguageCodeAndProgrammingLanguageName_thenReturnDeveloperList()
    {
        
    }
    
    @Test
    public void whenFindByEmail_thenReturnDeveloper() {
        // given
        Developer developer = new Developer("alam.mahbub214@gmail.com");
        entityManager.persist(developer);
        entityManager.flush();

        // when
        Developer found = developerRepository.findByEmail(developer.getEmail());

        // then
        assertThat(found.getEmail()).isEqualTo(developer.getEmail());
    }
 
}