package com.example.jamify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.jamify.model.Task;
import com.example.jamify.repository.TaskRepository;

@SpringBootApplication
public class JamifyApplication {
	private static final Logger log = LoggerFactory.getLogger(JamifyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JamifyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TaskRepository repository) {
		return (args) -> {
			// Testdaten erstellen
			repository.save(new Task("Team Meeting vorbereiten", false));
			repository.save(new Task("Code Review durchführen", true));
			repository.save(new Task("Dokumentation aktualisieren", false));
			repository.save(new Task("Unit Tests schreiben", false));
			repository.save(new Task("Bug #1234 beheben", true));
			repository.save(new Task("Datenbank Migration", false));
			repository.save(new Task("Performance Optimierung", false));
			repository.save(new Task("Security Audit", true));
			repository.save(new Task("Client Präsentation", false));
			repository.save(new Task("Server Wartung", true));
			repository.save(new Task("API Dokumentation", false));
			repository.save(new Task("Backup Strategie entwickeln", false));
			repository.save(new Task("Monitoring Setup", true));
			repository.save(new Task("Code Refactoring", false));
			repository.save(new Task("Integration Tests", false));

			log.info("Tasks found with findAll():");
			log.info("----------------------------");
			for (Task task : repository.findAll()) {
				log.info(task.toString());
			}
		};
	}
}