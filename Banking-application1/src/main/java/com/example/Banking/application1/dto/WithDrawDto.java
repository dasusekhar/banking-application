package com.example.Banking.application1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class WithDrawDto {

  private Long id;
  private Long withdraw;
  private String transactionId;
  private Long  with_draw_id;

}
