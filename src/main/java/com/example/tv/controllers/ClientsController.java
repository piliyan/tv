package com.example.tv.controllers;

import com.example.tv.entities.Clients;
import com.example.tv.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tv/clients")
public class ClientsController {
    private final ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping
    public List<Clients> getClients(){
        return clientsService.getAll();
    }

    @PostMapping
    public Clients addClient(@RequestBody Clients client){
        return clientsService.addNew(client);
    }

    @DeleteMapping(path = "{clientId}")
    public void deleteClient(@PathVariable("clientId") Integer clientId){
        clientsService.delete(clientId);
    }

    @PutMapping(path = "{clientId}")
    public Clients updateClient(@PathVariable("clientId") Integer clientId,
                                @RequestBody Clients client){
        return clientsService.update(clientId,client);
    }
}
