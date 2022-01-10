package ru.filit.mdma.dms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.filit.mdma.dms.exception.WrongDataException;
import ru.filit.mdma.dms.web.dto.*;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class Masking {

    private Logger logger = LoggerFactory.getLogger(Masking.class);


    public void maskObject(Object object, List<AccessDto> accessList) throws WrongDataException {
        Field[] fields = object.getClass().getDeclaredFields();
        String entityName;
        if(object instanceof AccountDto){
            entityName="account";
        }else if(object instanceof ClientDto){
            entityName="client";
        }else if(object instanceof ContactDto) {
            entityName = "contact";
        }else if(object instanceof CurrentBalanceDto){
            entityName="currentBalance";
        }else if(object instanceof ClientLevelDto){
            entityName="clientLevel";
        }else if(object instanceof LoanPaymentDto) {
            entityName = "loanPayment";
        }else if(object instanceof OperationDto) {
            entityName = "operation";
        }else {
            logger.warn("Cannot mask "+object.getClass()+". Wrong entity name ");
            throw new WrongDataException("Wrong entity name");
        }
        for (Field field:fields){
            try {
                if (!accessList.contains(new AccessDto(entityName, field.getName()))) {
                    field.setAccessible(true);
                    field.set(object, "****");
                }
            } catch (IllegalAccessException e) {
                throw new WrongDataException(e.getMessage());
            }
        }
    }
}
