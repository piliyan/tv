package com.example.tv.controllers;

import com.example.tv.entities.Providers;
import com.example.tv.services.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tv/providers")
public class ProvidersController {
    private final ProvidersService providersService;

    @Autowired
    public ProvidersController(ProvidersService providersService) {
        this.providersService = providersService;
    }

    @GetMapping
    public List<Providers> getProviders(){
        return providersService.getAll();
    }

    @PostMapping
    public Providers addProvider(@RequestBody Providers provider){
        return providersService.addNew(provider);
    }

    @DeleteMapping(path = "{providerId}")
    public void deleteProvider(@PathVariable("providerId") Integer providerId){
        providersService.delete(providerId);
    }

    @PutMapping(path = "{providerId}")
    public Providers updateProvider(@PathVariable("providerId") Integer providerId,
                                    @RequestBody Providers provider){
        return providersService.update(providerId, provider);
    }

}
