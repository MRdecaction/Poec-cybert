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

import com.vasaal.crm.entities.Order;
import com.vasaal.crm.entities.OrderItem;
import com.vasaal.crm.repository.OrderRepository;


@Controller
@RequestMapping(path = "/order")
public class OrderController {

    public static final String MODEL_ALL = "order";
    public static final String MODEL_ONE = "current_order";

    
    @Autowired
    private OrderRepository orderRepository;

    // LISTER CLIENT
    /**
     * @return
     */
    @GetMapping(CommonConstant.ROUTE_ALL)
    public  String showAll(Model model) {
        
        model.addAttribute(MODEL_ALL, this.orderRepository.findAll());
        return "orders/list";
    }

    @GetMapping(CommonConstant.ROUTE_SHOW)   // => /order/120/show
    public String viewProfil(Model model, @PathVariable("id") long id) {
        Order orderFinded = this.orderRepository.findById(id).orElse(new Order());

        if (orderFinded != null) {
            model.addAttribute(MODEL_ONE, orderFinded);
        }

        return "orders/profil";
    }
     
    List<Order> torders;
    

    /**
     * @param id
     * @return
     */
    private Order findOrderById(long id) {
        Order orderFinded = null;
        torders = this.orderRepository.findAll();
        for (int i = 0; i <= torders.size() - 1; i++) {
            Order order = torders.get(i);
                if (order.getId() == id) {
                    orderFinded = order;
                    break;
                }
        }
        return orderFinded;
    }

    
    
    @GetMapping(CommonConstant.ROUTE_EDIT)
    public String editOrder(Model model, @PathVariable("id") long id) {
        Order orderFinded = this.findOrderById(id);

        model.addAttribute(MODEL_ONE, orderFinded);
        return "orders/form";
    }


    @PostMapping(CommonConstant.ROUTE_SAVE)
    public String saveOrder(Model model, @ModelAttribute Order orderSubmit) {
        Order orderFinded = this.findOrderById(orderSubmit.getId());
        
        if (orderFinded != null) {
            orderFinded.setCustomer(orderSubmit.getCustomer());
            orderFinded.setTotalPrice(orderSubmit.getTotalPrice());
            orderFinded.setId(orderSubmit.getId());
        }

        this.orderRepository.save(orderFinded);

        return "redirect:/order/" + orderFinded.getId() + "/show";
    }




        //Ajouter un client 

        @GetMapping(CommonConstant.ROUTE_CREATE)
        public String createorder(Model model) {


            return "orders/create";
        }
    
    

        //Ajouter un client sauvegarde

   /*@PostMapping(CommonConstant.ROUTE_SAVECREATE)
    public String createOrder(Model model, @ModelAttribute Order orderSubmit,@RequestParam("customer") String customer , @RequestParam("totalPrice") String totalPrice ) {
        Order newOrder = new Order();
        newOrder.setname(name);
        newOrder.set();
        newOrder.setAddress();
        newOrder.setEmail();
        newOrder.setPhoneNumber();
        this.orderRepository.save();

        return  "orders/list";


    }*/





}
