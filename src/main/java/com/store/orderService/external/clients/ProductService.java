package com.store.orderService.external.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "productService/product")
public interface ProductService {
    @PutMapping("/reduceQuantity/{productId}")
     ResponseEntity<Void> reduceQuantity
            (@PathVariable Long productId,
             @RequestParam long quantity) ;

}
