package ru.filit.mdma.dm.web.dto;


import io.swagger.annotations.ApiModel;

import lombok.Data;
import ru.filit.mdma.dm.model.Contact;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.regex.*;


/**
 * Контактные данные клиента
 */
@ApiModel(description = "Контактные данные клиента")
@Data
public class ContactDto   {

  private String id;

  @NotNull
  private String clientId;

  @NotNull
  private Contact.TypeEnum type;

  @NotNull
  private String value;

  private String shortcut;


  public void setShortcut(){
    if(type== Contact.TypeEnum.EMAIL){
      shortcut=value.substring(value.indexOf('@')-1);
    }else if (type== Contact.TypeEnum.PHONE){
      shortcut=value.substring(value.length()-4);
    }
  }

  public void setValue(String value) {
    java.util.regex.Pattern p;
    Matcher m;
    if(type== Contact.TypeEnum.EMAIL){
      p = java.util.regex.Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
      m = p.matcher(value);
      if (m.matches()){
        this.value=value;
      }else{
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
      }
    }else if (type== Contact.TypeEnum.PHONE){
      p = java.util.regex.Pattern.compile("^((\\+7|7|8)+([0-9]){10})$");
      m = p.matcher(value);
      if (m.matches()){
        this.value=value;
      }else{
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
      }
    }
  }

  public static ContactDto fromContact(Contact contact){
    ContactDto contactDto =new ContactDto();
    contactDto.setId(contact.getId());
    contactDto.setClientId(contact.getClientId());
    contactDto.setType(contact.getType());
    contactDto.setValue(contact.getValue());
    contactDto.setShortcut();
    return contactDto;
  }
}

