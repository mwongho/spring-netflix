package za.co.mwongho.microservice.composite.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import za.co.mwongho.microservice.customer.model.Customer;
import za.co.mwongho.microservice.product.model.Product;

@Component
public class OrderCompositeIntegration {

    private static final Logger LOG = LoggerFactory.getLogger(OrderCompositeIntegration.class);

    @Autowired
    private Util util;
    
    @Autowired
    private ProductClient productClient;
    
    @Autowired
    private CustomerClient customerClient;

    public ResponseEntity<Product> getProduct(long orderId) {
        Product product = productClient.getProduct(orderId);
        LOG.debug("Product id: {}", product.getId());

        return util.createOkResponse(product);
    }
    
    public ResponseEntity<Customer> getCustomer(long orderId) {
        Customer customer = customerClient.getCustomer(orderId);
        LOG.debug("Customer id: {}", customer.getId());

        return util.createOkResponse(customer);
    }

}
