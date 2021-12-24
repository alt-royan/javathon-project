package ru.filit.mdma.dm.web.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import ru.filit.mdma.dm.service.MoneySerializer;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Начисленные платежи по кредиту
 */
@ApiModel(description = "Начисленные платежи по кредиту")
public class LoanPaymentDto   {

  @NotNull
  @JsonSerialize(using = MoneySerializer.class)
  private BigDecimal amount;

}

