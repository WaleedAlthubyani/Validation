package org.example.eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {

    @NotEmpty(message = "Please provide an ID")
    @Size(min = 3,message = "ID must be longer than 2 characters")
    private String id;

    @NotEmpty(message = "Please provide a description")
    @Size(min = 16,message = "Description must be longer than 15 character")
    private String description;

    @NotNull(message = "Please provide a capacity")
    @Positive(message = "Capacity can only be a positive number")
    @Min(value = 26,message = "Capacity must be bigger than 25")
    private int capacity;

    @NotNull(message = "Please provide a starting date to your event")
    @FutureOrPresent(message = "The start date of the event can't be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDate;

    @NotNull(message = "Please provide an end date to your event")
    @Future(message = "the end date of the event can't be in the past")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime endDate;



}
