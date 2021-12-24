package ru.filit.mdma.dm.web.dto;


import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.*;

/**
 * Запрос по accountNumber
 */
@Getter
@Setter
public class AccountNumberDto   {

  @NotNull
  private String accountNumber;

}

