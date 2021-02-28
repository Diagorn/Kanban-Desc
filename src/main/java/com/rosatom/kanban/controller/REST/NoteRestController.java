package com.rosatom.kanban.controller.REST;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Note;
import com.rosatom.kanban.dto.requests.NoteRequest;
import com.rosatom.kanban.dto.responses.NoteDataResponse;
import com.rosatom.kanban.dto.responses.NoteResponse;
import com.rosatom.kanban.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteRestController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/{id}")
    @ResponseBody
    public NoteResponse getNote(@PathVariable Long id) {
        return noteService.findById(id) == null?
                new NoteResponse(id, HttpStatus.OK) : new NoteResponse(-1, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    @ResponseBody
    public Iterable<NoteDataResponse> getAllNotes() {
        return noteService.findAll();
    }

    @PostMapping("")
    @ResponseBody
    public NoteResponse createNote(@AuthenticationPrincipal Account account, @RequestBody NoteRequest request) {
        return noteService.saveNoteFromRequest(request, account);
    }

    @DeleteMapping("/{id}")
    public NoteResponse deleteNote(@PathVariable Long id) {
        return  noteService.delete(id);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public NoteResponse updateNote(@PathVariable Long id, @RequestBody NoteRequest request, @AuthenticationPrincipal Account account) {
        return noteService.update(id, request, account);
    }
}
