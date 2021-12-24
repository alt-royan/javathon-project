package ru.filit.mdma.dm.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Подбитые балансы на счетах на начало месяца
 */
@ApiModel(description = "Подбитые балансы на счетах на начало месяца")
@Data
public class AccountBalance implements Serializable {

  private String accountNumber;

  private Long balanceDate;

  private BigDecimal amount;

}

