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