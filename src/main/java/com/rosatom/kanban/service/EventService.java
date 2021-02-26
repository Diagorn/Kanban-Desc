package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Event;
import com.rosatom.kanban.repos.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepo;

    public Set<Event> getEventsByDate(GregorianCalendar date) {
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        int year = date.get(Calendar.YEAR);

        Iterable<Event> all = eventRepo.findAll();
        Set<Event> result = new HashSet<Event>();
        for (Event e: all) {
            if (e.isRepeatable()) {
                if (month == e.getDate().get(Calendar.MONTH) && day == e.getDate().get(Calendar.DAY_OF_MONTH)) {
                    result.add(e);
                }
            }
                else {
                if (month == e.getDate().get(Calendar.MONTH) &&
                        day == e.getDate().get(Calendar.DAY_OF_MONTH) &&
                        year == e.getDate().get(Calendar.YEAR)) {
                    result.add(e);
                }
            }
        }

        return result;
    }
}
