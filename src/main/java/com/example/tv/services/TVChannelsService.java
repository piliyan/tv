package com.example.tv.services;

import com.example.tv.entities.TVChannels;
import com.example.tv.repositories.TVChannelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TVChannelsService {
    private final TVChannelsRepository tvChannelsRepository;

    @Autowired
    public TVChannelsService(TVChannelsRepository tvChannelsRepository) {
        this.tvChannelsRepository = tvChannelsRepository;
    }

    public List<TVChannels> getAll() {
        return tvChannelsRepository.findAll();
    }

    public TVChannels addNew(TVChannels tvChannel) {
        Optional<TVChannels> tvChannelOptional = tvChannelsRepository.findTVChannelsByName(tvChannel.getName());
        if(tvChannelOptional.isPresent()){
            throw new IllegalStateException("This Name already exists");
        }

        tvChannelsRepository.save(tvChannel);
        return tvChannel;
    }

    public void delete(Integer id) {
        boolean exists = tvChannelsRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("TVChannel with ID" + id + "does not exists");
        }
        tvChannelsRepository.deleteById(id);
    }

    @Transactional
    public TVChannels update(Integer id, TVChannels newTVChannel) {
        TVChannels tvChannel = tvChannelsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("TVChannel with ID" + id + "does not exists"));

        if(newTVChannel.getName() != null
                && newTVChannel.getName().length() > 0
                && !Objects.equals(tvChannel.getName(),newTVChannel.getName())){
            Optional<TVChannels> tvChannelsOptional = tvChannelsRepository.findTVChannelsByName(newTVChannel.getName());
            if(tvChannelsOptional.isPresent()){
                throw new IllegalStateException("This name is taken");
            }

            tvChannel.setName(newTVChannel.getName());
        }

        if(newTVChannel.getClientPrice() != null
                && newTVChannel.getClientPrice() > newTVChannel.getProvidingPrice()
                && tvChannel.getClientPrice() != newTVChannel.getClientPrice()){
            tvChannel.setClientPrice(newTVChannel.getClientPrice());
        }

        if(newTVChannel.getProvidingPrice() != null
                && newTVChannel.getProvidingPrice() < newTVChannel.getClientPrice()
                && tvChannel.getProvidingPrice() != newTVChannel.getProvidingPrice()){
            tvChannel.setProvidingPrice(newTVChannel.getProvidingPrice());
        }

        if(newTVChannel.getProvider() != null
                && !Objects.equals(tvChannel.getProvider(),newTVChannel.getProvider())){
            tvChannel.setProvider(newTVChannel.getProvider());
        }

        if(newTVChannel.getTvPackage() != null
                && !Objects.equals(tvChannel.getTvPackage(),newTVChannel.getTvPackage())){
            tvChannel.setTvPackage(newTVChannel.getTvPackage());
        }

        return tvChannel;
    }
}
