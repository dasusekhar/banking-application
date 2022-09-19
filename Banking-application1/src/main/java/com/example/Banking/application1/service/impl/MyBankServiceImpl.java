package com.example.Banking.application1.service.impl;

import com.example.Banking.application1.CsvUtil;
import com.example.Banking.application1.MailUtil;
import com.example.Banking.application1.dto.DepositDto;
import com.example.Banking.application1.dto.MyBankDto;
import com.example.Banking.application1.dto.WithDrawDto;
import com.example.Banking.application1.entity.Deposit;
import com.example.Banking.application1.entity.MyBank;
import com.example.Banking.application1.entity.WithDraw;
import com.example.Banking.application1.repository.DepositRepository;
import com.example.Banking.application1.repository.MyBankRepository;
import com.example.Banking.application1.repository.WithDrawRepository;
import com.example.Banking.application1.service.MyBankService;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBankServiceImpl implements MyBankService {
  @Autowired
  private MyBankRepository myBankRepository;
  @Autowired
  private MailUtil mailUtil;
  @Autowired
  private DepositRepository depositRepository;
  @Autowired
  private WithDrawRepository withDrawRepository;

  @Override
  @Transactional
  public MyBankDto save(MyBankDto myBankDto) throws MessagingException {
    MyBank myBank=new MyBank();
    BeanUtils.copyProperties(myBankDto,myBank);
    List<Deposit>deposits=new ArrayList<>();
    Deposit deposit=new Deposit();
  myBankDto.getDeposit().stream().forEach(x->{
         deposit.setId(x.getId());
         deposit.setDeposit(x.getDeposit());
         deposit.setTransactionId(generateTransactionId());
         deposits.add(deposit);
        // Deposit deposit1=depositRepository.save(deposit);
         myBank.setTotalAmountAvaliable(x.getDeposit());
      });
   // Deposit deposit1=depositRepository.save(deposit);
    BeanUtils.copyProperties(deposit,myBankDto.getDeposit());
    myBank.setAccountNumber(generateAccountNumber());
    myBank.setDeposit(deposits);
    List<DepositDto>depositDtos=new ArrayList<>();
    DepositDto depositDto=new DepositDto() ;
    myBank.getDeposit().stream().forEach(x->{
      depositDto.setId(x.getId());
      depositDto.setDeposit(x.getDeposit());
      depositDto.setTransactionId(x.getTransactionId());
      depositDtos.add(depositDto);
    });
    MyBank myBank1=myBankRepository.save(myBank);
    mailUtil.sendEmail(myBank1.getEmail(),buildMessageAterCreatingTheAccount(myBank));

  BeanUtils.copyProperties(myBank1,myBankDto);
  myBankDto.setDeposit(depositDtos);
  return myBankDto;
  }

  @Override
  public DepositDto depositAmount(String accountNumber,DepositDto depositDto) throws Exception {
    MyBank myBank=myBankRepository.findByaccountNumber(accountNumber);
    List<MyBank>myBanks=myBankRepository.findAll();
    List<Deposit>deposits=new ArrayList<>();
    Deposit deposit=new Deposit();
    myBanks.stream().filter(x->x.getId().equals(myBank.getId())).forEach(x->{
      x.setId(myBank.getId());
      deposit.setDeposit(depositDto.getDeposit());
      deposit.setTransactionId(generateTransactionId());
      deposits.add(deposit);
      x.setTotalAmountAvaliable(totalBalance(myBank.getAccountNumber(),depositDto.getDeposit()));
      x.setDeposit(deposits);
      myBankRepository.save(x);
    });
    depositDto.setId(deposit.getId());
    depositDto.setDeposit(deposit.getDeposit());
    depositDto.setTransactionId(deposit.getTransactionId());
     return depositDto;

  }

  @Override
  public MyBankDto getDetails(String accountNumber) {
    MyBank myBank=myBankRepository.findByaccountNumber(accountNumber);
    MyBankDto myBankDto=new MyBankDto();
    myBankDto.setName(myBank.getName());
    myBankDto.setMobileNumber(myBank.getMobileNumber());
    myBankDto.setEmail(myBank.getEmail());
    myBankDto.setTotalAmountAvaliable(myBank.getTotalAmountAvaliable());
    return myBankDto;
  }

  @Override
  public MyBankDto getDetailsId(Long id) {
    MyBank my =myBankRepository.findById(id).get();
    MyBankDto myBankDto=new MyBankDto();
    myBankDto.setEmail(my.getEmail());
    myBankDto.setName(my.getName());
    myBankDto.setTotalAmountAvaliable(my.getTotalAmountAvaliable());
    return myBankDto ;
  }

  @Override
  public WithDrawDto withdraw(String accountNumber,WithDrawDto withDrawDto) {
   MyBank myBank=myBankRepository.findByaccountNumber(accountNumber);
   WithDraw withDraw=new WithDraw();
   withDraw.setId(withDrawDto.getId());
   withDraw.setWithdraw(withDrawDto.getWithdraw());
   withDraw.setWith_draw_id(myBank.getId());
   withDraw.setTransactionId(generateTransactionId());
   WithDraw withDraw1=withDrawRepository.save(withDraw);
   myBank.setTotalAmountAvaliable(totalBalanceAfterWithDraw(accountNumber,withDraw1.getWithdraw()));
   myBankRepository.save(myBank);
   withDrawDto.setId(withDraw1.getId());
   withDrawDto.setWithdraw(withDrawDto.getWithdraw());
   withDrawDto.setTransactionId(withDrawDto.getTransactionId());
    return withDrawDto;
  }


  private String generateAccountNumber()
  {
    Random random=new Random();
    String ids=String.valueOf(random.nextInt());
   return ids;
  }
  private Long totalBalance(String accountNumber,Long deposit)
  {
   MyBank bank=myBankRepository.findByaccountNumber(accountNumber);
   MyBank myBank=new MyBank();
   Long number=bank.getTotalAmountAvaliable();
   myBank.setTotalAmountAvaliable(number += deposit);
   MyBank updatedBalance=myBankRepository.save(myBank);
   return updatedBalance.getTotalAmountAvaliable();
  }

  private Long totalBalanceAfterWithDraw(String accountNumber,Long withdraw)
  {
    MyBank bank=myBankRepository.findByaccountNumber(accountNumber);
    MyBank myBank=new MyBank();
    Long number=bank.getTotalAmountAvaliable();
    myBank.setTotalAmountAvaliable(number-=withdraw);
    MyBank updatedBalance=myBankRepository.save(myBank);
    return updatedBalance.getTotalAmountAvaliable();
  }
  private String generateTransactionId()
  {
    Random random=new Random();
    String ids=String.valueOf("txnId"+random.nextLong());
    return ids;
  }

  private String buildMessageAterCreatingTheAccount(MyBank myBank) {

    String mailBody = "Dear " + myBank.getName() + "," + "\n" + "Congratullations,   you got account  number  is  :   "
        + myBank.getAccountNumber() + "**" + "  do not share any one  " + "\n" + " your are deposited  :"+myBank.getDeposit().stream().map(Deposit::getDeposit)+

         "your  transcationId is  : "+myBank.getDeposit().stream().map(Deposit::getTransactionId) + "\n" + " Login details we will share  your registered email  within 2 working days  "+"\n"+myBank.getName()+"total balance is :"+myBank.getTotalAmountAvaliable();
    System.out.println(mailBody);
    return mailBody;
  }
  private String buildMessageWhennotSuccessfull(MyBank myBank) {

    String mailBody = "Dear " + myBank.getName() + "," + "\n" + "statement of your saving account ending with   "
        + myBank.getAccountNumber() + "**" + " has been generated" + "\n" + "dues amount :"

        + "your transcationId is  :"+myBank.getName() + "\n" + "Thanks for choosing  MyBank  "+"\n"+myBank.getName()+"total balance is :"+myBank.getTotalAmountAvaliable();
    System.out.println(mailBody);
    return mailBody;
  }

  public ByteArrayInputStream load2() {
    List<MyBank> tutorials = myBankRepository.findAll();
    List<Object[]> list2 = new ArrayList<>();
    Object headers[] = {"Id", "Name", "mobileNumber", "Email", "AccountNumber", "TransctionId",
        "Balance"};
    list2.add(headers);
    for (MyBank bandwidthChange : tutorials) {
      Object rowData[] = {bandwidthChange.getId(), bandwidthChange.getName(),
          bandwidthChange.getMobileNumber(), bandwidthChange.getEmail(),
          bandwidthChange.getAccountNumber(), bandwidthChange.getTotalAmountAvaliable()};
      list2.add(rowData);
    }
    //
    ByteArrayInputStream in = CsvUtil.DataToCSV(list2);
    return in;
  }
  public List<DepositDto> getBankStatement(String accountNumber) {
    MyBank myBank=myBankRepository.findByaccountNumber(accountNumber);
    List<DepositDto>depositDtos=new ArrayList<>();
    List<Deposit>deposits=depositRepository.findAll();
    Optional.of(deposits).orElse(Collections.emptyList()).forEach((Deposit deposit) -> {
      DepositDto depositDto=new DepositDto();
      depositDto.setDeposit(deposit.getDeposit());
      depositDto.setTransactionId(depositDto.getTransactionId());
      depositDtos.add(depositDto);
    });
    return depositDtos;
  }

}
