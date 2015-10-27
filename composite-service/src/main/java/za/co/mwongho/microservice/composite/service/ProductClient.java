package za.co.mwongho.microservice.composite.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import za.co.mwongho.microservice.product.model.Product;

@FeignClient("product-service")
public interface ProductClient {
	@RequestMapping(method = RequestMethod.GET, value = "/product?orderId={orderId}")
	public Product getProduct(@RequestParam("orderId") long orderId);
}
