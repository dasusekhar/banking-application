package com.example.Banking.application1.service;

import com.example.Banking.application1.dto.DepositDto;
import com.example.Banking.application1.dto.MyBankDto;
import com.example.Banking.application1.dto.WithDrawDto;
import java.io.InputStream;
import java.util.List;
import javax.mail.MessagingException;

public interface MyBankService {

  MyBankDto save(MyBankDto myBankDto) throws MessagingException;


  DepositDto depositAmount(String accountNumber, DepositDto depositDto) throws Exception;

  MyBankDto getDetails(String accountNumber);

  MyBankDto getDetailsId(Long id);

  WithDrawDto withdraw(String accountNumber,WithDrawDto withDrawDto);

  InputStream load2();

  List<DepositDto> getBankStatement(String accountNumber);
}
