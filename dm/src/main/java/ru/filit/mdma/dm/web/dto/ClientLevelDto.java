package ru.filit.mdma.dm.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.filit.mdma.dm.model.ClientLevel;
import ru.filit.mdma.dm.service.MoneySerializer;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * Уровни клиентов
 */
@ApiModel(description = "Уровни клиентов")
@Data
public class ClientLevelDto   {

  @NotNull
  private ClientLevel level;
  @NotNull
  private String accountNumber;
  @NotNull
  @JsonSerialize(using = MoneySerializer.class)
  private BigDecimal avgBalance;

}

