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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class DeveloperRepositoryUnitTest {
 
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private DeveloperRepository developerRepository;
 

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