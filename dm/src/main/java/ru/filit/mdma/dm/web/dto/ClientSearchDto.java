package ru.filit.mdma.dm.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.text.DateFormat;
import java.util.Date;


/**
 * Параметры поиска клиентов
 */
@ApiModel(description = "Параметры поиска клиентов")
@Data
public class ClientSearchDto   {

  private String id;

  private String lastname;

  private String firstname;

  private String patronymic;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date birthDate;

  private String passport;

  private String inn;

}

