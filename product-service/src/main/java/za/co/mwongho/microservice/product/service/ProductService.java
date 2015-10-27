package za.co.mwongho.microservice.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.mwongho.microservice.product.model.Product;


@RestController
public class ProductService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @RequestMapping("/product")
    public Product getProduct(@RequestParam(value = "orderId", required = true) long orderId) {
    	LOG.debug("GetProduct ");
        return new Product(1, orderId, "Foo");
    }
}