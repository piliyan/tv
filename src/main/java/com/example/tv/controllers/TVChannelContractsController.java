package com.example.tv.controllers;

import com.example.tv.entities.TVChannelContracts;
import com.example.tv.services.TVChannelContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tv/channelContracts")
public class TVChannelContractsController {
    private final TVChannelContractsService tvChannelContractsService;

    @Autowired
    public TVChannelContractsController(TVChannelContractsService tvChannelContractsService) {
        this.tvChannelContractsService = tvChannelContractsService;
    }

    @GetMapping
    public List<TVChannelContracts> getTVChannelContracts(){
        return tvChannelContractsService.getAll();
    }

    @PostMapping
    public TVChannelContracts addTVChannelContract(@RequestBody TVChannelContracts tvChannelContract){
        return tvChannelContractsService.addNew(tvChannelContract);
    }

    @DeleteMapping(path = "{tvChannelContractId}")
    public void deleteTVChannelContract(@PathVariable("tvChannelContractId") Integer tvChannelContractId){
        tvChannelContractsService.delete(tvChannelContractId);
    }

    @PutMapping(path = "{tvChannelContractId}")
    public TVChannelContracts updateTVChannelContract(@PathVariable("tvChannelContractId") Integer tvChannelContractId,
                                                      @RequestBody TVChannelContracts contract){
        return tvChannelContractsService.update(tvChannelContractId, contract);
    }
}
