package com.pa.livesinerie.service;
import com.pa.livesinerie.models.Community;
import java.util.List;

public interface CommunityServiceInterface {
    public Community createCommunity(Community newCommunity);
    public List<Community> getAllCommunity();
    public Community getCommunityById(int id);
    public Community updateCommunityById(int id, Community updatedCommunity);
    public boolean deleteCommunity(int id);
}