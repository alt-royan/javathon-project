package ru.filit.mdma.dm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.filit.mdma.dm.exception.WrongDataException;
import ru.filit.mdma.dm.model.Account;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {


    private final URL accountsFileUrl ;

    private final URL balancesFileUrl;

    public AccountService(@Value("${datafiles.accounts}") String accountsFileName, @Value("${datafiles.accounts}") String balancesFileName){
        this.accountsFileUrl = getClass().getClassLoader().getResource(accountsFileName);
        this.balancesFileUrl = getClass().getClassLoader().getResource(balancesFileName);
    }

    @Autowired
    private YamlService yamlService;

    @Autowired
    private OperationService operationService;

    public List<Account> getAccounts(String clientId) throws WrongDataException {
        List<Account> accounts = yamlService.readYaml(accountsFileUrl, Account.class);
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
