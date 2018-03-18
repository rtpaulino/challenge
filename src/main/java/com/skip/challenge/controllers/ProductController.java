package com.skip.challenge.controllers;

import com.skip.challenge.model.Product;
import com.skip.challenge.services.ProductService;
import com.skip.challenge.vo.PagedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    public PagedContent<Product> search(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(value = "size", defaultValue = "50") @Min(1) @Max(200) Integer size) {
        Page<Product> result = service.search(text, page, size);
        return new PagedContent<Product>(result.getTotalElements(), result.getContent());
    }

}
