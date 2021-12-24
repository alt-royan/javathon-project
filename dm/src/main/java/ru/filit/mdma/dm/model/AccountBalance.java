package ru.filit.mdma.dm.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Подбитые балансы на счетах на начало месяца
 */
@Data
public class AccountBalance implements Serializable {

  @NotNull
  private String accountNumber;

  @NotNull
  private Long balanceDate;

  @NotNull
  private BigDecimal amount;

}

