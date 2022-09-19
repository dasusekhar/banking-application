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
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name="L1")
@ToString
public class LoginDetail {
  @Id
  //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOGIN_DTLS1_GENERATOR")
 // @SequenceGenerator(name = "LOGIN_DTLS1_GENERATOR", sequenceName = "LOGIN_DTLS1_SEQ", allocationSize = 1)
  private Long id;
  private String  userName;
  private String password;
  @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "my_bank_id")
  private MyBank myBank;


}
