package ru.filit.mdma.crm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.filit.mdma.crm.web.dto.*;

import java.util.*;

@Service
public class CrmService {

    @Autowired
    private DmClient dmClient;

    public List<ClientDto> searchClients(ClientSearchDto client, Collection<? extends GrantedAuthority> authorities) throws ClientException {
        return dmClient.searchClients(client, authorities);
    }

    public ClientInfoDto getClientInfo(ClientIdDto clientId, Collection<? extends GrantedAuthority> authorities) throws ClientException {
        ClientSearchDto clientSearchDto=new ClientSearchDto();
        clientSearchDto.setId(clientId.getClientId());
        List<ClientDto> client = dmClient.searchClients(clientSearchDto, authorities);
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
        List<ContactDto> contacts =dmClient.getContacts(clientId, authorities);
        List<AccountDto> accounts =dmClient.getAccounts(clientId, authorities);
        return new ClientInfoDto(client.get(0),contacts,accounts);

    }

    public List<OperationDto> getLastOperations(AccountNumberDto accountNumber, int n, Collection<? extends GrantedAuthority> authorities) throws ClientException {
        OperationSearchDto operationSearchDto =new OperationSearchDto();
        operationSearchDto.setQuantity(n);
        operationSearchDto.setAccountNumber(accountNumber.getAccountNumber());
        return dmClient.getOperations(operationSearchDto, authorities);
    }

    public ContactDto saveContact(ContactDto contact,Collection<? extends GrantedAuthority> authorities) throws ClientException {
        return dmClient.saveContact(contact, authorities);
    }

    public ClientLevelDto getClientLevel(ClientIdDto clientId, Collection<? extends GrantedAuthority> authorities) throws ClientException {
        return dmClient.getClientLevel(clientId, authorities);
    }

    public LoanPaymentDto getOverdraft(AccountNumberDto accountNumber, Collection<? extends GrantedAuthority> authorities) throws ClientException {
        return dmClient.getOverdraft(accountNumber, authorities);
    }
}
