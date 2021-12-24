package ru.filit.mdma.dm.model;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


/**
 * Клиент банка, Физ. лицо
 */
@ApiModel(description = "Клиент банка, Физ. лицо")
@Data
public class Client implements Serializable {

  private String id;

  private String lastname;

  private String firstname;

  private String patronymic;

  private Long birthDate;

  private String passportSeries;

  private String passportNumber;

  private String inn;

  private String address;

}

