package com.searchapp.datalayertest;


import com.searchapp.dao.LanguageRepository;
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
public class LanguageRepositoryUnitTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private LanguageRepository languageRepository;
 
    
    @Test
    public void whenFindByCode_thenReturnLanguage() {
        // given
        Language language = new Language("en");
        entityManager.persist(language);
        entityManager.flush();
        System.out.println("test lang repo");
        // when
        Language found = languageRepository.findByCode(language.getCode());

        // then
        assertThat(found.getCode()).isEqualTo(language.getCode());
    }
 
}