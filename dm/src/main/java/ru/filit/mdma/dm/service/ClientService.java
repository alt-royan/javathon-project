package ru.filit.mdma.dm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filit.mdma.dm.exception.WrongDataException;
import ru.filit.mdma.dm.model.Client;
import ru.filit.mdma.dm.web.dto.ClientSearchDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private YamlService yamlService;

    private final String filename ="src/main/resources/datafiles/clients.yml";
    private File file;

    public ClientService(){
        this.file=new File(filename);
    }

    public Client findClientById(String id) throws WrongDataException {
        List<Client> clients =yamlService.readYaml(file, Client.class);
        return clients.stream().filter((c)-> Objects.equals(c.getId(), id)).findFirst().orElseThrow(()->new NullPointerException("No client with id:"+id));
    }

    public List<Client> findClients(ClientSearchDto client) throws WrongDataException {
        List<Client> clients =yamlService.readYaml(file, Client.class);
        return clients.stream().filter((c)->{
            boolean b=true;
            if(client.getId()!=null) b=b && (Objects.equals(client.getId(), c.getId()));
            if(client.getLastname()!=null) b=b && (Objects.equals(client.getLastname(), c.getLastname()));
            if(client.getFirstname()!=null) b=b && (Objects.equals(client.getFirstname(), c.getFirstname()));
            if(client.getPatronymic()!=null) b=b && (Objects.equals(client.getPatronymic(), c.getPatronymic()));
            if(client.getBirthDate()!=null) b=b && (Objects.equals(client.getBirthDate().getTime(), c.getBirthDate()));
            if(client.getPassport()!=null) b=b && (Objects.equals(client.getPassport(), c.getPassportSeries()+" "+c.getPassportNumber()));
            if(client.getInn()!=null) b=b && (Objects.equals(client.getInn(), c.getInn()));
            return b;
        }).collect(Collectors.toList());
    }
}
