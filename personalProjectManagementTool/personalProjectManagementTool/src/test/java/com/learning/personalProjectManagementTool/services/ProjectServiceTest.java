package com.learning.personalProjectManagementTool.services;

import com.learning.personalProjectManagementTool.domain.Project;
import com.learning.personalProjectManagementTool.exceptions.ProjectIdException;
import com.learning.personalProjectManagementTool.repositories.BacklogRepository;
import com.learning.personalProjectManagementTool.repositories.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectServiceTest {

    private Project project;

    private ProjectService projectService;

    private ProjectRepository projectRepository;

    private BacklogRepository backlogRepository;

    @BeforeEach
    public void beforeEach(){
        projectRepository = mock(ProjectRepository.class);
        backlogRepository=mock(BacklogRepository.class);
        projectService = new ProjectService();
    }

    @Test
    void should_get_project_by_projectIdentifier() throws ProjectIdException {
        project = mock(Project.class);
        projectService = mock(ProjectService.class);
        when(projectRepository.findByProjectIdentifier("react")).thenReturn(project);
        projectService.findProjectByIdentifier("react");

    }

    @Test
    void should_get_allProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        when(projectRepository.findAll()).thenReturn(projects);
    }

    @Test
    void should_addOrUpdate_project() {
        when(projectRepository.findByProjectIdentifier("react")).thenReturn(new Project());
        Project mockProject = mock(Project.class);
        when(projectRepository.save(new Project())).thenReturn(mockProject);
    }


}