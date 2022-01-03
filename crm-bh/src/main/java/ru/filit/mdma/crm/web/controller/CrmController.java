package ru.filit.mdma.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.filit.mdma.crm.service.ClientException;
import ru.filit.mdma.crm.service.CrmService;
import ru.filit.mdma.crm.web.dto.*;

import java.util.List;

@RestController
@RequestMapping
public class CrmController {

    @Autowired
    private CrmService crmService;

    /**
     * Поиск клиентов по фильтру
     */
    @PostMapping("client/find")
    public List<ClientDto> searchClients(@RequestBody ClientSearchDto client) throws ClientException {
     return crmService.searchClients(client);
    }

    /**
     * Поиск информации о клиенте по id
     */
    @PostMapping("/client")
    public ClientInfoDto getClientInfo(@RequestBody ClientIdDto id) throws ClientException {
        return crmService.getClientInfo(id);
    }

    /**
     * Получение последних 3 операций по счёту
     */
    @PostMapping("/client/account/last-operations")
    public List<OperationDto> getOperations(@RequestBody AccountNumberDto accountNumber) throws ClientException {
        return crmService.getLastOperations(accountNumber ,3);
    }

    /**
     * Сохранение контакта клиента
     */
    @PostMapping("/client/contact/save")
    public ContactDto saveContact(@RequestBody ContactDto contact) throws ClientException {
        return crmService.saveContact(contact);
    }

    /**
     * Получение уровня клиента
     */
    @PostMapping("/client/level")
    public ClientLevelDto getClientLevel(@RequestBody ClientIdDto id) throws ClientException {
        return crmService.getClientLevel(id);
    }

    /**
     * Получение суммы процентных платежей по счету Овердрафт
     */
    @PostMapping("/client/account/loan-payment")
    public LoanPaymentDto getOverdraft(@RequestBody AccountNumberDto accountNumber) throws ClientException {
        return crmService.getOverdraft(accountNumber);
    }


}
