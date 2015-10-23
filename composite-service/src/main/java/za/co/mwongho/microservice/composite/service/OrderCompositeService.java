package za.co.mwongho.microservice.composite.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.mwongho.microservice.composite.model.Order;
import za.co.mwongho.microservice.customer.model.Customer;
import za.co.mwongho.microservice.product.model.Product;


@RestController
public class OrderCompositeService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderCompositeService.class);

    @Autowired
    OrderCompositeIntegration integration;

    @Autowired
    Util util;

    @RequestMapping("/")
    public String getProduct() {
        return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Hello from ProductAPi\"}";
    }

    @RequestMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable long orderId) {

        // 1. First get mandatory product information
        ResponseEntity<Product> productResult = integration.getProduct(orderId);

        if (!productResult.getStatusCode().is2xxSuccessful()) {
            // We can't proceed, return whatever fault we got from the getProduct call
            return util.createResponse(null, productResult.getStatusCode());
        }
        
        // 1. First get mandatory customer information
        ResponseEntity<Customer> customerResult = integration.getCustomer(orderId);

        if (!customerResult.getStatusCode().is2xxSuccessful()) {
            // We can't proceed, return whatever fault we got from the getProduct call
            return util.createResponse(null, customerResult.getStatusCode());
        }

        return util.createOkResponse( new Order( orderId, productResult.getBody(),  customerResult.getBody()) );
    }
}

