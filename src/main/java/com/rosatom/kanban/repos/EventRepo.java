package com.rosatom.kanban.repos;

import com.rosatom.kanban.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {

}
