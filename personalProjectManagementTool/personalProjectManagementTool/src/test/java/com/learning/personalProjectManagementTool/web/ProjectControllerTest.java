package com.learning.personalProjectManagementTool.web;

import com.learning.personalProjectManagementTool.domain.Project;
import com.learning.personalProjectManagementTool.domain.ProjectTask;
import com.learning.personalProjectManagementTool.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectService projectService;

    @Test
    void shouldCreateProjectSuccessfully() throws Exception {
        String requestJson = "{" +
                "\"id\": \"1\"," +
                "\"projectName\":  \"New Project\"  ," +
                "\"projectIdentifier\": \"react\", " +
                "\"description\": \"New Project added\"," +
                "\"start_date\": \"10-12-2021\"" +
                "\"end_date\": \"12-01-2022\"" +
                "}";
//        mockMvc.perform(post("/api/project")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                        .content(requestJson))
//                .andExpect(status().isCreated());
    }

    @Test
    void shouldThrowErrorMessageForExistingProject() throws Exception {
        String requestJson = "{" +
                "\"id\": \"1\"," +
                "\"projectName\":  \"New Project\"  ," +
                "\"projectIdentifier\": \"react\", " +
                "\"description\": \"New Project added\"," +
                "\"start_date\": \"10-12-2021\"" +
                "\"end_date\": \"12-01-2022\"" +
                "}";
//        projectService.saveOrUpdateProject(new Project());
//        mockMvc.perform(post("/api/project")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                        .content(requestJson))
//                .andExpect(status().isBadRequest());
    }
}