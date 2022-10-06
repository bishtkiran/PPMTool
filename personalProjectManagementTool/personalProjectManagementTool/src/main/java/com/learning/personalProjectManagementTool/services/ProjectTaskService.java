package com.learning.personalProjectManagementTool.services;

import com.learning.personalProjectManagementTool.domain.Backlog;
import com.learning.personalProjectManagementTool.domain.Project;
import com.learning.personalProjectManagementTool.domain.ProjectTask;
import com.learning.personalProjectManagementTool.exceptions.ProjectNotFoundException;
import com.learning.personalProjectManagementTool.repositories.BacklogRepository;
import com.learning.personalProjectManagementTool.repositories.ProjectRepository;
import com.learning.personalProjectManagementTool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask) throws ProjectNotFoundException {
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            projectTask.setBacklog(backlog);
            Integer backlogSequence = backlog.getPTSequence();
            backlogSequence++;
            backlog.setPTSequence(backlogSequence);

            projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            if (projectTask.getStatus() == "" || projectTask.getStatus() == null)
                projectTask.setStatus("TODO");

            if (projectTask.getPriority() == null)
                projectTask.setPriority(3);

            return projectTaskRepository.save(projectTask);
        }catch (Exception ex){
            throw new ProjectNotFoundException("Project with Id:'"+projectIdentifier+" 'doesn't exist");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        Project project = projectRepository.findByProjectIdentifier(id);
        if(project == null)
            throw new ProjectNotFoundException("Project with Id:'"+id+"'doesn't exist");
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findProjectTaskByProjectSequence(String backlog_id,String pt_id){
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(backlog == null)
            throw new ProjectNotFoundException("Project with Id:'"+backlog_id+"'doesn't exist");

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if(projectTask == null)
            throw new ProjectNotFoundException("Project Task '"+pt_id+"'doesn't exist");

        if(!projectTask.getProjectIdentifier().equals(backlog_id))
            throw new ProjectNotFoundException("Project Task '"+pt_id+"'doesn't exist in project: '"+backlog_id);

        return projectTask;
    }

    public ProjectTask updateProjectTaskBySequence(ProjectTask updatedTask, String backlog_id, String pt_id){
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id,pt_id);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);

    }
    public void deleteProjectTaskBySequence(String backlog_id, String pt_id){
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id,pt_id);
        projectTaskRepository.delete(projectTask);
    }
}
