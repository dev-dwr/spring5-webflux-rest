package com.dwr.spring5webfluxrest.bootstrap;

import com.dwr.spring5webfluxrest.domain.Category;
import com.dwr.spring5webfluxrest.domain.Vendor;
import com.dwr.spring5webfluxrest.repositories.CategoryRepository;
import com.dwr.spring5webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) {
            loadCategories();
            loadVendors();

    }
    private void loadCategories(){
        categoryRepository.save(Category.builder()
                .description("Fruits").build()).block();

        categoryRepository.save(Category.builder()
                .description("Nuts").build()).block();

        categoryRepository.save(Category.builder()
                .description("Breads").build()).block();

        categoryRepository.save(Category.builder()
                .description("Meats").build()).block();

        categoryRepository.save(Category.builder()
                .description("Eggs").build()).block();


        System.out.println("Categories Loaded = " + categoryRepository.count().block() );
    }
    private void loadVendors(){
        vendorRepository.save(Vendor.builder().firstName("Joe").lastName("Mama").build()).block();
        vendorRepository.save(Vendor.builder().firstName("Ann").lastName("Ham").build()).block();
        vendorRepository.save(Vendor.builder().firstName("Dave").lastName("Scott").build()).block();
        vendorRepository.save(Vendor.builder().firstName("Brock").lastName("Pokemon").build()).block();
        System.out.println("Vendors Loaded = " + vendorRepository.count().block());
    }
}
