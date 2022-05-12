package com.example.tv.services;

import com.example.tv.entities.TVChannelContracts;
import com.example.tv.repositories.TVChannelContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TVChannelContractsService {
    private final TVChannelContractsRepository tvChannelContractsRepository;

    @Autowired
    public TVChannelContractsService(TVChannelContractsRepository tvChannelContractsRepository) {
        this.tvChannelContractsRepository = tvChannelContractsRepository;
    }

    public List<TVChannelContracts> getAll() {
        return tvChannelContractsRepository.findAll();
    }

    public TVChannelContracts addNew(TVChannelContracts contract) {
        tvChannelContractsRepository.save(contract);
        return contract;
    }

    public void delete(Integer id) {
        boolean exists = tvChannelContractsRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Contract with ID" + id + "does not exists");
        }
        tvChannelContractsRepository.deleteById(id);
    }

    @Transactional
    public TVChannelContracts update(Integer id, TVChannelContracts newContract) {
        TVChannelContracts contract = tvChannelContractsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Contract with ID" + id + "does not exists"));

        if(newContract.getClient() != null
                && !Objects.equals(contract.getClient(),newContract.getClient())){
            contract.setClient(newContract.getClient());
        }

        if(newContract.getTvChannel() != null
                && !Objects.equals(contract.getTvChannel(),newContract.getTvChannel())){
            contract.setTvChannel(newContract.getTvChannel());
        }

        if(newContract.getSignedDate() != null
                && !Objects.equals(contract.getSignedDate(),newContract.getSignedDate())){
            contract.setSignedDate(newContract.getSignedDate());
        }

        if(newContract.getDurationDate() != null
                && !Objects.equals(contract.getDurationDate(),newContract.getDurationDate())){
            newContract.setDurationDate(newContract.getDurationDate());
        }

        return contract;
    }
}
