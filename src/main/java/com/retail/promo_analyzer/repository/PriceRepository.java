package com.retail.promo_analyzer.repository;

import com.retail.promo_analyzer.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
  List<Price> findByChainName(String chainName);
  Optional<Price> findByChainNameAndMaterialNo(String chainName, String materialNo);
}