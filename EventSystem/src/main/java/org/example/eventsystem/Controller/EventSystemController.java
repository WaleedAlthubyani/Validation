package org.example.eventsystem.Controller;

import jakarta.validation.Valid;
import org.example.eventsystem.ApiResponse.ApiResponse;
import org.example.eventsystem.Model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event-system")
public class EventSystemController {

    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getEvents(){
        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@RequestBody @Valid Event event,@PathVariable int index,Errors error){
        if (error.hasErrors()){
            return ResponseEntity.status(400).body(error.getFieldError().getDefaultMessage());
        }
        events.set(index,event);

        return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index){
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));
    }

    @PutMapping("/change-capacity/{index}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index,@PathVariable int capacity){
        if (capacity<26)
            return ResponseEntity.status(400).body(new ApiResponse("capacity must be more than 25"));

        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(200).body(new ApiResponse("Capacity changed successfully"));
    }

    @GetMapping("search-by-id/{id}")
    public ResponseEntity searchById(@PathVariable String id){

        for (Event event : events) {
            if (event.getId().equals(id))
                return ResponseEntity.status(200).body(event);
        }

        return ResponseEntity.status(400).body(new ApiResponse("There are no events matching this id"));
    }
}
