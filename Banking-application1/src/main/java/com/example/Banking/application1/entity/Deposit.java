package com.example.Banking.application1.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "d1")
public class Deposit {

 // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPOSIT_DTLS1_GENERATOR")
  //@SequenceGenerator(name = "DEPOSIT_DTLS1_GENERATOR", sequenceName = "DEPOSIT_DTLS1_SEQ", allocationSize = 1)
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long deposit;
  private String transactionId;


}
