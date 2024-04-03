package de.tinf22b6.dhbwhub.repository.interfaces;

import de.tinf22b6.dhbwhub.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringEventRepository extends JpaRepository<Event, Long> {}
