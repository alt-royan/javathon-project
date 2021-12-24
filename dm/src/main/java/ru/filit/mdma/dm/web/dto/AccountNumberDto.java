package ru.filit.mdma.dm.web.dto;


import io.swagger.annotations.ApiModel;

import lombok.Data;



import javax.validation.constraints.*;

/**
 * Запрос по accountNumber
 */
@ApiModel(description = "Запрос по accountNumber")
@Data
public class AccountNumberDto   {

  @NotNull
  private String accountNumber;


}

