package com.dwr.spring5webfluxrest.services;

import com.dwr.spring5webfluxrest.domain.Vendor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {
    Flux<Vendor> listAllVendors();
    Mono<Vendor> getVendorById(String id);
    Mono<Void> saveVendors(Publisher<Vendor> vendorPublisher);
    Mono<Vendor> update(String id, Vendor vendor);
    Mono<Vendor> patch(String id, Vendor vendor);
    Mono<Void> deleteCategoryById(String id);
}
