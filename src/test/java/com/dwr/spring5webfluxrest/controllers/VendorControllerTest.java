package com.dwr.spring5webfluxrest.controllers;

import com.dwr.spring5webfluxrest.domain.Vendor;
import com.dwr.spring5webfluxrest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    WebTestClient webTestClient;

    Flux<Vendor> vendors;

    Mono<Vendor> vendorMono;

    @BeforeEach
    void setUp() {
        vendors = Flux.just(Vendor.builder().firstName("Test1").lastName("Test2").build(),
                        Vendor.builder().firstName("firstName").lastName("lastName").build());

        vendorMono = Mono.just(Vendor.builder().firstName("Test1").lastName("Test2").build());

        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void getAllVendors() {
        given(vendorService.listAllVendors())
                .willReturn(vendors);
        webTestClient.get()
                .uri(VendorController.BASE_URL)
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    void getVendorById() {
        given(vendorService.getVendorById(anyString()))
                .willReturn(vendorMono);
        webTestClient.get()
                .uri(VendorController.BASE_URL + "/1")
                .exchange()
                .expectBody(Vendor.class);
    }
}