package com.vasaal.crm.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vasaal.crm.entities.Product;
import com.vasaal.crm.repository.ProductRepository;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // LISTER PRODUCT
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Product> getAllProducts() {

        return productRepository.findAll();
    }

    // AJOUTER PRODUIT
    @PostMapping(path = "/add")
    public @ResponseBody String addNewProduct(@RequestParam String name, @RequestParam String surface,
            @RequestParam String hex_color, @RequestParam BigDecimal price) {

        Product n = new Product();
        n.setPrice(price);
        n.setName(name);
        n.setHexColor(hex_color);
        n.setSurface(surface);
        productRepository.save(n);
        return "Product added";
    }

    // SUPPRIMER UN PRODUIT
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteProduct(@RequestParam long id) {

        productRepository.deleteById(id);
        return "Product deleted";
    }

    // MODIFIER UN PRODUIT
    @PutMapping(path = "/update")
    public @ResponseBody String updateProduct(@RequestParam long id, @RequestParam String name,
            @RequestParam String surface,
            @RequestParam String hex_color, @RequestParam BigDecimal price) {
        Product product = productRepository.findById(id).orElseThrow();

        product.setPrice(price);
        product.setName(name);
        product.setHexColor(hex_color);
        product.setSurface(surface);
        productRepository.save(product);
        return "Product updated";

    }
}
