package ru.filit.mdma.dm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filit.mdma.dm.exception.WrongDataException;
import ru.filit.mdma.dm.model.Account;
import ru.filit.mdma.dm.model.AccountBalance;
import ru.filit.mdma.dm.model.ClientLevel;
import ru.filit.mdma.dm.model.Operation;
import ru.filit.mdma.dm.web.dto.ClientLevelDto;
import ru.filit.mdma.dm.web.dto.CurrentBalanceDto;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final String accountsFileName ="src/main/resources/datafiles/accounts.yml";
    private File accountsFile;

    private final String balancesFileName ="datafiles/balances.yml";
    private File balancesFile;

    public AccountService(){
        this.accountsFile=new File(accountsFileName);
        this.balancesFile=new File(balancesFileName);
    }

    @Autowired
    private YamlService yamlService;

    @Autowired
    private OperationService operationService;

    public List<Account> getAccounts(String clientId) throws WrongDataException {
        List<Account> accounts = yamlService.readYaml(accountsFile, Account.class);
        return accounts.stream().filter(a->a.getClientId().equals(clientId)).collect(Collectors.toList());
    }

    /*public CurrentBalanceDto getBalance(String accountNumber, Date date) throws WrongDataException {
        CurrentBalanceDto currentBalanceDto=new CurrentBalanceDto();
        List<Operation> operations =operationService.getOperations(accountNumber);
        List<AccountBalance> balances =getBalances(accountNumber);
        AccountBalance current = balances.stream().filter(b->
            new Date(b.getBalanceDate()).getMonth() == date.getMonth()).findFirst().get();
        for (Operation o:operations){
            currentBalanceDto.setBalanceAmount(current.getAmount().add(o.getAmount()));
        }
        return currentBalanceDto;
    }

    private List<AccountBalance> getBalances(String accountNumber) throws WrongDataException {
        List<AccountBalance> balances =yamlService.readYaml(balancesFile, AccountBalance.class);
        List<AccountBalance> myBalances=balances.stream().filter(b->b.getAccountNumber().equals(accountNumber)).collect(Collectors.toList());
        return myBalances;
    }

    public ClientLevelDto getClientLevel(String clientId) throws WrongDataException {
        List<CurrentBalanceDto> balances = getAccounts(clientId);
    }

    private ClientLevel svo(String accountNumber) throws WrongDataException {
        for
        getBalance(accountNumber);
    }*/


}
