package com.learning.personalProjectManagementTool.web;

import com.learning.personalProjectManagementTool.domain.Project;
import com.learning.personalProjectManagementTool.exceptions.ProjectIdException;
import com.learning.personalProjectManagementTool.services.ProjectService;
import com.learning.personalProjectManagementTool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) throws ProjectIdException {
        ResponseEntity<?>errorMap = validationErrorService.validationService(result);
        if(errorMap != null) return errorMap;
        return new ResponseEntity<>(projectService.saveOrUpdateProject(project),HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProject(@PathVariable String projectId) throws ProjectIdException {
        return new ResponseEntity<>(projectService.findProjectByIdentifier(projectId),HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) throws ProjectIdException {
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project with ID: '"+projectId+"' was deleted",HttpStatus.OK);
    }
}
