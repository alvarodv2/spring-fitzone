package com.spring_fitzone.service;
import com.spring_fitzone.entity.Client;

import java.util.List;

public interface ServiceClient {

     List<Client> clientList();
     Client searchClientById(Integer clientId);
     void saveClient(Client client);
     void deleteClient(Client client);

}
