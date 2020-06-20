package com.dwr.spring5webfluxrest.services;

import com.dwr.spring5webfluxrest.domain.Vendor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {
    Flux<Vendor> listAllVendors();
    Mono<Vendor> getVendorById(String id);
}
