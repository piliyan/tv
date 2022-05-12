package com.example.tv.controllers;

import com.example.tv.entities.TVPackageContracts;
import com.example.tv.services.TVPackageContractsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tv/packageContracts")
public class TVPackageContractsController {
    private final TVPackageContractsService tvPackageContractsService;

    @Autowired
    public TVPackageContractsController(TVPackageContractsService tvPackageContractsService) {
        this.tvPackageContractsService = tvPackageContractsService;
    }

    @GetMapping
    public List<TVPackageContracts> getTVPackageContracts(){
        return tvPackageContractsService.getAll();
    }

    @PostMapping
    public TVPackageContracts addTVPackageContract(@RequestBody TVPackageContracts tvPackageContract){
        return tvPackageContractsService.addNew(tvPackageContract);
    }

    @DeleteMapping(path = "{tvPackageContractId}")
    public void deleteTVPackageContract(@PathVariable("tvPackageContractId") Integer tvPackageContractId){
        tvPackageContractsService.delete(tvPackageContractId);
    }

    @PutMapping(path = "{tvPackageContractId}")
    public TVPackageContracts updateTVChannelContract(@PathVariable("tvPackageContractId") Integer tvPackageContractId,
                                                      @RequestBody TVPackageContracts contract){
        return tvPackageContractsService.update(tvPackageContractId,contract);
    }
}
