package ru.filit.mdma.crm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.filit.mdma.crm.web.dto.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrmService {

    @Autowired
    private DmClient dmClient;

    public List<ClientDto> searchClients(ClientSearchDto client) throws ClientException {
        return dmClient.searchClients(client);
    }

    public ClientInfoDto getClientInfo(ClientIdDto clientId) throws ClientException {
        ClientSearchDto clientSearchDto=new ClientSearchDto();
        clientSearchDto.setId(clientId.getClientId());
        List<ClientDto> client = dmClient.searchClients(clientSearchDto);
        if(client.isEmpty()){
            Map<String, String> error =new HashMap<>();
            error.put("timestamp", new Date().toString());
            error.put("status", HttpStatus.NOT_FOUND.toString());
            error.put("message", "Client not found");
            try {
                throw new ClientException(HttpStatus.NOT_FOUND, error );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        List<ContactDto> contacts =dmClient.getContacts(clientId);
        List<AccountDto> accounts =dmClient.getAccounts(clientId);
        return new ClientInfoDto(client.get(0),contacts,accounts);

    }

    public List<OperationDto> getLastOperations(AccountNumberDto accountNumber, int n) throws ClientException {
        OperationSearchDto operationSearchDto =new OperationSearchDto();
        operationSearchDto.setQuantity(n);
        operationSearchDto.setAccountNumber(accountNumber.getAccountNumber());
        return dmClient.getOperations(operationSearchDto);
    }

    public ContactDto saveContact(ContactDto contact) throws ClientException {
        return dmClient.saveContact(contact);
    }

    public ClientLevelDto getClientLevel(ClientIdDto clientId) throws ClientException {
        return dmClient.getClientLevel(clientId);
    }

    public LoanPaymentDto getOverdraft(AccountNumberDto accountNumber) throws ClientException {
        return dmClient.getOverdraft(accountNumber);
    }
}
