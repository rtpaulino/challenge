package com.skip.challenge.services;

import com.skip.challenge.model.Product;
import com.skip.challenge.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Page<Product> search(String text, int page, int size) {
        return repository.search("%" + text + "%", PageRequest.of(page, size));
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }
}
