package com.dwr.spring5webfluxrest.controllers;

import com.dwr.spring5webfluxrest.domain.Vendor;
import com.dwr.spring5webfluxrest.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
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

    @Test
    void saveVendorTest() {
        given(vendorService.saveVendors(any(Publisher.class)))
                .willReturn(Mono.just(Vendor.builder().firstName("Name").build()));

        Mono<Vendor> vendorToSave = Mono.just(Vendor.builder().firstName("A").lastName("B").build());

        webTestClient.post()
                .uri(VendorController.BASE_URL)
                .body(vendorToSave, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();

    }
    @Test
    void updateVendorTest(){
        given(vendorService.update(anyString(), any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().firstName("lal").build()));

        Mono<Vendor> vendorToUpdate = Mono.just(Vendor.builder().firstName("dav").lastName("rym").build());

        webTestClient.put()
                .uri(VendorController.BASE_URL + "/1")
                .body(vendorToUpdate, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();

    }
    @Test
    void patchVendorTestWithChanges(){
        given(vendorService.patch(anyString(), any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().firstName("lal").build()));

        Mono<Vendor> vendorToUpdate = Mono.just(Vendor.builder().firstName("dav").lastName("rym").build());

        webTestClient.patch()
                .uri(VendorController.BASE_URL + "/1")
                .body(vendorToUpdate, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();

    }
    @Test
    void patchVendorTestWithNoChanges(){
        given(vendorService.patch(anyString(), any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().firstName("lal").lastName("l").build()));

        Mono<Vendor> vendorToUpdate = Mono.just(Vendor.builder().firstName("lal").lastName("l").build());

        webTestClient.patch()
                .uri(VendorController.BASE_URL + "/1")
                .body(vendorToUpdate, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();

    }
    @Test
    void deleteVendorTest(){
        webTestClient.delete()
                .uri(VendorController.BASE_URL +  "/id")
                .exchange()
                .expectStatus()
                .isOk();
    }
}














