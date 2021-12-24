package ru.filit.mdma.dm.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.filit.mdma.dm.model.Client;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Клиент банка, Физ. лицо
 */
@ApiModel(description = "Клиент банка, Физ. лицо")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto   {

  @NotNull
  private String id;
  @NotNull
  private String lastname;
  @NotNull
  private String firstname;

  private String patronymic;
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date birthDate;
  @NotNull
  private String passportSeries;
  @NotNull
  private String passportNumber;
  @NotNull
  private String inn;

  private String address;

  public static ClientDto fromClient(Client client){
    ClientDto clientDto =new ClientDto();
    clientDto.setId(client.getId());
    clientDto.setLastname(client.getLastname());
    clientDto.setFirstname(client.getFirstname());
    clientDto.setPatronymic(client.getPatronymic());
    clientDto.setBirthDate(new Date(client.getBirthDate()));
    clientDto.setPassportSeries(client.getPassportSeries());
    clientDto.setPassportNumber(client.getPassportNumber());
    clientDto.setInn(client.getInn());
    clientDto.setAddress(client.getAddress());
    return clientDto;
  }

}

