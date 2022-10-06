package com.learning.personalProjectManagementTool.web;

import com.learning.personalProjectManagementTool.domain.ProjectTask;
import com.learning.personalProjectManagementTool.exceptions.ProjectNotFoundException;
import com.learning.personalProjectManagementTool.services.ProjectTaskService;
import com.learning.personalProjectManagementTool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> createProjectTask(@Valid @RequestBody ProjectTask projectTask,
                                               BindingResult bindingResult, @PathVariable String backlog_id) throws ProjectNotFoundException {
        ResponseEntity<?> errorMap = validationErrorService.validationService(bindingResult);
        if(errorMap != null)
            return errorMap;

        return new ResponseEntity<ProjectTask>(projectTaskService.addProjectTask(backlog_id,projectTask), HttpStatus.CREATED);

    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){
        return projectTaskService.findBacklogById(backlog_id);
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTaskBySequence(@PathVariable String backlog_id, @PathVariable String pt_id){
       ProjectTask projectTask = projectTaskService.findProjectTaskByProjectSequence(backlog_id,pt_id);
       return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask,BindingResult result,
                                               @PathVariable String backlog_id, @PathVariable String pt_id){
        ResponseEntity<?> errorMap = validationErrorService.validationService(result);
        if(errorMap != null)
            return errorMap;

        ProjectTask updatedTask = projectTaskService.updateProjectTaskBySequence(projectTask, backlog_id,pt_id);
        return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
        projectTaskService.deleteProjectTaskBySequence(backlog_id,pt_id);
        return new ResponseEntity<String>("Project Task "+pt_id+" was deleted successfully",HttpStatus.OK);
    }
}
