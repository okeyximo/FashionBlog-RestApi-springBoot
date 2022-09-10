package com.example.blog.controller;

import com.example.blog.dto.CommentDto;
import com.example.blog.service.ICommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {CommentController.class})
@ExtendWith(SpringExtension.class)
class CommentControllerTest {

    @Autowired
    private CommentController commentController;

    @MockBean
    private ICommentService iCommentService;

    @Test
    void postComment() {
    }

    /**
     * Method under test: {@link CommentController#postComment(CommentDto)}
     */
    @Test
    void testPostComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment("Comment");
        commentDto.setCommenter("Commenter");
        commentDto.setPostId(123L);
        commentDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(commentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    void getCommentForAPost() {
    }
}