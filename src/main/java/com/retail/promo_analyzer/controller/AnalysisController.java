package com.retail.promo_analyzer.controller;

import com.retail.promo_analyzer.model.ActualSales;
import com.retail.promo_analyzer.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {
  private final AnalysisService analysisService;

  @GetMapping("/promo")
  public Page<Map<String, Object>> getPromoAnalysis(
      @PageableDefault(size = 20, sort = "chainName") Pageable pageable) {
    return analysisService.getPromoAnalysis(pageable);
  }

  @GetMapping("/sales")
  public List<ActualSales> getSalesByFilter(
      @RequestParam(required = false) List<String> chains,
      @RequestParam(required = false) List<String> products,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

    if (startDate == null) startDate = LocalDate.now().minusMonths(1);
    if (endDate == null) endDate = LocalDate.now();

    return analysisService.getSalesByFilter(chains, products, startDate, endDate);
  }
}