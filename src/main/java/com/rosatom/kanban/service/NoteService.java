package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Note;
import com.rosatom.kanban.dto.requests.NoteRequest;
import com.rosatom.kanban.dto.responses.NoteResponse;
import com.rosatom.kanban.repos.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public NoteResponse delete(long id) {
        Note note = findById(id);
        if (note != null) {
            noteRepo.delete(note);
            return new NoteResponse(id, HttpStatus.OK);
        } else {
            return new NoteResponse(id, HttpStatus.BAD_REQUEST);
        }
    }

    public Set<Note> getNotesByDate(GregorianCalendar date) {
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        int year = date.get(Calendar.YEAR);

        Iterable<Note> all = noteRepo.findAll();
        Set<Note> result = new HashSet<Note>();
        for (Note n: all) {
            if (month == n.getDate().get(Calendar.MONTH) &&
                    day == n.getDate().get(Calendar.DAY_OF_MONTH) &&
                    year == n.getDate().get(Calendar.YEAR))
                result.add(n);
        }

        return result;
    }

    public NoteResponse saveNoteFromRequest(NoteRequest request, Account account) {
        Note note = new Note();

        note.setAccount(account);
        if (request.getDate() != null)
            note.setDate(request.getDate());
        if (request.getContent() != null && !request.getContent().isEmpty())
            note.setContent(request.getContent());
        if (request.getTitle() != null && !request.getTitle().isEmpty())
            note.setTitle(request.getTitle());
        note.setCreationDate(new GregorianCalendar());

        noteRepo.save(note);

        return new NoteResponse(note.getId(), HttpStatus.CREATED);
    }

    public NoteResponse update(Long id, NoteRequest request, Account account) {
        Note note = findById(id);
        if (note != null) {
            if (request.getDate() != null)
                note.setDate(request.getDate());
            if (request.getTitle() != null)
                note.setTitle(request.getTitle());
            if (request.getContent() != null && !request.getContent().isEmpty())
                note.setContent(request.getContent());
            note.setAccount(account);

            noteRepo.save(note);

            return new NoteResponse(id, HttpStatus.OK);
        } else {
            return new NoteResponse(id, HttpStatus.BAD_REQUEST);
        }
    }

    public Iterable<Note> findAll() {
        return noteRepo.findAll();
    }
}
