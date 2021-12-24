package ru.filit.mdma.dm.web.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.filit.mdma.dm.model.Operation;
import ru.filit.mdma.dm.service.MoneySerializer;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Банковские операции по счету
 */
@ApiModel(description = "Банковские операции по счету")
@Data

public class OperationDto   {
  @NotNull
  private Operation.TypeEnum type;
  @NotNull
  private String accountNumber;
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private Date operDate;
  @NotNull
  @JsonSerialize(using = MoneySerializer.class)
  private BigDecimal amount;

  private String description;

}

