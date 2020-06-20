package com.dwr.spring5webfluxrest.services;

import com.dwr.spring5webfluxrest.domain.Vendor;
import com.dwr.spring5webfluxrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Flux<Vendor> listAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Mono<Vendor> getVendorById(String id) {
        return vendorRepository.findById(id);
    }
}
