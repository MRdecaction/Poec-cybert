package com.vasaal.crm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    public static final String MODEL_ALL = "product";
    public static final String MODEL_ONE = "current_product";

    
    @Autowired
    private ProductRepository productRepository;

    // LISTER CLIENT
    /**
     * @return
     */
    @GetMapping(CommonConstant.ROUTE_ALL)
    public  String showAll(Model model) {
        
        model.addAttribute(MODEL_ALL, this.productRepository.findAll());
        return "products/list";
    }

    @GetMapping(CommonConstant.ROUTE_SHOW)   // => /Product/120/show
    public String viewProfil(Model model, @PathVariable("id") long id) {
        Product productFinded = this.productRepository.findById(id).orElse(new Product());

        if (productFinded != null) {
            model.addAttribute(MODEL_ONE, productFinded);
        }

        return "products/profil";
    }
     
    List<Product> tproduct;
    

    /**
     * @param id
     * @return
     */
    private Product findProductById(long id) {
        Product productFinded = null;
        tproduct = this.productRepository.findAll();
        for (int i = 0; i <= tproduct.size() - 1; i++) {
            Product product = tproduct.get(i);
                if (product.getId() == id) {
                    productFinded = product ;
                    break;
                }
        }
        return productFinded;
    }

    // AJOUTER CLIENT
    
    @GetMapping(CommonConstant.ROUTE_EDIT)
    public String editCustomer(Model model, @PathVariable("id") long id) {
        Product productFinded = this.findProductById(id);

        model.addAttribute(MODEL_ONE, productFinded);
        return "products/form";
    }

// VOIR LE PROFIL DU PRODUCT
    @PostMapping(CommonConstant.ROUTE_SAVE)
    public String saveProduct(Model model, @ModelAttribute Product ProductSubmit) {
        Product productFinded = this.findProductById(ProductSubmit.getId());
        
        if (productFinded != null) {
            productFinded.setName(ProductSubmit.getName());
            productFinded.setPrice(ProductSubmit.getPrice());
            productFinded.setId(ProductSubmit.getId());
        }

        this.productRepository.save(productFinded);

        return "redirect:/product/" + productFinded.getId() + "/show";
    }




    // SUPPRIMER UN CLIENT
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteProduct(@RequestParam long id) {

        productRepository.deleteById(id);
        return "Product deleted";
    }

}
