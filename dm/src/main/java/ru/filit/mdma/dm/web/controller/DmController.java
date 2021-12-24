package ru.filit.mdma.dm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.filit.mdma.dm.exception.WrongDataException;
import ru.filit.mdma.dm.model.Account;
import ru.filit.mdma.dm.model.Contact;
import ru.filit.mdma.dm.service.AccountService;
import ru.filit.mdma.dm.service.ClientService;
import ru.filit.mdma.dm.service.ContactService;
import ru.filit.mdma.dm.web.dto.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/dm")
public class DmController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/client")
    public Stream<ClientDto> searchClients(@RequestBody ClientSearchDto clientSearchDto) throws WrongDataException {
        return clientService.findClients(clientSearchDto).stream().map(ClientDto::fromEntity);
    }

    @PostMapping("/client/contact")
    public Stream<ContactDto> searchContacts(@RequestBody ClientIdDto id) throws WrongDataException {
        return contactService.getClientContact(id.getClientId()).stream().map(ContactDto::fromContact);
    }

    @PostMapping("/client/account")
    public List<Account> searchAccounts(@RequestBody ClientIdDto id) throws WrongDataException {
        contactService.saveContact(new ContactDto("9999999", "777777", Contact.TypeEnum.PHONE, "+79164600073","0073"));
        return accountService.getAccounts(id.getClientId());
    }

}
