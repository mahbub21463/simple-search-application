package com.searchapp.datalayertest;


import com.searchapp.dao.LanguageRepository;
import com.searchapp.models.Language;
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
public class LanguageRepositoryUnitTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private LanguageRepository languageRepository;
 
    
    @Test
    public void whenFindByCode_thenReturnLanguage() {
        // given
        languageRepository.deleteAll();
        Language language = new Language("en");
        entityManager.persist(language);
        entityManager.flush();
        // when
        Language found = languageRepository.findByCode(language.getCode());

        // then
        assertThat(found.getCode()).isEqualTo(language.getCode());
    }
 
}