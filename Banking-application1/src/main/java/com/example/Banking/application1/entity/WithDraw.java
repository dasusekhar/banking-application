package com.example.Banking.application1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "shsy")
public class WithDraw {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WITHDRAW_DTLS1_GENERATOR")
 // @SequenceGenerator(name = "WITHDRAW_DTLS1_GENERATOR", sequenceName = "WITHDRAW_DTLS1_SEQ", allocationSize = 1)
  private Long id;
  private Long withdraw;
  private String transactionId;
  private Long  with_draw_id;

}
