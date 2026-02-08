package com.pa.livesinerie.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriUtils;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api/geocoding")
@CrossOrigin
public class MapController {
    
    @GetMapping
    public Map<String, Double> geocode(@RequestParam String address) {
        
        try {
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "LivesInErie 1.0/ (6namdang@gmail.com)");
            headers.set("Accept-Language", "en-US,en;q=0.9");
            System.out.println("   User-Agent: " + headers.getFirst("User-Agent"));
            System.out.println("   Accept-Language: " + headers.getFirst("Accept-Language"));
            
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            
            // Step 3: Encode address
            String encodedAddress = UriUtils.encode(address, StandardCharsets.UTF_8);
            System.out.println("   Encoded: " + encodedAddress);
            
            // Step 4: Build URL
            System.out.println("\n5. BUILDING URL...");
            // 1. Manually replace spaces with '+' to match your curl example exactly
            String formattedAddress = address.replace(" ", "+");

            String url = "https://nominatim.openstreetmap.org/search?q=" + formattedAddress + "&format=json";
            System.out.println("   Full URL: " + url);
            
            // Step 5: Make HTTP request
            System.out.println("\n6. MAKING HTTP REQUEST...");
            System.out.println("   Sending GET request to Nominatim...");
            ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("   ✓ Response received");
            System.out.println("   Status Code: " + response.getStatusCode());
            System.out.println("   Status Value: " + response.getStatusCode().value());
            
            // Step 6: Log response body
            System.out.println("\n7. RESPONSE BODY:");
            String responseBody = response.getBody();
            System.out.println("   Body Length: " + (responseBody != null ? responseBody.length() : 0) + " characters");
            System.out.println("   Body Content: " + responseBody);
            
            // Step 7: Parse JSON
            System.out.println("\n8. PARSING JSON...");
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("   ObjectMapper created");
            
            JsonNode root = mapper.readTree(responseBody);
            System.out.println("   ✓ JSON parsed successfully");
            System.out.println("   Root node type: " + root.getNodeType());
            System.out.println("   Is array: " + root.isArray());
            
            if (root.isArray()) {
                System.out.println("   Array size: " + root.size());
            }
            
            // Step 8: Check for results
            System.out.println("\n9. CHECKING FOR RESULTS...");
            if (root.isArray() && root.size() > 0) {
                System.out.println("   ✓ Results found! Count: " + root.size());
                
                // Step 9: Extract first result
                System.out.println("\n10. EXTRACTING FIRST RESULT...");
                JsonNode firstResult = root.get(0);
                System.out.println("    First result JSON: " + firstResult.toString());
                
                // Step 10: Extract lat field
                System.out.println("\n11. EXTRACTING LATITUDE...");
                JsonNode latNode = firstResult.get("lat");
                System.out.println("    Lat node: " + latNode);
                System.out.println("    Lat node type: " + (latNode != null ? latNode.getNodeType() : "null"));
                
                String latString = latNode.asString();
                System.out.println("    Lat as text: " + latString);
                
                Double lat = Double.parseDouble(latString);
                System.out.println("    ✓ Parsed latitude: " + lat);
                
                // Step 11: Extract lon field
                System.out.println("\n12. EXTRACTING LONGITUDE...");
                JsonNode lonNode = firstResult.get("lon");
                System.out.println("    Lon node: " + lonNode);
                System.out.println("    Lon node type: " + (lonNode != null ? lonNode.getNodeType() : "null"));
                
                String lonString = lonNode.asString();
                System.out.println("    Lon as text: " + lonString);
                
                Double lon = Double.parseDouble(lonString);
                System.out.println("    ✓ Parsed longitude: " + lon);
                
                // Step 12: Create result map
                System.out.println("\n13. CREATING RESULT MAP...");
                Map<String, Double> result = Map.of("lat", lat, "lon", lon);
                System.out.println("    Result map: " + result);
                
                System.out.println("\n════════════════════════════════════════");
                System.out.println("✓✓✓ GEOCODE REQUEST SUCCESS ✓✓✓");
                System.out.println("════════════════════════════════════════\n");
                
                return result;
                
            } else {
                System.err.println("\n✗✗✗ NO RESULTS FOUND ✗✗✗");
                System.err.println("Array is empty or response is not an array");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No results found for address: " + address);
            }
            
        } catch (ResponseStatusException e) {
            System.err.println("\n════════════════════════════════════════");
            System.err.println("✗✗✗ RESPONSE STATUS EXCEPTION ✗✗✗");
            System.err.println("════════════════════════════════════════");
            System.err.println("Status: " + e.getStatusCode());
            System.err.println("Reason: " + e.getReason());
            System.err.println("Message: " + e.getMessage());
            throw e;
            
        } catch (Exception e) {
            System.err.println("\n════════════════════════════════════════");
            System.err.println("✗✗✗ EXCEPTION CAUGHT ✗✗✗");
            System.err.println("════════════════════════════════════════");
            System.err.println("Exception Type: " + e.getClass().getName());
            System.err.println("Exception Message: " + e.getMessage());
            System.err.println("\nStack Trace:");
            e.printStackTrace();
            System.err.println("════════════════════════════════════════\n");
            
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Geocoding failed: " + e.getMessage(),
                e
            );
        }
    }
}