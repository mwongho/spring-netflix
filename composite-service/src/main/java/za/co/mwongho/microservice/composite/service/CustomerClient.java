package za.co.mwongho.microservice.composite.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import za.co.mwongho.microservice.customer.model.Customer;

@FeignClient("customer-service")
public interface CustomerClient {
	@RequestMapping(method = RequestMethod.GET, value = "/customer?orderId={orderId}")
	public Customer getCustomer(@RequestParam("orderId") long orderId);
}
