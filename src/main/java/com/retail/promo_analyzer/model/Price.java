package com.retail.promo_analyzer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
public class Price {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "chain_name")
  private String chainName;

  @ManyToOne
  @JoinColumn(name = "material_no", referencedColumnName = "materialNo")
  private Product product;

  @Column(name = "material_no")
  private String materialNo;

  @Column(name = "regular_price_per_unit")
  private BigDecimal regularPricePerUnit;
}