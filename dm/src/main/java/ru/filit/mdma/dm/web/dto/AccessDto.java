package ru.filit.mdma.dm.web.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;



/**
 * Права доступа к полям сущностей
 */
@ApiModel(description = "Права доступа к полям сущностей")
@Data
public class AccessDto  {
  private String entity;

  private String property;

}

