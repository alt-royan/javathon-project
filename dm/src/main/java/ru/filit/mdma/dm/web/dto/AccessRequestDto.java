package ru.filit.mdma.dm.web.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Запрос прав доступа для роли
 */
@ApiModel(description = "Запрос прав доступа для роли")
@Data
public class AccessRequestDto   {
  private String role;

  private String version;


}

