package za.co.mwongho.microservice.composite.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Util {
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    @Autowired
    private LoadBalancerClient loadBalancer;

    public URI getServiceUrl(String serviceId) {
    	ServiceInstance serviceInstance = loadBalancer.choose(serviceId);
    	if(serviceInstance != null){
    		return serviceInstance.getUri();
    	}
        return null;
    }

    public <T> ResponseEntity<T> createOkResponse(T body) {
        return createResponse(body, HttpStatus.OK);
    }

    public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }
}
