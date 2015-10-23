package za.co.mwongho.microservice.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.mwongho.microservice.customer.model.Customer;


@RestController
public class CustomerService {
	
	 private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @RequestMapping("/customer")
    public Customer getCustomer(@RequestParam(value = "orderId", required = true) int orderId) {
    	LOG.debug("getCustomer ");
        return new Customer(1, orderId,  "Bob");
    }
}