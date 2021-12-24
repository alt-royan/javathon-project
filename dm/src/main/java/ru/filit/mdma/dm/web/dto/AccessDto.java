package ru.filit.mdma.dm.web.dto;


import lombok.Data;


/**
 * Права доступа к полям сущностей
 */
@Data
public class AccessDto  {

  private String entity;

  private String property;

}

