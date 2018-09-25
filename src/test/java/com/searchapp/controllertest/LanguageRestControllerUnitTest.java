package com.searchapp.controllertest;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.searchapp.controllers.DeveloperController;
import com.searchapp.controllers.LanguageController;
import com.searchapp.dao.DeveloperRepository;
import com.searchapp.dao.LanguageRepository;
import com.searchapp.models.Developer;
import com.searchapp.models.Language;
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
@WebMvcTest(LanguageController.class)
public class LanguageRestControllerUnitTest {
 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private LanguageRepository languageRepository;
 
    @Test
    public void givenDevelopers_whenGetLanguages_thenReturnJsonArray()
      throws Exception {

        Language language = new Language("en");

        List<Language> allLanguages = Arrays.asList(language);

        given(languageRepository.findAll()).willReturn(allLanguages);
        
        mvc.perform(get("/api/languages")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].code", is(language.getCode())));
    }
}