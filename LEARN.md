NON-NEGOTIABLE PRINCIPLES

1. Everything starts with the domain
2. Entities do not talk to the web
3. Controllers contain zero business logic
4. Services do not know HTTP
5. Repositories do not know business rules
6. One responsibility per layer
7. Validation happens before persistence
8. Every feature is CRUD + constraints + lifecycle

# Every backend is the same thing:
State + Rules + Transitions + Exposure

Translated to Spring Boot:

Concept	Spring Layer
State	Entity
Rules	Service
Transitions	Service methods
Storage	Repository
Exposure	Controller
Input safety	DTO + Validation
Output shaping	DTO / Mapper


com.example.app
│
├── config/           → security, cors, swagger
├── controller/       → HTTP only
├── dto/              → request / response models
├── entity/           → JPA entities
├── repository/       → DB access
├── service/
│   ├── impl/         → implementations
│   └── interfaces/
├── mapper/           → entity ↔ dto
├── exception/        → domain + global errors
└── AppApplication.java


UNIVERSAL AUTHENTICATION BLUEPRINT (SPRING BOOT)
GOAL

Build authentication that is:

Decoupled from entities

Independent of HTTP

Extensible to any auth method

Testable

Production-aligned

You will never hardcode auth logic into controllers or entities.

0. NON-NEGOTIABLE AUTH PRINCIPLES (MEMORIZE)

Authentication is a domain concern, not a controller concern

Entities do not know how authentication works

Controllers never validate credentials

Authentication logic lives in services

Security framework (Spring Security) is infrastructure, not business logic

Auth method (password, OAuth, token) must be pluggable

Identity ≠ Authorization (who you are ≠ what you can do)

If you violate these, your auth system will rot.


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // allow dev frontend
public class GeocodeController {

    @GetMapping("/geocode")
    public Map<String, Double> geocode(@RequestParam String address) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://nominatim.openstreetmap.org/search?format=json&limit=1&q=" +
                     UriUtils.encode(address, StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "LivesInErie/1.0 (contact@livesinerie.com)");
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        if (root.isArray() && root.size() > 0) {
            double lat = root.get(0).get("lat").asDouble();
            double lon = root.get(0).get("lon").asDouble();
            return Map.of("lat", lat, "lon", lon);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");
        }
    }
}
