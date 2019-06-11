package com.stackroute.favouriteservice.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.stackroute.favouriteservice.model.BookRecomendation;

@Component
@FeignClient(name="book-recomendations")
@RibbonClient(name="book-recomendations")
public interface RecommendBookProxy {
	@PostMapping("api/v1//recommend")
	public void recommendBook(@RequestBody BookRecomendation bookRecomendation);
}
