package com.example.Banking.application1.dto;

import com.example.Banking.application1.entity.MyBank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class DepositDto {
  private Long id;
  private Long deposit;
  private String transactionId;


}
