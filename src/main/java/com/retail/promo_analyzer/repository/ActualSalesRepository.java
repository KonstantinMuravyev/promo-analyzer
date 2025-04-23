package com.retail.promo_analyzer.repository;

import com.retail.promo_analyzer.model.ActualSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ActualSalesRepository extends JpaRepository<ActualSales, Long> {
  @Query("SELECT a FROM ActualSales a WHERE a.customer.chainName IN :chains AND a.product.materialNo IN :products")
  List<ActualSales> findByChainsAndProducts(@Param("chains") List<String> chains, @Param("products") List<String> products);

  @Query("SELECT a FROM ActualSales a WHERE " +
      "(a.customer.chainName IN :chains OR :chains IS NULL) AND " +
      "(a.product.materialNo IN :products OR :products IS NULL) AND " +
      "a.date BETWEEN :startDate AND :endDate")
  List<ActualSales> findByChainsAndProductsAndDateBetween(
      @Param("chains") List<String> chains,
      @Param("products") List<String> products,
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);
}