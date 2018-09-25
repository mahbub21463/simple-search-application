package com.searchapp.controllertest;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.searchapp.controllers.DeveloperController;
import com.searchapp.controllers.LanguageController;
import com.searchapp.controllers.ProgrammingLanguageController;
import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.LanguageRepository;
import com.searchapp.dao.ProgrammingLanguageRepository;
import com.searchapp.models.Developer;
import com.searchapp.models.Language;
import com.searchapp.models.ProgrammingLanguage;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ProgrammingLanguageController.class)
public class ProgrammingLanguageRestControllerUnitTest {
 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private ProgrammingLanguageRepository programmingLanguageRepository;
 
    @Test
    public void givenDevelopers_whenGetProgrammingLanguages_thenReturnJsonArray()
      throws Exception {

        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage("en");

        List<ProgrammingLanguage> allProgrammingLanguages = Arrays.asList(programmingLanguage);

        given(programmingLanguageRepository.findAll()).willReturn(allProgrammingLanguages);
        
        mvc.perform(get("/api/porgramming-languages")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].name", is(programmingLanguage.getName())));
    }
}