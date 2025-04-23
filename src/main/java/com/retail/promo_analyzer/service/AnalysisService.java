package com.retail.promo_analyzer.service;

import com.retail.promo_analyzer.model.ActualSales;
import com.retail.promo_analyzer.repository.ActualSalesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalysisService {
  private final ActualSalesRepository actualSalesRepository;
  private final EntityManager entityManager;

  public Page<Map<String, Object>> getPromoAnalysis(Pageable pageable) {
    Query query = entityManager.createNativeQuery(
        "SELECT * FROM promo_analysis_view ORDER BY chain_name, month");
    query.setFirstResult((int) pageable.getOffset());
    query.setMaxResults(pageable.getPageSize());

    // Получение общего количества для Page
    Query countQuery = entityManager.createNativeQuery(
        "SELECT COUNT(*) FROM promo_analysis_view");
    long total = ((Number) countQuery.getSingleResult()).longValue();

    return new PageImpl<Map<String, Object>>(transformToMapList(query.getResultList()), pageable, total);
  }

  public List<ActualSales> getSalesByFilter(List<String> chains, List<String> products,
                                            LocalDate startDate, LocalDate endDate) {
    if (chains == null || chains.isEmpty()) {
      chains = List.of("%");
    }
    if (products == null || products.isEmpty()) {
      products = List.of("%");
    }

    return actualSalesRepository.findByChainsAndProductsAndDateBetween(
        chains, products, startDate, endDate);
  }

  private List<Map<String, Object>> transformToMapList(List<Object[]> resultList) {
    List<Map<String, Object>> mappedResults = new ArrayList<>();

    for (Object[] row : resultList) {
      Map<String, Object> rowMap = new LinkedHashMap<>();
      rowMap.put("chainName", row[0]);
      rowMap.put("categoryCode", row[1]);
      rowMap.put("categoryName", row[2]);
      rowMap.put("month", row[3]);
      rowMap.put("regularVolume", row[4]);
      rowMap.put("promoVolume", row[5]);
      rowMap.put("regularSalesValue", row[6]);
      rowMap.put("promoSalesValue", row[7]);
      rowMap.put("promoPercentage", row[8]);

      mappedResults.add(rowMap);
    }

    return mappedResults;
  }
}