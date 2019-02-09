package com.home.tenants.controller;

import com.home.tenants.TenantsApplication;
import com.home.tenants.repository.PaymentRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TenantsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PaymentRepository paymentRepository;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testCreateCar() {
        int paymentId =
                given()
                        .contentType(ContentType.JSON)
                        .body(paymentRequest("Rent paid", 1234L)).
                        when()
                        .post("/payments")
                        .then()
                        .statusCode(200)
                        .body("id", is(notNullValue()))
                        .body("contractId", is(1234))
                        .body("value", is(4))
                        .body("description", is("Rent paid"))
                        .body("isImported", is(true))
                        .extract()
                        .path("id");

        assertTrue(paymentRepository.existsById((long) paymentId));
    }

    @Test
    public void testCreateCarShouldFailIfContractIdIsNull() {
        given()
                .contentType(ContentType.JSON)
                .body(paymentRequest("Rent paid", null)).
                when()
                .post("/payments")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateCarShouldFailIfDescriptionIsNull() {
        given()
                .contentType(ContentType.JSON)
                .body(paymentRequest(null, 1234L)).
                when()
                .post("/payments")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateCarShouldFailIfDescriptionIsEmpty() {
        given()
                .contentType(ContentType.JSON)
                .body(paymentRequest("", 1234L)).
                when()
                .post("/payments")
                .then()
                .statusCode(400);
    }

    @Test
    public void testGetPayment() {
        when()
                .get("/payments/1")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("contractId", is(17689))
                .body("value", is(100))
                .body("description", is("Rent payment"))
                .body("isImported", is(false))
                .body("isDeleted", is(false));
    }

    @Test
    public void testGetPaymentFailureWithInvalidId() {
        when()
                .get("/payments/13232")
                .then()
                .statusCode(400);
    }


    private Map<String, Object> paymentRequest(String description, Long contractId) {
        Map<String, Object> paymentRequest = new HashMap<>();
        Date now = new Date();
        paymentRequest.put("contractId", contractId);
        paymentRequest.put("value", 4);
        paymentRequest.put("description", description);
        paymentRequest.put("createdAt", now);
        paymentRequest.put("updatedAt", now);
        paymentRequest.put("time", now);
        paymentRequest.put("isImported", true);
        return paymentRequest;
    }

}
