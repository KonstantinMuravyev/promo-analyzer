package com.retail.promo_analyzer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "actuals")
@Getter
@Setter
@NoArgsConstructor
public class ActualSales {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ship_to_code", referencedColumnName = "ch3ShipToCode")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "material_no", referencedColumnName = "materialNo")
  private Product product;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "volume")
  private BigDecimal volume;

  @Column(name = "actual_sales_value")
  private BigDecimal actualSalesValue;

  @Column(name = "price_per_unit")
  private BigDecimal pricePerUnit;

  @Enumerated(EnumType.STRING)
  @Column(name = "price_type")
  private PriceType priceType;

  public enum PriceType {
    REGULAR, PROMO
  }
}