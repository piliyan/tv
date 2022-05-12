package com.example.tv.services;

import com.example.tv.entities.TVPackages;
import com.example.tv.repositories.TVPackagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TVPackagesService {
    private final TVPackagesRepository tvPackagesRepository;

    @Autowired
    public TVPackagesService(TVPackagesRepository tvPackagesRepository) {
        this.tvPackagesRepository = tvPackagesRepository;
    }

    public List<TVPackages> getAll() {
        return tvPackagesRepository.findAll();
    }

    public TVPackages addNew(TVPackages tvPackage) {
        Optional<TVPackages> tvPackageOptional = tvPackagesRepository.findTVPackagesByCategory(tvPackage.getCategory());
        if(tvPackageOptional.isPresent()){
            throw new IllegalStateException("This Category already exists");
        }

        tvPackagesRepository.save(tvPackage);
        return tvPackage;
    }

    public void delete(Integer id) {
        boolean exists = tvPackagesRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("TVPackage with ID" + id + "does not exists");
        }
        tvPackagesRepository.deleteById(id);
    }

    @Transactional
    public TVPackages update(Integer id, TVPackages newTVPackage) {
        TVPackages tvPackage = tvPackagesRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("TVPackage with ID" + id + "does not exists"));

        if(newTVPackage.getCategory() != null
                && newTVPackage.getCategory().length() > 0
                && !Objects.equals(tvPackage.getCategory(),newTVPackage.getCategory())){
            Optional<TVPackages> tvChannelsOptional = tvPackagesRepository.findTVPackagesByCategory(newTVPackage.getCategory());
            if(tvChannelsOptional.isPresent()){
                throw new IllegalStateException("This category is taken");
            }

            tvPackage.setCategory(newTVPackage.getCategory());
        }

        if(newTVPackage.getPrice() != null
                && newTVPackage.getPrice() > 0
                && tvPackage.getPrice() != newTVPackage.getPrice()){
            tvPackage.setPrice(newTVPackage.getPrice());
        }

        return tvPackage;
    }
}
