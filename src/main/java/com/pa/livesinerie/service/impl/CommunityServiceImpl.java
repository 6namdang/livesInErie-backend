package com.pa.livesinerie.service.impl;

import com.pa.livesinerie.repository.CommunityRepository;
import com.pa.livesinerie.service.CommunityServiceInterface;

import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pa.livesinerie.models.Community;

@Service
@Transactional
public class CommunityServiceImpl implements CommunityServiceInterface {

    private final CommunityRepository communityRepository;

    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public List<Community> getAllCommunity() {
        return communityRepository.findAll();
    }

    @Override
    public Community getCommunityById(int id) {
        return communityRepository.findById(id).orElseThrow(() -> new RuntimeException("This community is not found"));
    }

    @Override
    public Community createCommunity(Community newCommunity) {
        return communityRepository.save(newCommunity);
    }
@Override
public Community updateCommunityById(int id, Community updatedCommunity) {
    // 1. Fetch existing record (throws exception if missing)
    Community oldCommunity = getCommunityById(id);
    
    // 2. Map fields from the request to the managed entity
    oldCommunity.setName(updatedCommunity.getName());
    oldCommunity.setWebsiteUrl(updatedCommunity.getWebsiteUrl());
    oldCommunity.setPhoneNumber(updatedCommunity.getPhoneNumber());
    oldCommunity.setAddress(updatedCommunity.getAddress());
    oldCommunity.setEmail(updatedCommunity.getEmail());

    // 3. Save the MANAGED entity (oldCommunity)
    return communityRepository.save(oldCommunity); 
}

@Override
public boolean deleteCommunity(int id) {
    getCommunityById(id); 
    communityRepository.deleteById(id);
    return true;
}
}