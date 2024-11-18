package org.example.projecttrackersystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotEmpty(message = "You must provide an id")
    @Size(min = 3,message = "ID must be longer than 2 character")
    private String id;

    @NotEmpty(message = "Please provide a title")
    @Size(min = 9,message = "title must be more than 8 characters")
    private String title;

    @NotEmpty(message = "Please provide a description")
    @Size(min = 16,message = "Description must be 16 characters or more")
    private String description;

    @NotEmpty(message = "Please provide the status of this project")
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$",message = "status can only be Not Started or In Progress or Completed")
    private String status;

    @NotEmpty(message = "Please provide a company name")
    @Size(min = 6, message = "Company name must be longer than 5 characters")
    private String companyName;

}
