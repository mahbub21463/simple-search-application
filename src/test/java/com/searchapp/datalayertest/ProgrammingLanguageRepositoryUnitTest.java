package com.searchapp.datalayertest;


import com.searchapp.dao.LanguageRepository;
import com.searchapp.dao.ProgrammingLanguageRepository;
import com.searchapp.models.Language;
import com.searchapp.models.ProgrammingLanguage;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ProgrammingLanguageRepositoryUnitTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private ProgrammingLanguageRepository programmingLanguageRepository;
 
    
    @Test
    public void whenFindByName_thenReturnProgrammingLanguage() {
        // given
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage("Java");
        entityManager.persist(programmingLanguage);
        entityManager.flush();

        // when
        ProgrammingLanguage found = programmingLanguageRepository.findByName(programmingLanguage.getName());

        // then
        assertThat(found.getName()).isEqualTo(programmingLanguage.getName());
    }
 
}