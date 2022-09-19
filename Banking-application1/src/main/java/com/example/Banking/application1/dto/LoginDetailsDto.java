package com.example.Banking.application1.dto;

import com.example.Banking.application1.entity.LoginDetail;
import com.example.Banking.application1.entity.MyBank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
@ToString
public class LoginDetailsDto {
  private Long id;
  private String  userName;
  private String password;
  private MyBankDto  myBankDto;

}
