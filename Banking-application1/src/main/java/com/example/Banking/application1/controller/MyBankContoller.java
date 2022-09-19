package com.example.Banking.application1.controller;

import com.example.Banking.application1.dto.DepositDto;
import com.example.Banking.application1.dto.MyBankDto;
import com.example.Banking.application1.dto.WithDrawDto;
import com.example.Banking.application1.entity.MyBank;
import com.example.Banking.application1.service.MyBankService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-bank")
@CrossOrigin(origins = "http://localhost:4200")
public class MyBankContoller {
  @Autowired
  private MyBankService myBankService;
  @PostMapping("/create-account")
  public ResponseEntity<MyBankDto>save(@RequestBody MyBankDto myBankDto) throws MessagingException {
    MyBankDto save=myBankService.save(myBankDto);

    return new ResponseEntity<>(save, HttpStatus.CREATED);
  }
  @PutMapping("/deposit-amount/{accountNumber}")
  public  ResponseEntity<DepositDto>depositAmount(@PathVariable String accountNumber,@RequestBody
      DepositDto depositDto)
      throws Exception {
    DepositDto updatesAmount=myBankService.depositAmount(accountNumber,depositDto);
    return new ResponseEntity<>(updatesAmount,HttpStatus.CREATED);
  }
  @GetMapping("/getDetails/{accountNumber}")
  public  ResponseEntity<MyBankDto>getDetails(@PathVariable String accountNumber) {
    MyBankDto updatesAmounts = myBankService.getDetails(accountNumber);
    return new ResponseEntity<>(updatesAmounts, HttpStatus.CREATED);
  }
  @GetMapping("/getDetailId/{id}")
  public  ResponseEntity<MyBankDto>getDetailsId(@PathVariable Long id) {
    MyBankDto updatesAmountss = myBankService.getDetailsId(id);
    return new ResponseEntity<>(updatesAmountss, HttpStatus.OK);
  }
  @PutMapping("/withDraw/{accountNumber}")
public  ResponseEntity<WithDrawDto>withDraw(@PathVariable String accountNumber,
     @RequestBody  WithDrawDto withDrawDto)
{
  WithDrawDto withDrawAmount=myBankService.withdraw(accountNumber,withDrawDto);
  return new ResponseEntity<>(withDrawAmount,HttpStatus.CREATED);
}
  @GetMapping("/download-csvFile")
  public ResponseEntity<Resource> getFile1() throws IOException, InvalidFormatException {
    File outputFile = new File("data.csv");
    InputStreamResource file = new InputStreamResource(myBankService.load2());
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + outputFile)
        .contentType(MediaType.parseMediaType("application/csv")).body(file);
  }
  @GetMapping("/get-bankstatment/{accountNumber}")
  public  ResponseEntity<List<DepositDto>> getBankStatement(@PathVariable String accountNumber)
  {
    List<DepositDto> getStat=myBankService.getBankStatement(accountNumber);
    return new ResponseEntity<>(getStat,HttpStatus.OK);
  }


}
