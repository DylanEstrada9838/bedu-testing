package org.bedu.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.bedu.dto.CreateInterviewerDTO;
import org.bedu.dto.InterviewerDTO;
import org.bedu.service.InterviewerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class InterviewerControllerTest {

    @MockBean
    private InterviewerService service; 

    @Autowired
    private InterviewerController controller;

    @Test
    @DisplayName("Controller should be injected")
    public void smokeTest(){
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return  a list of interviewer")
    public void findAllTest(){
        List<InterviewerDTO> fakeData = new LinkedList<>();

        InterviewerDTO fakeInterviewer = new InterviewerDTO();

        fakeInterviewer.setId(100);
        fakeInterviewer.setEmail("email@gmail.com");
        fakeInterviewer.setName("name");

        fakeData.add(fakeInterviewer);

        when(service.findAll()).thenReturn(fakeData);

        // Act 
        List<InterviewerDTO> result = controller.findAll();

        // Assert
        assertEquals(fakeData, result);
    }

    @Test
    @DisplayName("Controller should save the Interviewer")
    public void  saveTest(){
        //Arrange
        CreateInterviewerDTO dto = new CreateInterviewerDTO();

        dto.setEmail("maria@gmail.com");
        dto.setName("Maria");

        InterviewerDTO saved = new InterviewerDTO();

        saved.setId(5);
        saved.setName("Maria");
        saved.setEmail("maria@gmail.com");

        when(service.save(any(CreateInterviewerDTO.class))).thenReturn(saved);

        //Act 
        InterviewerDTO result =  controller.save(dto);
        //Assert
        assertNotNull(result);
        assertEquals(dto.getName(),result.getName());
        assertEquals(dto.getEmail(), result.getEmail());



    }
}
