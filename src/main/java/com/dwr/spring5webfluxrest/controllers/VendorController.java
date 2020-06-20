package com.dwr.spring5webfluxrest.controllers;

import com.dwr.spring5webfluxrest.domain.Vendor;
import com.dwr.spring5webfluxrest.services.VendorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors";
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }
    @GetMapping
    public Flux<Vendor> getAllVendors(){
        return vendorService.listAllVendors();
    }

    @GetMapping("/{id}")
    public Mono<Vendor> getVendorById(@PathVariable String id){
        return vendorService.getVendorById(id);
    }


}
