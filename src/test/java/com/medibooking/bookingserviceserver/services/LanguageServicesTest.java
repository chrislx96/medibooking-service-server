package com.medibooking.bookingserviceserver.services;

import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserviceserver.entities.Account;
import com.medibooking.bookingserviceserver.entities.Language;
import com.medibooking.bookingserviceserver.mappers.*;
import com.medibooking.bookingserviceserver.repositories.AccountRepository;
import com.medibooking.bookingserviceserver.repositories.LanguageRepository;
import com.medibooking.bookingserviceserver.utils.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AccountMapperImpl.class, AppointmentMapperImpl.class, DoctorMapperImpl.class,
        LanguageMapperImpl.class, PatientMapperImpl.class, SpecializationMapperImpl.class, Utility.class})
public class LanguageServicesTest {

    @Mock
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private Utility utility;

    LanguageService languageService;

    @BeforeEach
    void setup() {
        languageService = new LanguageService(languageRepository, languageMapper);
    }

    @Test
    public void shouldReturnLanguageListGivenLanguagesExist() {
        Language language1 = utility.buildLanguage("English");
        Language language2 = utility.buildLanguage("Chinese");

        when(languageRepository.findAll()).thenReturn(List.of(language1, language2));
        List<LanguageGetDto> returnedLanguageList = languageService.getAllLanguages();
        assertNotNull(returnedLanguageList);
        assertEquals(2, returnedLanguageList.size());
    }
}
