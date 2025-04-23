package com.retail.promo_analyzer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

  @Id
  @Column(name = "material_no")
  private String materialNo;

  @Column(name = "material_desc_rus")
  private String materialDescRus;

  @Column(name = "l3_product_category_code")
  private String l3ProductCategoryCode;

  @Column(name = "l3_product_category_name")
  private String l3ProductCategoryName;
}