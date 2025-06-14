package com.home.sabir.spring.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StudentController.class)
@WithMockUser
public class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService studentService;

  Course mockCourse = new Course("Course1", "Spring", "10Steps",
		  new ArrayList<String>(Arrays.asList("Learn Maven", "Import Project", "First Example", "Second Example")));

  String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

  @Test
  public void retrieveDetailsForCourse() throws Exception {

    Mockito.when(studentService.retrieveCourse(Mockito.anyString(), Mockito.anyString())).thenReturn(mockCourse);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/Student1/courses/Course1")
            .accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    System.out.println(result.getResponse());
    String expected = "{id:Course1,name:Spring,description:10Steps}";

    // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
  }

  @Test
  public void createStudentCourse() throws Exception {
    Course mockCourse = new Course("1", "Smallest Number", "1",
    		new ArrayList<String>(Arrays.asList("1", "2", "3", "4")));

    // studentService.addCourse to respond back with mockCourse
    Mockito.when(studentService.addCourse(Mockito.anyString(), Mockito.any(Course.class))).thenReturn(mockCourse);

    // Send course as body to /students/Student1/courses
    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/students/Student1/courses")
            .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    assertEquals("http://localhost/students/Student1/courses/Course1", response.getHeader(HttpHeaders.LOCATION));

  }

}