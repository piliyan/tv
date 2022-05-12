package com.example.tv.services;

import com.example.tv.entities.Providers;
import com.example.tv.repositories.ProvidersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProvidersService {
    private final ProvidersRepository providersRepository;

    @Autowired
    public ProvidersService(ProvidersRepository providersRepository) {
        this.providersRepository = providersRepository;
    }


    public List<Providers> getAll() {
        return providersRepository.findAll();
    }

    public Providers addNew(Providers provider) {
        Optional<Providers> providerOptional = providersRepository.findProvidersByName(provider.getName());
        if(providerOptional.isPresent()){
            throw new IllegalStateException("This Name is Taken");
        }

        providersRepository.save(provider);
        return provider;
    }

    public void delete(Integer id) {
        boolean exists = providersRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Provider with ID" + id + "does not exists");
        }
        providersRepository.deleteById(id);
    }

    @Transactional
    public Providers update(Integer id, Providers newProvider) {
        Providers provider = providersRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Provider with ID" + id + "does not exists"));

        if(newProvider.getName() != null
                && newProvider.getName().length() > 0
                && !Objects.equals(provider.getName(),newProvider.getName())){
            Optional<Providers> providersOptional = providersRepository.findProvidersByName(newProvider.getName());
            if(providersOptional.isPresent()){
                throw new IllegalStateException("This name is taken");
            }

            provider.setName(newProvider.getName());
        }

        if(newProvider.getSignedDate() != null
                && !Objects.equals(provider.getSignedDate(),newProvider.getSignedDate())){
            provider.setSignedDate(newProvider.getSignedDate());
        }

        if(newProvider.getDurationDate() != null
                && !Objects.equals(provider.getDurationDate(),newProvider.getDurationDate())){
            provider.setDurationDate(newProvider.getDurationDate());
        }

        return provider;
    }

}
