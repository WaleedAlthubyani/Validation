package org.example.projecttrackersystem.Controller;

import jakarta.validation.Valid;
import org.example.projecttrackersystem.ApiResponse.ApiResponse;
import org.example.projecttrackersystem.Model.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project-tracker-system")
public class ProjectTrackerSystemController {
    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @PostMapping("/add-project")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project added successfully"));
    }

    @PutMapping("/update-project/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        projects.set(index,project);
        return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));
    }

    @DeleteMapping("/delete-project/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully"));
    }

    @PutMapping("/change-status/{index}")
    public ResponseEntity changeStatus(@PathVariable int index){
        String[] statuses={"Not Started","In Progress","Completed"};
        for (int i = 0; i < statuses.length-1; i++) {
            if (projects.get(index).getStatus().equals(statuses[i])){
                projects.get(index).setStatus(statuses[i+1]);
                return ResponseEntity.status(200).body(new ApiResponse("Status changed to "+statuses[i+1]+" successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project is already completed"));
    }

    @GetMapping("/search-by-company/{companyName}")
    public ResponseEntity projectsByCompanyName(@PathVariable String companyName){
        ArrayList<Project> projectsByCompanyName=new ArrayList<>();

        for (Project p:projects){
            if (p.getCompanyName().equals(companyName))
                projectsByCompanyName.add(p);
        }

        return ResponseEntity.status(200).body(projectsByCompanyName);
    }

}
