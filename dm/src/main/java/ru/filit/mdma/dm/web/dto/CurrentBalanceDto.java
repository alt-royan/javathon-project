package ru.filit.mdma.dm.web.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.filit.mdma.dm.service.MoneySerializer;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * Значение текущего баланса счета
 */
@ApiModel(description = "Значение текущего баланса счета")
@Data
public class CurrentBalanceDto   {

  @NotNull
  @JsonSerialize(using = MoneySerializer.class)
  private BigDecimal balanceAmount;


}

