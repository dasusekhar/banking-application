package com.example.Banking.application1.condtions;

import com.example.Banking.application1.MailUtil;
import com.example.Banking.application1.entity.MyBank;
import org.springframework.stereotype.Component;

@Component
public class MinimumBalance {
  public static  Long minBalance() throws Exception {
    MyBank myBank=new MyBank();
     Long minBalance=2000l;
     if(myBank.getTotalAmountAvaliable()<minBalance)
     {
       throw new Exception("please maintain minimum Balance in your account"+minBalance);
     }
    return minBalance;
  }

}
