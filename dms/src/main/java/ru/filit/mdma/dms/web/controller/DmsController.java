package ru.filit.mdma.dms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.filit.mdma.dms.exception.ClientException;
import ru.filit.mdma.dms.exception.WrongDataException;
import ru.filit.mdma.dms.model.Role;
import ru.filit.mdma.dms.service.DmsService;
import ru.filit.mdma.dms.web.dto.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping()
public class DmsController {
    @Autowired
    private DmsService dmsService;

    private Role getRole(HttpHeaders headers) throws WrongDataException {
        try {
            Role role = Role.valueOf(headers.getFirst("CRM-User-Role"));
            return role;
        }catch (IllegalArgumentException | NullPointerException e){
            throw new WrongDataException("Wrong user role");
        }
    }


    //Поиск клиента по параметрам
    @PostMapping("/client")
    public List<ClientDto> searchClients(@RequestBody ClientSearchDto clientSearchDto, @RequestHeader HttpHeaders headers) throws WrongDataException, ClientException {
        return dmsService.findClients(clientSearchDto, getRole(headers));
    }

    //Запрос контактов клиента
    @PostMapping("/client/contact")
    public List<ContactDto> searchContacts(@RequestBody ClientIdDto id, @RequestHeader HttpHeaders headers) throws WrongDataException, ClientException {
        return dmsService.getClientContacts(id,getRole(headers));
    }

    //Запрос счетов коиента
    @PostMapping("/client/account")
    public List<AccountDto> searchAccounts(@RequestBody ClientIdDto id, @RequestHeader HttpHeaders headers) throws WrongDataException, ClientException {
        return dmsService.getAccounts(id, getRole(headers));
    }

    //Запрос баланса по счёту
    @PostMapping("/client/account/balance")
    public CurrentBalanceDto getCurrentBalance(@RequestBody AccountNumberDto accountNumber, @RequestHeader HttpHeaders headers) throws WrongDataException, ClientException {
        return dmsService.getCurrentBalance(accountNumber, getRole(headers));
    }

    //Запрос операций по счёту
    @PostMapping("/client/account/operation")
    public List<OperationDto> getOperations(@RequestBody OperationSearchDto operationSearchDto, @RequestHeader HttpHeaders headers) throws WrongDataException, ClientException {
        return dmsService.getLastOperations(operationSearchDto, getRole(headers));
    }

    //Сохранение Контактов клиента
    @PostMapping("/client/contact/save")
    public ContactDto saveContact(@RequestBody ContactDto contactDto, @RequestHeader HttpHeaders headers) throws WrongDataException, ClientException {
        return dmsService.saveContact(contactDto,getRole(headers));
    }

    //Получение уровня клиента
    @PostMapping("/client/level")
    public ClientLevelDto clientLevel(@RequestBody ClientIdDto clientId, @RequestHeader HttpHeaders headers) throws WrongDataException, ClientException {
        return dmsService.calculateClientLevel(clientId, getRole(headers));
    }

    //Получение процентов по кредиту
    @PostMapping("/client/account/loan-payment")
    public LoanPaymentDto clientLevel(@RequestBody AccountNumberDto accountNumber, @RequestHeader HttpHeaders headers) throws WrongDataException, ClientException {
        return dmsService.getOverdraft(accountNumber,getRole(headers));
    }


}
