package com.mendel.challenge.integration;

import com.mendel.challenge.Application;
import com.mendel.challenge.dto.TransactionDTO;
import com.mendel.challenge.dto.TransactionSumDTO;
import com.mendel.challenge.util.TransactionsMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ObjectUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    private final String BASE_URL = "/transactions/";
    private final String BASE_URL_TYPE = "/transactions/types/";
    private final String BASE_URL_SUM = "/transactions/sum/";


    private final TransactionsMock transactionsMock = new TransactionsMock();
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getTransaction_notFound() {

        ResponseEntity<TransactionDTO> responseEntity = testRestTemplate.getForEntity(BASE_URL + "13", TransactionDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }


    @Test
    void setTransaction_OK() {

        var updatedInstance = transactionsMock.getOnlyOne();

        updatedInstance.setId(3L);

        HttpEntity<TransactionDTO> requestUpdate = new HttpEntity<>(updatedInstance, new HttpHeaders());

        var response = testRestTemplate.exchange(BASE_URL + "3", HttpMethod.PUT, requestUpdate, TransactionDTO.class);

        var repeatedResponse = testRestTemplate.exchange(BASE_URL + "3", HttpMethod.PUT, requestUpdate, TransactionDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(HttpStatus.NO_CONTENT, repeatedResponse.getStatusCode());

    }

    @Test
    void getTransaction_OK() {

        var transactionMock = transactionsMock.getOnlyOne();
        transactionMock.setId(2L);

        HttpEntity<TransactionDTO> requestUpdate = new HttpEntity<>(transactionMock, new HttpHeaders());

        var newTransactionResponse = testRestTemplate.exchange(BASE_URL + "2", HttpMethod.PUT, requestUpdate, TransactionDTO.class);

        var getTransactionResponse = testRestTemplate.getForEntity(BASE_URL + "2", TransactionDTO.class);

        assertEquals(HttpStatus.OK, getTransactionResponse.getStatusCode());
        assertEquals(HttpStatus.CREATED, newTransactionResponse.getStatusCode());
        assertEquals(transactionMock, getTransactionResponse.getBody());

    }

    @Test
    void getSumTransaction_NotFound() {


        var getTransactionResponse = testRestTemplate.getForEntity(BASE_URL_SUM + "10", TransactionDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, getTransactionResponse.getStatusCode());
        Assertions.assertTrue(ObjectUtils.isEmpty(getTransactionResponse.getBody()));

    }

    @Test
    void getSumTransaction_OK() {

        var transactionMock =  transactionsMock.getOnlyOne();
        transactionMock.setId(5L);

        HttpEntity<TransactionDTO> requestUpdate = new HttpEntity<>(transactionMock, new HttpHeaders());

        var newTransactionResponse = testRestTemplate.exchange(BASE_URL + "5", HttpMethod.PUT, requestUpdate, TransactionDTO.class);
        var getTransactionResponse = testRestTemplate.getForEntity(BASE_URL_SUM + "5", TransactionSumDTO.class);


        assertEquals(HttpStatus.CREATED, newTransactionResponse.getStatusCode());
        assertEquals(HttpStatus.OK, getTransactionResponse.getStatusCode());
        assertEquals(transactionMock.getAmount(), getTransactionResponse.getBody().getSum());



    }

}
