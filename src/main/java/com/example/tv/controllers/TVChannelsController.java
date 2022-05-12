package com.example.tv.controllers;

import com.example.tv.entities.TVChannels;
import com.example.tv.services.TVChannelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tv/channels")
public class TVChannelsController {
    private final TVChannelsService tvChannelsService;

    @Autowired
    public TVChannelsController(TVChannelsService tvChannelsService) {
        this.tvChannelsService = tvChannelsService;
    }

    @GetMapping
    public List<TVChannels> getTVChannels(){
        return tvChannelsService.getAll();
    }

    @PostMapping
    public TVChannels addTVChannel(@RequestBody TVChannels tvChannel){
        return tvChannelsService.addNew(tvChannel);
    }

    @DeleteMapping(path = "{tvChannelId}")
    public void deleteTVChannel(@PathVariable("tvChannelId") Integer tvChannelId){
        tvChannelsService.delete(tvChannelId);
    }

    @PutMapping(path = "{tvChannelId}")
    public TVChannels updateTVChannel(@PathVariable("tvChannelId") Integer tvChannelId,
                                      @RequestBody TVChannels tvChannel){
        return tvChannelsService.update(tvChannelId, tvChannel);
    }
}
