package com.retail.promo_analyzer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

  @Id
  @Column(name = "ch3_ship_to_code")
  private String ch3ShipToCode;

  @Column(name = "chain_name")
  private String chainName;

  @Column(name = "ch3_ship_to_name")
  private String ch3ShipToName;
}