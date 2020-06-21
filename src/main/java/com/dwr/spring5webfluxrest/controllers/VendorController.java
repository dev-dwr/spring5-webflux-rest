package com.dwr.spring5webfluxrest.controllers;

import com.dwr.spring5webfluxrest.domain.Vendor;
import com.dwr.spring5webfluxrest.services.VendorService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Void> createVendor(@RequestBody Publisher<Vendor> vendor){
        return vendorService.saveVendors(vendor).then();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Mono<Vendor> updateVendor(@RequestBody Vendor vendor, @PathVariable String id){
        return vendorService.update(id, vendor);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public Mono<Vendor> patchVendor(@RequestBody Vendor vendor, @PathVariable String id){
        return vendorService.patch(id, vendor);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Mono<Void> deleteVendor(@PathVariable String id){
        return vendorService.deleteCategoryById(id);
    }


}
