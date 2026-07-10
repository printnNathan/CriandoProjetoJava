package com.enventostec.api.controller;

import com.enventostec.api.Domain.event.Event;
import com.enventostec.api.Domain.event.EventDetailsDTO;
import com.enventostec.api.Domain.event.EventRequestDTO;
import com.enventostec.api.Domain.event.EventResponseDTO;
import com.enventostec.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

@PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(@RequestParam("title") String title,
                                        @RequestParam(value = "description", required = false) String description,
                                        @RequestParam("date") Long date,
                                        @RequestParam("city") String city,
                                        @RequestParam("state") String state,
                                        @RequestParam("remote") Boolean remote,
                                        @RequestParam("eventUrl") String eventUrl,
                                        @RequestParam(value = "image", required = false) MultipartFile image){
        EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, date, city, state, remote, eventUrl, image);
        Event newEvent = this.eventService.createEvent(eventRequestDTO);
        return ResponseEntity.ok(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
            List<EventResponseDTO> allEvent = this.eventService.getUpcomingEvents(page, size);
        return ResponseEntity.ok(allEvent);
    }

    @GetMapping("/{event}")
    public ResponseEntity<EventDetailsDTO> getEventDetails(@PathVariable UUID eventId) {
        EventDetailsDTO eventDetails = eventService.getEventDetails(eventId);
        return ResponseEntity.ok(eventDetails);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponseDTO>> filterEvent(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size,
                                                              @RequestParam(required = false) String title,
                                                              @RequestParam(required = false) String city,
                                                              @RequestParam(required = false) String uf,
                                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                              @RequestParam(required = false ) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
    List<EventResponseDTO> events = eventService.getFilteredEvents(page, size, title, city, uf, startDate, endDate);
    return ResponseEntity.ok(events);

    }
}
