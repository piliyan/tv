package com.example.tv.services;

import com.example.tv.entities.TVPackageContracts;
import com.example.tv.repositories.TVPackageContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TVPackageContractsService {
    private final TVPackageContractsRepository tvPackageContractsRepository;

    @Autowired
    public TVPackageContractsService(TVPackageContractsRepository tvPackageContractsRepository) {
        this.tvPackageContractsRepository = tvPackageContractsRepository;
    }

    public List<TVPackageContracts> getAll() {
        return tvPackageContractsRepository.findAll();
    }

    public TVPackageContracts addNew(TVPackageContracts contract) {
        tvPackageContractsRepository.save(contract);
        return contract;
    }

    public void delete(Integer id) {
        boolean exists = tvPackageContractsRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Contract with ID" + id + "does not exists");
        }
        tvPackageContractsRepository.deleteById(id);
    }

    @Transactional
    public TVPackageContracts update(Integer id, TVPackageContracts newContract) {
        TVPackageContracts contract = tvPackageContractsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Contract with ID" + id + "does not exists"));

        if(newContract.getClient() != null
                && !Objects.equals(contract.getClient(),newContract.getClient())){
            contract.setClient(newContract.getClient());
        }

        if(newContract.getTvPackage() != null
                && !Objects.equals(contract.getTvPackage(),newContract.getTvPackage())){
            contract.setTvPackage(newContract.getTvPackage());
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