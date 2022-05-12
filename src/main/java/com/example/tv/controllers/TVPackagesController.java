package com.example.tv.controllers;

import com.example.tv.entities.TVPackages;
import com.example.tv.services.TVPackagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tv/packages")
public class TVPackagesController {
    private final TVPackagesService tvPackagesService;

    @Autowired
    public TVPackagesController(TVPackagesService tvPackagesService) {
        this.tvPackagesService = tvPackagesService;
    }

    @GetMapping
    public List<TVPackages> getTVPackages(){
        return tvPackagesService.getAll();
    }

    @PostMapping
    public TVPackages addTVPackage(@RequestBody TVPackages tvPackage){
        return tvPackagesService.addNew(tvPackage);
    }

    @DeleteMapping(path = "{tvPackageId}")
    public void deleteTVPackage(@PathVariable("tvPackageId") Integer tvPackageId){
        tvPackagesService.delete(tvPackageId);
    }

    @PutMapping(path = "{tvPackageId}")
    public TVPackages updateTVPackage(@PathVariable("tvPackageId") Integer tvPackageId,
                                      @RequestBody TVPackages tvPackage){
        return tvPackagesService.update(tvPackageId,tvPackage);
    }
}
