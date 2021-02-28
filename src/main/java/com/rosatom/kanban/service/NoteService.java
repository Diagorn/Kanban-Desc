package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Note;
import com.rosatom.kanban.dto.requests.NoteRequest;
import com.rosatom.kanban.dto.responses.NoteDataResponse;
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
//        return new NoteDataResponse();
    }

    public Iterable<NoteDataResponse> findAll() {
        List<NoteDataResponse> data = new ArrayList<>();
        for (Note note: noteRepo.findAll()) {
            data.add(new NoteDataResponse(
                    note.getId(),
                    note.getTitle(),
                    note.getDescription(),
                    note.getStartDate(),
                    note.getEndDate()
            ));
        }
        return data;
    }

    public void setValues(Note note, String title, String content, GregorianCalendar date, Account account) {
        note.setTitle(title);
        note.setDescription(content);
        note.setStartDate(date);
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
            if (month == n.getStartDate().get(Calendar.MONTH) &&
                    day == n.getStartDate().get(Calendar.DAY_OF_MONTH) &&
                    year == n.getStartDate().get(Calendar.YEAR))
                result.add(n);
        }

        return result;
    }

    public NoteResponse saveNoteFromRequest(NoteRequest request, Account account) {
        Note note = new Note();

        note.setAccount(account);
        if (request.getStart_date() != null)
            note.setStartDate(request.getStart_date());
        if (request.getStart_date() != null)
            note.setEndDate(request.getStart_date());
        if (request.getDescription() != null && !request.getDescription().isEmpty())
            note.setDescription(request.getDescription());
        if (request.getTitle() != null && !request.getTitle().isEmpty())
            note.setTitle(request.getTitle());
        note.setCreationDate(new GregorianCalendar());

        noteRepo.save(note);

        return new NoteResponse(note.getId(), HttpStatus.CREATED);
    }

    public NoteResponse update(Long id, NoteRequest request, Account account) {
        Note note = findById(id);
        if (note != null) {
            if (request.getStart_date() != null)
                note.setStartDate(request.getStart_date());
            if (request.getStart_date() != null)
                note.setEndDate(request.getStart_date());
            if (request.getTitle() != null)
                note.setTitle(request.getTitle());
            if (request.getDescription() != null && !request.getDescription().isEmpty())
                note.setDescription(request.getDescription());
            note.setAccount(account);

            noteRepo.save(note);

            return new NoteResponse(id, HttpStatus.OK);
        } else {
            return new NoteResponse(id, HttpStatus.BAD_REQUEST);
        }
    }


}
