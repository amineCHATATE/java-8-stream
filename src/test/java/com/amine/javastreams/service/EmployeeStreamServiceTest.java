package com.amine.javastreams.service;

import com.amine.javastreams.model.Employee;
import com.amine.javastreams.repository.EmployeeRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class EmployeeStreamServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeStreamService employeeStreamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    List<Employee> allEmployees = Arrays.asList(
            new Employee(1L, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0),
            new Employee(2L, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0),
            new Employee(3L, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0),
            new Employee(4L, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0),
            new Employee(5L, "Nima Roy", 27, "Female", "HR", 2013, 22700.0),
            new Employee(6L, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0),
            new Employee(7L, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0),
            new Employee(8L, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0),
            new Employee(9L, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0),
            new Employee(10L, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5),
            new Employee(11L, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0),
            new Employee(12L, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0),
            new Employee(13L, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0),
            new Employee(14L, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5),
            new Employee(15L, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0),
            new Employee(16L, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0),
            new Employee(17L, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0)
    );

    @SneakyThrows
    @Test
    void getCountMaleFemale() {
        // Given

        Map<String, Integer> data = new HashMap<>();
        data.put("Male", 11);
        data.put("Female", 6);
        //Response<?> expected = new Response(LocalDateTime.now(), 200, "OK", "Count of male and female", data);

        //given(employeeRepository.findAll()).willReturn(List.of(***));
        //given(employeeRepository.findAll());

        //When
        when(employeeRepository.findAll()).thenReturn(allEmployees);

        mockMvc.perform(get("/api/v1/employees/getNameOfDepartment").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }
}