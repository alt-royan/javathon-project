package ru.filit.mdma.dms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filit.mdma.dms.exception.ClientException;
import ru.filit.mdma.dms.model.Role;
import ru.filit.mdma.dms.web.dto.*;

import java.util.List;

@Service
public class DmsService {

    @Autowired
    private DmClient dmClient;

    private final String accessVersion = "2";

    private List<AccessDto> getAccess(Role role) throws ClientException {

        return dmClient.getAccess(new AccessRequestDto(role.getValue(), accessVersion));
    }

    public List<ClientDto> findClients(ClientSearchDto clientSearchDto, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        return dmClient.searchClients(clientSearchDto);
    }

    public List<ContactDto> getClientContacts(ClientIdDto id, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        return dmClient.getContacts(id);
    }

    public List<AccountDto> getAccounts(ClientIdDto id, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        return dmClient.getAccounts(id);
    }

    public CurrentBalanceDto getCurrentBalance(AccountNumberDto accountNumber, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        return dmClient.getBalance(accountNumber);
    }

    public List<OperationDto> getLastOperations(OperationSearchDto operationSearchDto, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        return dmClient.getOperations(operationSearchDto);
    }

    public ContactDto saveContact(ContactDto contactDto, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        return dmClient.saveContact(contactDto);
    }

    public ClientLevelDto calculateClientLevel(ClientIdDto clientId, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        return dmClient.getClientLevel(clientId);
    }

    public LoanPaymentDto getOverdraft(AccountNumberDto accountNumber, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        return dmClient.getOverdraft(accountNumber);
    }
}
