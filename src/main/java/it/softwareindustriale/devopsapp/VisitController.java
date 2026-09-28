package it.softwareindustriale.devopsapp;

import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitController {

  private final JdbcTemplate jdbc;
  private volatile boolean schemaReady = false;

  public VisitController(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @GetMapping("/visits")
  public Map<String, Object> visit() {
    ensureSchema();
    jdbc.update("INSERT INTO visits (visited_at) VALUES (now())");
    Long total = jdbc.queryForObject("SELECT count(*) FROM visits", Long.class);
    return Map.of("visits", total);
  }


  private void ensureSchema() {
    if (schemaReady) {
      return;
    }
    jdbc.execute("""
				CREATE TABLE IF NOT EXISTS visits (
				    id         BIGSERIAL   PRIMARY KEY,
				    visited_at TIMESTAMPTZ NOT NULL DEFAULT now()
				)""");
    schemaReady = true;
  }
}
