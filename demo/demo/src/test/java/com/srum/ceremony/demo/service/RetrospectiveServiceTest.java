package com.srum.ceremony.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.srum.ceremony.demo.model.Retrospective;

public class RetrospectiveServiceTest {

    private RetrospectiveService service;

    @BeforeEach
    void setUp() {
        service = new RetrospectiveService();
    }

    @Test
    void testCreateRetrospective() {
        Retrospective newRetrospective = new Retrospective();
        newRetrospective.setName("ganesh");
        newRetrospective.setSummary("aaaaaaaaaaaaaaaaaaaa");
        LocalDate date = LocalDate.now();
        newRetrospective.setDate(date);
        
        ResponseEntity<?> response = service.createRetrospective(newRetrospective);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
