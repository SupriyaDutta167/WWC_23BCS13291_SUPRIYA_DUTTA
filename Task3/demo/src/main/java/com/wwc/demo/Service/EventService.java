package com.wwc.demo.Service;

import com.wwc.demo.Model.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event){
        events.add(event);
    }

    public List<Event> getAllEvents(){
        return events;
    }

    public Event getEventById(int id){
        for(Event event : events){
            if(event.getId() == id){
                return event;
            }
        }
        return null;
    }

    public boolean deleteEventById(int id){
        Event event = getEventById(id);
        if(event != null){
            events.remove(event);
            return true;
        }
        return false;
    }
}
