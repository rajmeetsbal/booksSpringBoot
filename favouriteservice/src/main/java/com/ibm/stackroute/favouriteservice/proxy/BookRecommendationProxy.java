package com.ibm.stackroute.favouriteservice.proxy;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// Without nameing server 
//@FeignClient(name="currency-exchange-service", url="localhost:8000")
// Using naming server 
@Component
@FeignClient(name="book-recomendations")
//@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="book-recomendations")

public interface BookRecommendationProxy {
//	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	//@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	@PostMapping("/book-recomendations/recommend")
	public void recommend();
}

