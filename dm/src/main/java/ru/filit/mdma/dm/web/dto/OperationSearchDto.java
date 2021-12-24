package ru.filit.mdma.dm.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;

import lombok.Data;
import ru.filit.mdma.dm.service.MoneySerializer;

import javax.validation.constraints.NotNull;


/**
 * Запрос операций по счету
 */
@ApiModel(description = "Запрос операций по счету")
@Data
public class OperationSearchDto   {

  @NotNull
  private String accountNumber;


  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Integer quantity;

}

