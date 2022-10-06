package com.learning.personalProjectManagementTool.services;

import com.learning.personalProjectManagementTool.domain.Backlog;
import com.learning.personalProjectManagementTool.domain.Project;
import com.learning.personalProjectManagementTool.exceptions.ProjectIdException;
import com.learning.personalProjectManagementTool.repositories.BacklogRepository;
import com.learning.personalProjectManagementTool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project) throws ProjectIdException {
        try{
            String identifier = project.getProjectIdentifier().toUpperCase();
            project.setProjectIdentifier(identifier);
            if(project.getId() == null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(identifier);
            }

            if(project.getId() != null){
                Backlog backlog = backlogRepository.findByProjectIdentifier(identifier);
                project.setBacklog(backlog);
            }
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }

    }

    public Project findProjectByIdentifier(String projectIdentifier) throws ProjectIdException {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIdException(("Project ID '"+projectIdentifier+"' doesn't exists"));
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) throws ProjectIdException {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdException(("Project ID '"+projectId+"' doesn't exists"));
        }
        projectRepository.delete(project);
    }

}
