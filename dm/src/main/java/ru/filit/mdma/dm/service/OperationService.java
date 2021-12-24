package ru.filit.mdma.dm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filit.mdma.dm.exception.WrongDataException;
import ru.filit.mdma.dm.model.Account;
import ru.filit.mdma.dm.model.Operation;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationService {

    @Autowired
    private YamlService yamlService;

    private final String operationsFileName ="/datafiles/operations.yml";
    private File operationsFile;

    public OperationService(){
        this.operationsFile=new File(operationsFileName);
    }

    public List<Operation> getOperations(String accountNumber) throws WrongDataException {
        List<Operation> operations = yamlService.readYaml(operationsFile, Operation.class);
        return operations.stream().filter(o->o.getAccountNumber().equals(accountNumber)).collect(Collectors.toList());
    }

}
