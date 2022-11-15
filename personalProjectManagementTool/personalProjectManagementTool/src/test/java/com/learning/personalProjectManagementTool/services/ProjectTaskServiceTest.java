package com.learning.personalProjectManagementTool.services;

import com.learning.personalProjectManagementTool.domain.Backlog;
import com.learning.personalProjectManagementTool.domain.Project;
import com.learning.personalProjectManagementTool.domain.ProjectTask;
import com.learning.personalProjectManagementTool.repositories.BacklogRepository;
import com.learning.personalProjectManagementTool.repositories.ProjectRepository;
import com.learning.personalProjectManagementTool.repositories.ProjectTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectTaskServiceTest {

    private BacklogRepository backlogRepository;

    private ProjectTaskRepository projectTaskRepository;

    private ProjectRepository projectRepository;

    private ProjectTask projectTask;

    @BeforeEach
    private void init(){
        projectTaskRepository = mock(ProjectTaskRepository.class);
        projectRepository = mock(ProjectRepository.class);
        backlogRepository=mock(BacklogRepository.class);
        projectTask= new ProjectTask();
    }


    @Test
    void should_addProjectTask() {
        Backlog backlog = backlogRepository.findByProjectIdentifier("react");
        projectTask.setBacklog(backlog);
        ProjectTask mockProjectTask = mock(ProjectTask.class);
        when(projectTaskRepository.save(new ProjectTask())).thenReturn(mockProjectTask);
    }

    @Test
    void should_getBacklog_By_Id() {
        Project mockProject = mock(Project.class);
        when(projectRepository.findByProjectIdentifier("react")).thenReturn(mockProject);

    }
}