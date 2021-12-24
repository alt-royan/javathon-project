package ru.filit.mdma.dm.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * Банковские счета клиента
 */
@ApiModel(description = "Банковские счета клиента")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account  implements Serializable {

  private String number;

  private String clientId;


  public enum TypeEnum {
    PAYMENT("PAYMENT"),
    
    BUDGET("BUDGET"),
    
    TRANSIT("TRANSIT"),
    
    OVERDRAFT("OVERDRAFT");

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

  public enum CurrencyEnum {
    RUR("RUR");

    private String value;

    CurrencyEnum(String value) {
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
    public static CurrencyEnum fromValue(String value) {
      for (CurrencyEnum b : CurrencyEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private CurrencyEnum currency = CurrencyEnum.RUR;


  public enum StatusEnum {
    INACTIVE("INACTIVE"),
    
    ACTIVE("ACTIVE"),
    
    LOCKED("LOCKED"),
    
    CLOSED("CLOSED");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  private Long openDate;

  private Long closeDate;

  private Integer deferment;

}

