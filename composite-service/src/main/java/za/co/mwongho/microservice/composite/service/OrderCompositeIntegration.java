package za.co.mwongho.microservice.composite.service;


import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import za.co.mwongho.microservice.customer.model.Customer;
import za.co.mwongho.microservice.product.model.Product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@Component
public class OrderCompositeIntegration {

    private static final Logger LOG = LoggerFactory.getLogger(OrderCompositeIntegration.class);

    @Autowired
    private Util util;

    private RestTemplate restTemplate = new RestTemplate();

    // -------- //
    // PRODUCTS //
    // -------- //

    public ResponseEntity<Product> getProduct(long productId) {

        URI uri = util.getServiceUrl("product-service");
        LOG.debug("getProduct from URI: {}", uri);
        String url = uri.toString() + "/product?orderId=" + productId;
        LOG.debug("getProduct from URL: {}", url);

        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        LOG.debug("getProduct http-status: {}", resultStr.getStatusCode());
        LOG.debug("getProduct body: {}", resultStr.getBody());

        Product product = response2Product(resultStr);
        LOG.debug("getProduct.id: {}", product.getId());

        return util.createOkResponse(product);
    }
    
    // -------- //
    // CUSTOMER //
    // -------- //

    public ResponseEntity<Customer> getCustomer(long customerId) {

        URI uri = util.getServiceUrl("customer-service");
        LOG.debug("getCustomer from URI: {}", uri);
        String url = uri.toString() + "/customer?orderId=" + customerId;
        LOG.debug("getCustomer from URL: {}", url);

        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        LOG.debug("getCustomer http-status: {}", resultStr.getStatusCode());
        LOG.debug("getCustomer body: {}", resultStr.getBody());

        Customer customer = response2Customer(resultStr);
        LOG.debug("getCustomer.id: {}", customer.getId());

        return util.createOkResponse(customer);
    }

    // ----- //
    // UTILS //
    // ----- //

    /*
     * TODO: Extract to a common util-lib
     */

    private ObjectReader getObjectReader(Class<?> type) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.reader(type);
    }

    public Product response2Product(ResponseEntity<String> response) {
        try {
            return getObjectReader(Product.class).readValue(response.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Customer response2Customer(ResponseEntity<String> response) {
        try {
            return getObjectReader(Customer.class).readValue(response.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
