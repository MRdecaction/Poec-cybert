package com.vasaal.crm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.vasaal.crm.entities.Customer;
import com.vasaal.crm.repository.CustomerRepository;


@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

    public static final String MODEL_ALL = "customer";
    public static final String MODEL_ONE = "current_customer";

    
    @Autowired
    private CustomerRepository customerRepository;

    // LISTER CLIENT
    /**
     * @return
     */
    @GetMapping(CommonConstant.ROUTE_ALL)
    public  String showAll(Model model) {
        
        model.addAttribute(MODEL_ALL, this.customerRepository.findAll());
        return "customers/list";
    }

    @GetMapping(CommonConstant.ROUTE_SHOW)   // => /customer/120/show
    public String viewProfil(Model model, @PathVariable("id") long id) {
        Customer customerFinded = this.customerRepository.findById(id).orElse(new Customer());

        if (customerFinded != null) {
            model.addAttribute(MODEL_ONE, customerFinded);
        }

        return "customers/profil";
    }
     
    List<Customer> tcustomers;
    

    /**
     * @param id
     * @return
     */
    private Customer findCustomerById(long id) {
        Customer customerFinded = null;
        tcustomers = this.customerRepository.findAll();
        for (int i = 0; i <= tcustomers.size() - 1; i++) {
            Customer customer = tcustomers.get(i);
                if (customer.getId() == id) {
                    customerFinded = customer;
                    break;
                }
        }
        return customerFinded;
    }

    
    
    @GetMapping(CommonConstant.ROUTE_EDIT)
    public String editCustomer(Model model, @PathVariable("id") long id) {
        Customer customerFinded = this.findCustomerById(id);

        model.addAttribute(MODEL_ONE, customerFinded);
        return "customers/form";
    }


    @PostMapping(CommonConstant.ROUTE_SAVE)
    public String saveCustomer(Model model, @ModelAttribute Customer customerSubmit) {
        Customer customerFinded = this.findCustomerById(customerSubmit.getId());
        
        if (customerFinded != null) {
            customerFinded.setFirstname(customerSubmit.getFirstname());
            customerFinded.setLastname(customerSubmit.getLastname());
            customerFinded.setId(customerSubmit.getId());
        }

        this.customerRepository.save(customerFinded);

        return "redirect:/customer/" + customerFinded.getId() + "/show";
    }




        //Ajouter un client 

        @GetMapping(CommonConstant.ROUTE_CREATE)
        public String creatCustomer(Model model) {


            return "customers/create";
        }
    
    

        //Ajouter un client sauvegarde

    @PostMapping(CommonConstant.ROUTE_SAVECREATE)
    public String createCustomer(Model model, @ModelAttribute Customer customerSubmit,@RequestParam("firstname") String firstname , @RequestParam("lastname") String lastname , @RequestParam("address") String address , @RequestParam("email") String email ,  @RequestParam("phoneNumber") String phoneNumber) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstname(firstname);
        newCustomer.setLastname(lastname);
        newCustomer.setAddress(address);
        newCustomer.setEmail(email);
        newCustomer.setPhoneNumber(phoneNumber);
        this.customerRepository.save(newCustomer);

        return  "customers/list";
    }





}
