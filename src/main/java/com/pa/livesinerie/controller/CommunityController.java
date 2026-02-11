package com.pa.livesinerie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pa.livesinerie.models.Community;
import com.pa.livesinerie.service.impl.CommunityServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/api/community")
@CrossOrigin
public class CommunityController {
    private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
    private final CommunityServiceImpl communityServiceImpl;

    public CommunityController(CommunityServiceImpl communityServiceImpl) {
        this.communityServiceImpl = communityServiceImpl;
    }

    // GET all communities
    @GetMapping()
    public ResponseEntity<List<Community>> getAllCommunity() {
        logger.info("Receive request to get all community ");
        return ResponseEntity.ok(communityServiceImpl.getAllCommunity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityById(@PathVariable Integer id) {
        logger.info("Receive request to get community by Id");
        return ResponseEntity.ok(communityServiceImpl.getCommunityById(id));
    }

    @PostMapping()
    public ResponseEntity<Community> createCommunity(@RequestBody Community community) {
        Community savedCommunity = communityServiceImpl.createCommunity(community);
        return new ResponseEntity<>(savedCommunity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Community> updateCommunity(@PathVariable Integer id, @RequestBody Community community) {
        return ResponseEntity.ok(communityServiceImpl.updateCommunityById(id, community));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCommunity(@PathVariable Integer id) {
        boolean isDeleted = communityServiceImpl.deleteCommunity(id);
        
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
}