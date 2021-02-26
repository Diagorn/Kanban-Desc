package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Note;
import com.rosatom.kanban.repos.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

@Service
public class NoteService {
    @Autowired
    private NoteRepo noteRepo;

    public Note findById(long id) {
        return noteRepo.findById(id).get();
    }

    public void setValues(Note note, String title, String content, GregorianCalendar date, Account account) {
        note.setTitle(title);
        note.setContent(content);
        note.setDate(date);
        note.setAccount(account);
    }

    public void create(String title, String content, GregorianCalendar date, Account account) {
        Note note = new Note();
        setValues(note, title, content, date, account);
        note.setCreationDate(new GregorianCalendar());
        noteRepo.save(note);
    }

    public void create(String title, String content, Account account) {
        Note note = new Note();
        setValues(note, title, content, null, account);
        note.setCreationDate(new GregorianCalendar());
        noteRepo.save(note);
    }

    public void update(long id, String title, String content, GregorianCalendar date, Account account) {
        Note note = findById(id);
        setValues(note, title, content, date, account);
        noteRepo.save(note);
    }

    public void update(long id, String title, String content, Account account) {
        Note note = findById(id);
        setValues(note, title, content, null, account);
        noteRepo.save(note);
    }

    public void delete(long id) {
        Note note = findById(id);
        noteRepo.delete(note);
    }
}
