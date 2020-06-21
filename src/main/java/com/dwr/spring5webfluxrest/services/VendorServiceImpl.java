package com.dwr.spring5webfluxrest.services;

import com.dwr.spring5webfluxrest.domain.Vendor;
import com.dwr.spring5webfluxrest.repositories.VendorRepository;
import org.reactivestreams.Publisher;
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

    @Override
    public Mono<Void> saveVendors(Publisher<Vendor> vendorPublisher) {
        return vendorRepository.saveAll(vendorPublisher).then();
    }

    @Override
    public Mono<Vendor> update(String id, Vendor vendor) {
        return vendorRepository.findById(id)
                .flatMap(foundVendor ->{
                    vendor.setId(foundVendor.getId());
                    return vendorRepository.save(vendor);
                })
                .switchIfEmpty(Mono.error(new Exception("not found")));
    }

    @Override
    public Mono<Vendor> patch(String id, Vendor vendor) {
        return vendorRepository.findById(id)
                .flatMap(foundVendor -> switchData(foundVendor, vendor))
                .switchIfEmpty(Mono.error(new Exception("not found")));
    }

    private Mono<Vendor> switchData(Vendor foundVendor, Vendor vendor){
        if(!foundVendor.getFirstName().equals(vendor.getFirstName())){
            foundVendor.setFirstName(vendor.getFirstName());
        }
        if(!foundVendor.getLastName().equals(vendor.getLastName())){
            foundVendor.setLastName(vendor.getLastName());
        }
        return Mono.just(foundVendor);
    }

    @Override
    public Mono<Void> deleteCategoryById(String id) {
        return vendorRepository.deleteById(id);
    }

}
