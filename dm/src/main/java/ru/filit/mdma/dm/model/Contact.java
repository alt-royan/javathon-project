package ru.filit.mdma.dm.model;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.filit.mdma.dm.web.dto.ContactDto;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Контактные данные клиента
 */
@ApiModel(description = "Контактные данные клиента")
@Data
public class Contact  implements Serializable {

  private String id;

  private String clientId;

  public enum TypeEnum {
    PHONE("PHONE"),
    
    EMAIL("EMAIL");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  private String value;

  public static Contact fromDto(ContactDto contactDto){
    Contact contact=new Contact();
    contact.clientId= contactDto.getClientId();
    contact.type=contactDto.getType();
    contact.value=contactDto.getValue();
    return contact;
  }

}

