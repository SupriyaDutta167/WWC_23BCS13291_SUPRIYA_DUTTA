package com.wwc.demo.Controller;

import com.wwc.demo.Model.Event;
import com.wwc.demo.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
        public List<Event> getAllEVents(){
           return eventService.getAllEvents();
        }

        @PostMapping
        public String addEvents(@RequestBody Event event){
           eventService.addEvent(event);
           return "Event Added Successfully";
        }

        @GetMapping("/{id}")
        public Event getEventById(@PathVariable int id){
            return eventService.getEventById(id);
        }

        @DeleteMapping("/{id}")
        public String deleteEvent(@PathVariable int id) {
            boolean deleted = eventService.deleteEventById(id);
            return deleted ? "Event Deleted" : "Event Not Found";
        }


}
