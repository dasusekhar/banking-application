package com.example.Banking.application1.controller;

import com.example.Banking.application1.dto.LoginDetailsDto;
import com.example.Banking.application1.entity.LoginDetail;
import com.example.Banking.application1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login-request")
public class LoginController {
  @Autowired
  private LoginService loginService;
  @GetMapping("/getLoginDetails/{accountNumber}")
  public ResponseEntity<LoginDetail>generate(@PathVariable  String accountNumber)
  {
    LoginDetail gene=loginService.generate(accountNumber);
    return new ResponseEntity<>(gene, HttpStatus.OK);
  }
  @PutMapping("/loginUpdate/{id}")
  public ResponseEntity<LoginDetailsDto>updateLoginDetails(@PathVariable Long id,@RequestBody LoginDetailsDto loginDetailsDto)
  {
    LoginDetailsDto update=loginService.update(id,loginDetailsDto);
    return new ResponseEntity<>(update,HttpStatus.CREATED);
  }
  @GetMapping("/getAccount/{userName}/{password}")
  public ResponseEntity<LoginDetailsDto>getAccountDetial(@PathVariable String userName,@PathVariable String password)
  {
    LoginDetailsDto get=loginService.getAccountDetails(userName,password);
    return new ResponseEntity<>(get,HttpStatus.OK);
  }



}
