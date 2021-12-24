package ru.filit.mdma.dm.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.filit.mdma.dm.model.Account;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Банковские счета клиента
 */
@ApiModel(description = "Банковские счета клиента")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto   {

  @NotNull
  private String number;

  @NotNull
  private String clientId;
  @NotNull
  private Account.TypeEnum type;
  @NotNull
  private Account.CurrencyEnum currency;
  @NotNull
  private Account.StatusEnum status;
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date openDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date closeDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Integer deferment;
  @NotNull
  private String shortcut;

  public void setShortcut(){
    this.shortcut=number.substring(number.length()-4);
  }


}

