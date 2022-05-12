package com.example.tv.services;

import com.example.tv.entities.Clients;
import com.example.tv.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ClientsService {
    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Clients> getAll() {
        return clientsRepository.findAll();
    }

    public Clients addNew(Clients client) {
        Optional<Clients> clientOptional = clientsRepository.findClientsByEgn(client.getEgn());
        if(clientOptional.isPresent()){
            throw new IllegalStateException("Client with this egn already exists");
        }

        clientOptional = clientsRepository.findClientsByPhone(client.getPhone());
        if(clientOptional.isPresent()){
            throw new IllegalStateException("Client with this phone already exists");
        }

        clientOptional = clientsRepository.findClientsByEmail(client.getEmail());
        if(clientOptional.isPresent()){
            throw new IllegalStateException("Client with this email already exists");
        }

        clientsRepository.save(client);
        return client;
    }

    public void delete(Integer id) {
        boolean exists = clientsRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Client with ID" + id + "does not exists");
        }

        clientsRepository.deleteById(id);
    }

    @Transactional
    public Clients update(Integer id,Clients newClient) {
        Clients client = clientsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Client with ID" + id + "does not exists"));

        if(newClient.getName() != null
                && newClient.getName().length() > 0
                && !Objects.equals(client.getName(),newClient.getName())){
            client.setName(newClient.getName());
        }

        if(newClient.getEgn() != null
                && newClient.getEgn().length() == 10
                && !Objects.equals(client.getEgn(),newClient.getEgn())){
            Optional<Clients> clientsOptional = clientsRepository.findClientsByEgn(newClient.getEgn());
            if(clientsOptional.isPresent()){
                throw new IllegalStateException("This EGN is taken");
            }
            client.setEgn(newClient.getEgn());
        }

        if(newClient.getPhone() != null
                && newClient.getPhone().length() == 10
                && !Objects.equals(client.getPhone(),newClient.getPhone())){
            Optional<Clients> clientsOptional = clientsRepository.findClientsByPhone(newClient.getPhone());
            if(clientsOptional.isPresent()){
                throw new IllegalStateException("This phone is taken");
            }
            client.setPhone(newClient.getPhone());
        }

        if(newClient.getEmail() != null
                && newClient.getEmail().length() > 0
                && !Objects.equals(client.getEmail(),newClient.getEmail())){
            Optional<Clients> clientsOptional = clientsRepository.findClientsByEmail(newClient.getEmail());
            if(clientsOptional.isPresent()){
                throw new IllegalStateException("This email is taken");
            }
            client.setEmail(newClient.getEmail());
        }

        return client;
    }
}
