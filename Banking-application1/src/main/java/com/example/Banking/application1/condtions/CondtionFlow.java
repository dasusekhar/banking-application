package com.example.Banking.application1.condtions;

import com.example.Banking.application1.entity.MyBank;

public class CondtionFlow {

  public static Long  maximumDeposit(Long balance) throws Exception {
    if(balance>120000)
    {
      throw new Exception("deposit not allowed beacasuse reached the limit");
    }
    return balance;
  }

}
