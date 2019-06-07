package com.stackroute.favouriteservice.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name="book-recomendations")
@RibbonClient(name="book-recomendations")
public interface RecommendBookProxy {
	@PostMapping("/recommend")
	public void recommendBook();
}
