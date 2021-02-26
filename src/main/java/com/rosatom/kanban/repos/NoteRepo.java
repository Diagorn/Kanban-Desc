package com.rosatom.kanban.repos;

import com.rosatom.kanban.domain.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepo extends CrudRepository<Note, Long> {

}
