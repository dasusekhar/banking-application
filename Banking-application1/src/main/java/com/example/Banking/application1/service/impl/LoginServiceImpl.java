package com.example.Banking.application1.service.impl;

import com.example.Banking.application1.dto.LoginDetailsDto;
import com.example.Banking.application1.dto.MyBankDto;
import com.example.Banking.application1.entity.LoginDetail;
import com.example.Banking.application1.entity.MyBank;
import com.example.Banking.application1.repository.LoginRepository;
import com.example.Banking.application1.repository.MyBankRepository;
import com.example.Banking.application1.service.LoginService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
@Autowired
  private LoginRepository loginRepository;
@Autowired
private MyBankRepository myBankRepository;

  @Override
  public LoginDetail generate(String accountNumber) {
    List<MyBank> myBank=myBankRepository.findAll();
    LoginDetail loginDetail=new LoginDetail();
    myBank.stream().forEach(x->{
      loginDetail.setUserName(x.getName());
      loginDetail.setPassword(x.getName()+123);
      MyBank myBank1=new MyBank();
      myBank1.setId(myBank1.getId());
      loginDetail.setMyBank(myBank1);
      loginRepository.save(loginDetail);
    });
    System.out.println("login generated Succesfully");
    return loginDetail ;
  }

  @Override
  public LoginDetailsDto update(Long id, LoginDetailsDto loginDetailsDto) {
    LoginDetail loginDetail=loginRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    loginDetail.setUserName(loginDetailsDto.getUserName());
    loginDetail.setPassword(loginDetailsDto.getPassword());
    LoginDetail loginDetail2=loginRepository.save(loginDetail);
    LoginDetailsDto loginDetailsDto1=new LoginDetailsDto();
    loginDetailsDto1.setUserName(loginDetail2.getUserName());
    loginDetailsDto1.setPassword(loginDetail2.getPassword());
    System.out.println(loginDetailsDto1);
    return loginDetailsDto1;
  }
 public LoginDetailsDto getAccountDetails(String userName,String password)
 {
   LoginDetail loginDetail=loginRepository.findByuserNameAndPassword(userName,password);
   MyBank myBank=myBankRepository.findByaccountNumber(loginDetail.getMyBank().getAccountNumber());
   LoginDetailsDto loginDetailsDto=new LoginDetailsDto();
   loginDetailsDto.setId(loginDetail.getId());
   loginDetailsDto.setUserName(loginDetail.getUserName());
   loginDetailsDto.setPassword(loginDetail.getPassword());
   MyBankDto myBankDto=new MyBankDto();
   myBankDto.setName(myBank.getName());
   myBankDto.setEmail(myBank.getEmail());
   myBankDto.setMobileNumber(myBank.getMobileNumber());
   loginDetailsDto.setMyBankDto(myBankDto);
   return loginDetailsDto;

 }
}
