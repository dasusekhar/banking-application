package com.example.Banking.application1.statements;

import com.example.Banking.application1.entity.MyBank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BankStatement {

  public static List<MyBank> getStatement(List<MyBank>myBankList)
  {
    List<MyBank>myBanks=new ArrayList<>();
    myBankList.stream().collect(Collectors.toList()).forEach(x->{
      MyBank myBank1=new MyBank();
     // myBank1.setDeposit(x.getDeposit());
    //  myBank1.setTransactionId(x.getTransactionId());
    //  myBank1.setWithdraw(x.getWithdraw());
      myBank1.setTotalAmountAvaliable(x.getTotalAmountAvaliable());
      myBanks.add(myBank1);
    });

    return myBanks;
  }


}
