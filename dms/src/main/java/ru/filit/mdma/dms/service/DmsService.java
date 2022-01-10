package ru.filit.mdma.dms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filit.mdma.dms.exception.ClientException;
import ru.filit.mdma.dms.exception.WrongDataException;
import ru.filit.mdma.dms.model.Role;
import ru.filit.mdma.dms.web.dto.*;

import java.util.List;

@Service
public class DmsService {

    @Autowired
    private DmClient dmClient;
    @Autowired
    private Masking masking;

    private final String accessVersion = "2";

    private List<AccessDto> getAccess(Role role) throws ClientException {

        return dmClient.getAccess(new AccessRequestDto(role.getValue(), accessVersion));
    }

    public List<ClientDto> findClients(ClientSearchDto clientSearchDto, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        List<ClientDto> clients = dmClient.searchClients(clientSearchDto);
        clients.forEach((c)-> {
            try {
                masking.maskObject(c,accessList);
            } catch (WrongDataException e) {
                e.printStackTrace();
            }
        });
        return clients;
    }

    public List<ContactDto> getClientContacts(ClientIdDto id, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        List<ContactDto> contacts = dmClient.getContacts(id);
        contacts.forEach((c)-> {
            try {
                masking.maskObject(c,accessList);
            } catch (WrongDataException e) {
                e.printStackTrace();
            }
        });
        return contacts;
    }

    public List<AccountDto> getAccounts(ClientIdDto id, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        List<AccountDto> accounts = dmClient.getAccounts(id);
        accounts.forEach((a)-> {
            try {
                masking.maskObject(a,accessList);
            } catch (WrongDataException e) {
                e.printStackTrace();
            }
        });
        return accounts;
    }

    public CurrentBalanceDto getCurrentBalance(AccountNumberDto accountNumber, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        CurrentBalanceDto currentBalance =  dmClient.getBalance(accountNumber);
        try {
            masking.maskObject(currentBalance, accessList);
        } catch (WrongDataException e) {
            e.printStackTrace();
        }
        return currentBalance;
    }

    public List<OperationDto> getLastOperations(OperationSearchDto operationSearchDto, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        List<OperationDto> operations =  dmClient.getOperations(operationSearchDto);
        operations.forEach((o)-> {
            try {
                masking.maskObject(o,accessList);
            } catch (WrongDataException e) {
                e.printStackTrace();
            }
        });
        return operations;
    }

    public ContactDto saveContact(ContactDto contactDto, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        ContactDto contact = dmClient.saveContact(contactDto);
        try {
            masking.maskObject(contact, accessList);
        } catch (WrongDataException e) {
            e.printStackTrace();
        }
        return contact;
    }

    public ClientLevelDto calculateClientLevel(ClientIdDto clientId, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        ClientLevelDto clientLevel =  dmClient.getClientLevel(clientId);
        try {
            masking.maskObject(clientLevel,accessList);
        } catch (WrongDataException e) {
            e.printStackTrace();
        }
        return clientLevel;
    }

    public LoanPaymentDto getOverdraft(AccountNumberDto accountNumber, Role role) throws ClientException {
        List<AccessDto> accessList=getAccess(role);
        LoanPaymentDto loanPayment =  dmClient.getOverdraft(accountNumber);
        try {
            masking.maskObject(loanPayment, accessList);
        } catch (WrongDataException e) {
            e.printStackTrace();
        }
        return  loanPayment;
    }
}
