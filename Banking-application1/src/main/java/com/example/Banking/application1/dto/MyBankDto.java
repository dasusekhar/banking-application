package com.example.Banking.application1.dto;

import com.example.Banking.application1.entity.Deposit;
import com.example.Banking.application1.entity.LoginDetail;
import com.example.Banking.application1.entity.WithDraw;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class MyBankDto {
  private Long id;
  private String name;
  private List<DepositDto> deposit;
  private Long totalAmountAvaliable;
  private String email;
  private Long mobileNumber;
  private String accountNumber;




}
