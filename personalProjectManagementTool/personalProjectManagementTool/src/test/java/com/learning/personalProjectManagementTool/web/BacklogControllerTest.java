package com.learning.personalProjectManagementTool.web;

import com.learning.personalProjectManagementTool.domain.ProjectTask;
import com.learning.personalProjectManagementTool.services.ProjectTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BacklogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectTaskService projectTaskService;

    @Test
    void shouldCreateProjectTaskSuccessfully() throws Exception {
        String requestJson = "{" +
                "\"id\": \"1\"," +
                "\"projectSequence\":  \"react\"  ," +
                "\"summary\": \"project task summary\", " +
                "\"status\": \"todo\"," +
                "\"priority\": \"high\"" +
                "}";
//        mockMvc.perform(post("/api/backlog/{backlog_id}")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                        .content(requestJson))
//                .andExpect(status().isCreated());
    }

    @Test
    void shouldThrowErrorMessageForExistingProjectTask() throws Exception {
        String requestJson = "{" +
                "\"id\": \"1\"," +
                "\"projectSequence\":  \"react\"  ," +
                "\"summary\": \"project task summary\", " +
                "\"status\": \"todo\"," +
                "\"priority\": \"high\"" +
                "}";
//        projectTaskService.addProjectTask("react", new ProjectTask());
//        mockMvc.perform(post("/api/backlog/{backlog_id}")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                        .content(requestJson))
//                .andExpect(status().isBadRequest());

    }
}