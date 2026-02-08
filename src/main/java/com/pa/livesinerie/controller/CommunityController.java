package com.pa.livesinerie.controller;

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
    
    private final CommunityServiceImpl communityServiceImpl;

    public CommunityController(CommunityServiceImpl communityServiceImpl) {
        this.communityServiceImpl = communityServiceImpl;
    }

    // GET all communities
    @GetMapping()
    public ResponseEntity<List<Community>> getAllCommunity() {
        return ResponseEntity.ok(communityServiceImpl.getAllCommunity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityById(@PathVariable Integer id) {
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
            return ResponseEntity.ok(true); // Returns 200 OK with 'true'
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false); // Returns 404 with 'false'
        }
    }
}