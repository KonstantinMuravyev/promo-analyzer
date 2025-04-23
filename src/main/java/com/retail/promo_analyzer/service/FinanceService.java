package com.retail.promo_analyzer.service;

import com.retail.promo_analyzer.model.Price;
import com.retail.promo_analyzer.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinanceService {
  private final PriceRepository priceRepository;

  public List<Price> getAllPrices() {
    return priceRepository.findAll();
  }

  public Price createPrice(Price price) {
    return priceRepository.save(price);
  }

  public Optional<Price> getPriceById(Long id) {
    return priceRepository.findById(id);
  }

  public Price updatePrice(Long id, Price priceDetails) {
    Price price = priceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Price not found"));
    price.setChainName(priceDetails.getChainName());
    price.setMaterialNo(priceDetails.getMaterialNo());
    price.setRegularPricePerUnit(priceDetails.getRegularPricePerUnit());
    return priceRepository.save(price);
  }

  public void deletePrice(Long id) {
    priceRepository.deleteById(id);
  }
}