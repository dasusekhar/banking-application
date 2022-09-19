package com.example.Banking.application1.service;

import com.example.Banking.application1.dto.LoginDetailsDto;
import com.example.Banking.application1.entity.LoginDetail;

public interface LoginService {

  LoginDetail generate(String accountNumber);

  LoginDetailsDto update(Long id, LoginDetailsDto loginDetailsDto);

  LoginDetailsDto getAccountDetails(String userName, String password);
}
