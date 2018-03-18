package com.skip.challenge.repositories;

import com.skip.challenge.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE UPPER(p.name) LIKE UPPER(?1) OR UPPER(p.description) LIKE UPPER(?1)")
    Page<Product> search(String text, Pageable pageable);
}
