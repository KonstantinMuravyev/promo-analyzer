package com.retail.promo_analyzer.controller;

import com.retail.promo_analyzer.model.Price;
import com.retail.promo_analyzer.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/finance/prices")
@RequiredArgsConstructor
public class FinanceController {
  private final FinanceService financeService;

  @GetMapping
  public List<Price> getAllPrices() {
    return financeService.getAllPrices();
  }

  @PostMapping
  public Price createPrice(@RequestBody Price price) {
    return financeService.createPrice(price);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Price> getPriceById(@PathVariable Long id) {
    return financeService.getPriceById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public Price updatePrice(@PathVariable Long id, @RequestBody Price price) {
    return financeService.updatePrice(id, price);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePrice(@PathVariable Long id) {
    financeService.deletePrice(id);
    return ResponseEntity.ok().build();
  }
}