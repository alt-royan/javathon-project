package ru.filit.mdma.dm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filit.mdma.dm.exception.WrongDataException;
import ru.filit.mdma.dm.model.Contact;
import ru.filit.mdma.dm.web.dto.ContactDto;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContactService {
    @Autowired
    private YamlService yamlService;

    private final String filename ="src/main/resources/datafiles/contacts.yml";
    private File file;

    public ContactService(){
        this.file=new File(filename);
    }

    public List<Contact> getClientContact(String clientId) throws WrongDataException {
        List<Contact> contacts=yamlService.readYaml(file, Contact.class);
        return contacts.stream().filter((c)-> c.getClientId().equals(clientId)).collect(Collectors.toList());
    }

    public void updateContact(ContactDto contactDto) throws WrongDataException {
        List<Contact> contacts=yamlService.readYaml(file, Contact.class);
        Contact contactOld =contacts.stream().filter(c-> c.getId().equals(contactDto.getId()) && c.getClientId().equals(contactDto.getClientId())).findFirst().orElseThrow(()->
                new WrongDataException("Неверный id или clientId"));
        contactOld.setType(contactDto.getType());
        contactOld.setValue(contactDto.getValue());
        yamlService.writeYaml(file, contacts);
    }

    public void saveContact(ContactDto contactDto) throws WrongDataException {
        List<Contact> contacts=yamlService.readYaml(file, Contact.class);
        Contact newContact =Contact.fromDto(contactDto);
        int id=Integer.parseInt(newContact.getClientId())/2;
        newContact.setId(String.valueOf(newContact.getClientId().hashCode()));
        contacts.add(newContact);
    }


}
