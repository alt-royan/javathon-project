package ru.filit.mdma.crm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ru.filit.mdma.crm.web.dto.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Component
public class DmClient {

    private final RestTemplate restTemplate=new RestTemplate();

    private final String dmRequest;

    private final String searchClientsUrl="/client";
    private final String getContactsUrl="/client/contact";
    private final String getAccountsUrl="/client/account";
    private final String getBalanceUrl="/client/account/balance";
    private final String getOperationsUrl="/client/account/operation";
    private final String saveContactUrl="/client/contact/save";
    private final String getLevelUrl="/client/level";
    private final String getOverdraftUrl="/client/account/loan-payment";

    public DmClient(@Value("${system.element.dm.host}") String host, @Value("${system.element.dm.port}") String port){
        dmRequest="http://"+host+":"+port+"/dm";
    }

    /**
     * Запрос поиска клиентов по фильтру
     */
    public List<ClientDto> searchClients(ClientSearchDto clientSearchDto) throws ClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientSearchDto> entity = new HttpEntity<>(clientSearchDto, headers);
        try {
            ResponseEntity<ClientDto[]> response = restTemplate.postForEntity(dmRequest+searchClientsUrl, entity, ClientDto[].class);
            return Arrays.asList(response.getBody());
        } catch(HttpStatusCodeException e) {
            throw new ClientException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * Запрос контактов клиента
     */
    public List<ContactDto> getContacts(ClientIdDto clientIdDto) throws ClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientIdDto> entity = new HttpEntity<>(clientIdDto, headers);
        try {
            ResponseEntity<ContactDto[]> response = restTemplate.postForEntity(dmRequest+getContactsUrl, entity, ContactDto[].class);
            return Arrays.asList(response.getBody());
        } catch(HttpStatusCodeException e) {
            throw new ClientException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * Запрос счетов клиента
     */
    public List<AccountDto> getAccounts(ClientIdDto clientIdDto) throws ClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientIdDto> entity = new HttpEntity<>(clientIdDto, headers);
        try {
            ResponseEntity<AccountDto[]> response = restTemplate.postForEntity(dmRequest+getAccountsUrl, entity, AccountDto[].class);
            return Arrays.asList(response.getBody());
        } catch(HttpStatusCodeException e) {
            throw new ClientException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * Запрос баланса по счёту
     */
    public CurrentBalanceDto getBalance(AccountNumberDto accountNumberDto) throws ClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountNumberDto> entity = new HttpEntity<>(accountNumberDto, headers);
        try {
            ResponseEntity<CurrentBalanceDto> response = restTemplate.postForEntity(dmRequest+getBalanceUrl, entity, CurrentBalanceDto.class);
            return response.getBody();
        } catch(HttpStatusCodeException e) {
            throw new ClientException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * Запрос операций по счету
     */
    public List<OperationDto> getOperations(OperationSearchDto operationSearchDto) throws ClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OperationSearchDto> entity = new HttpEntity<>(operationSearchDto, headers);
        try {
            ResponseEntity<OperationDto[]> response = restTemplate.postForEntity(dmRequest+getOperationsUrl, entity, OperationDto[].class);
            return Arrays.asList(response.getBody());
        } catch(HttpStatusCodeException e) {
            throw new ClientException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * Сохранение контакта клиента
     */
    public ContactDto saveContact(ContactDto contactDto) throws ClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ContactDto> entity = new HttpEntity<>(contactDto, headers);
        try {
            ResponseEntity<ContactDto> response = restTemplate.postForEntity(dmRequest+saveContactUrl, entity, ContactDto.class);
            return response.getBody();
        } catch(HttpStatusCodeException e) {
            throw new ClientException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * Получение уровня клиента
     */
    public ClientLevelDto getClientLevel(ClientIdDto clientIdDto) throws ClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClientIdDto> entity = new HttpEntity<>(clientIdDto, headers);
        try {
            ResponseEntity<ClientLevelDto> response = restTemplate.postForEntity(dmRequest+getLevelUrl, entity, ClientLevelDto.class);
            return response.getBody();
        } catch(HttpStatusCodeException e) {
            throw new ClientException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * Получение суммы процентных платежей по счету Овердрафт
     */
    public LoanPaymentDto getOverdraft(AccountNumberDto accountNumberDto) throws ClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountNumberDto> entity = new HttpEntity<>(accountNumberDto, headers);
        try {
            ResponseEntity<LoanPaymentDto> response = restTemplate.postForEntity(dmRequest+getOverdraftUrl, entity, LoanPaymentDto.class);
            return response.getBody();
        } catch(HttpStatusCodeException e) {
            throw new ClientException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

}

