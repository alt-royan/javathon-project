package ru.filit.mdma.dms.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * Права доступа к полям сущностей
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessDto  {

  private String entity;

  private String property;

}

