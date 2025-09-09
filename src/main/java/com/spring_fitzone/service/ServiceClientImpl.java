package com.spring_fitzone.service;

import com.spring_fitzone.entity.Client;
import com.spring_fitzone.repository.RepositoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceClientImpl implements ServiceClient{

    @Autowired
    private RepositoryClient repositoryClient;

    @Override
    public List<Client> clientList() {
        List<Client> clients = repositoryClient.findAll();
        return clients;
    }

    @Override
    public Client searchClientById(Integer clientId) {
        Client client = repositoryClient.findById(clientId).orElse(null);
        return client;
    }

    @Override
    public void saveClient(Client client) {
        repositoryClient.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        repositoryClient.delete(client);
    }
}
