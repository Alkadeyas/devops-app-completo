# devops-app

API di esempio del corso **DevOps per Junior** (Software Industriale).

È il *filo conduttore* di tutte le sessioni: oggi vive sul proprio laptop, alla
fine girerà su Kubernetes con pipeline automatizzata e monitoring. Espone un
endpoint **`/health`** fornito da **Spring Boot Actuator**.

- **Stack:** Java 21 · Spring Boot 4.0 · Maven
- **Endpoint:** `GET /health` (e `GET /info`)

---

## Requisiti

- **Java 21** (o superiore).
- **Maven NON serve**: usa il *Maven wrapper* (`./mvnw`) incluso nel repo.

## Avviare l'app

```bash
# macOS / Linux / Git Bash / WSL
./mvnw spring-boot:run

# Windows (PowerShell o CMD)
mvnw.cmd spring-boot:run
```

Poi, da un altro terminale o dal browser:

```bash
curl http://localhost:8080/health
# -> {"status":"UP", ...}
```

## L'endpoint `/health`

Non c'è alcun controller scritto a mano: l'endpoint arriva "gratis" dalla
dipendenza `spring-boot-starter-actuator`. In
[`application.properties`](src/main/resources/application.properties) abbiamo
solo spostato la base path degli endpoint Actuator da `/actuator` a `/`, così
l'health è raggiungibile su `/health` invece che su `/actuator/health`:

```properties
management.endpoints.web.base-path=/
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always
```

## Struttura

```
devops-app/
├── .gitignore
├── README.md
├── pom.xml
├── mvnw  /  mvnw.cmd                 # Maven wrapper (build senza installare Maven)
└── src/
    ├── main/java/it/softwareindustriale/devopsapp/DevopsAppApplication.java
    └── main/resources/application.properties   # espone /health via Actuator
```

---

## Partecipanti

Durante la Sessione 1 ogni partecipante aggiunge qui il proprio nome su un
branch staccato da `develop` (`git switch develop` → `git switch -c nome-cognome`)
e apre una Merge Request verso `develop`. Seguiamo **Git Flow**: `main` resta
protetto e non si tocca mai direttamente.

- Mario Rossi *(esempio)*
- Jacopo Camplone
- Bruno Barbieri
- _(aggiungi qui il tuo nome)_
