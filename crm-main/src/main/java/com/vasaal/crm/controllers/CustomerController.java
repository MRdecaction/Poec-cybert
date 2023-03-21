package com.vasaal.crm.controllers;

import java.util.ArrayList;

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
    
    ArrayList<Customer> customer = new ArrayList<>();

    private Customer findCustomerById(long id) {
        Customer customerFinded = null;

            for (Customer customer : this.customer) {
                if (customer.getId() == id) {
                    customerFinded = customer;
                    break;
                }
            }
            return customerFinded;
        }

    // AJOUTER CLIENT
    
    @GetMapping(CommonConstant.ROUTE_EDIT)
    public String editCustomer(Model model, @PathVariable("id") long id) {
        Customer customerFinded = this.findCustomerById(id);

        model.addAttribute(MODEL_ONE, customerFinded);
        return "customers/form";
    }


    @PostMapping(CommonConstant.ROUTE_SAVE)
    public String saveCustomer(Model model, @ModelAttribute Customer CustomerSubmit) {
        Customer customerFinded = this.findCustomerById(CustomerSubmit.getId());

        if (customerFinded != null) {
            customerFinded.setFirstname(CustomerSubmit.getFirstname());
            customerFinded.setLastname(CustomerSubmit.getLastname());
            customerFinded.setId(CustomerSubmit.getId());
        }

        return "redirect:/customers/" + customerFinded.getId() + "/show";
    }




    // SUPPRIMER UN CLIENT
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteCustomer(@RequestParam long id) {

        customerRepository.deleteById(id);
        return "Customer deleted";
    }

}
