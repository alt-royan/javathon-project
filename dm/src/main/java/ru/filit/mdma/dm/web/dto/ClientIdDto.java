package ru.filit.mdma.dm.web.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * Запрос по clientId
 */
@ApiModel(description = "Запрос по clientId")
@Data
public class ClientIdDto   {

  private String clientId;

}

