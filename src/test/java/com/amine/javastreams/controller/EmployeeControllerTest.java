package com.amine.javastreams.controller;

import com.amine.javastreams.commandLineRunner.EmployeeCommandLineRunner;
import com.amine.javastreams.model.Response;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 09/03/2022 09:50
 * @description
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class EmployeeControllerTest {

    Logger logger = LoggerFactory.getLogger(EmployeeCommandLineRunner.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int serverPort;

    @Test
    @DisplayName("Get count male and female")
    void getCountMaleFemale() {
        /******************************************************
         * {
         *      "timeStamp": "2022-08-19 11:45:30",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "Count of male and female",
         *      "data": {
         *          "Male": 11,
         *          "Female": 6
         *      }
         * }
         ******************************************************/
        var count = this.restTemplate.getForObject(String.format("http://localhost:%d/api/v1/employees/getCountMaleFemale", serverPort), Response.class);
        var data = (Map<String, Integer>) count.getData();
        logger.info(String.format("count : %s", count));

        assertThat(data.get("Male")).isEqualTo(11);
        assertThat(data.get("Female")).isEqualTo(6);

        /*********************************************************************************************************************************************
         * var count = this.restTemplate.getForObject(String.format("http://localhost:%d/api/v1/employees/getCountMaleFemale", port), HashMap.class);
         * *******************************************************************************************************************************************
         * { "Male": 11, "Female": 6}
         * *******************************************************************************************************************************************
         * assertThat(count.get("Male")).isEqualTo(11);
         * assertThat(count.get("Female")).isEqualTo(6);
         *********************************************************************************************************************************************/
    }

    @Test
    @DisplayName("Get count male and female")
    public void getCountMaleFemale_whenGetEmployees_thenStatus200() throws Exception {
        /******************************************************
         * {
         *      "timeStamp": "2022-08-19 11:51:37",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "Count of male and female",
         *      "data": {
         *          "Male": 11,
         *          "Female": 6
         *      }
         * }
         ******************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getCountMaleFemale", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.Male").value(11))
                .andExpect(jsonPath("$.data.Female").value(6))
                .andDo(document("employees"));
    }

    @SneakyThrows
    @Test
    public void getNameOfDepartment() {
        /**********************************************************************
         * {
         *       "timeStamp": "2022-08-19 11:52:46",
         *       "statusCode": 200,
         *       "httpStatus": "OK",
         *       "message": "Name of all departments in the organization",
         *       "data": [
         *           "HR",
         *           "Sales And Marketing",
         *           "Infrastructure",
         *           "Product Development",
         *           "Security And Transport",
         *           "Account And Finance"
         *       ]
         * }
         **********************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getNameOfDepartment", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0]").value("HR"))
                .andExpect(jsonPath("$.data.[1]").value("Sales And Marketing"))
                .andExpect(jsonPath("$.data.[2]").value("Infrastructure"))
                .andExpect(jsonPath("$.data.[3]").value("Product Development"))
                .andExpect(jsonPath("$.data.[4]").value("Security And Transport"))
                .andExpect(jsonPath("$.data.[5]").value("Account And Finance"))
                .andDo(document("Departments"));
    }

    @SneakyThrows
    @Test
    void getAverageAgeMaleFemale() {
        /*******************************************************************
         * {
         *      "timeStamp": "2022-08-19 11:52:08",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "The average age of male and female employees",
         *      "data": {
         *          "Male": 30.181818181818183,
         *          "Female": 27.166666666666668
         *      }
         * }
         ********************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getAverageAgeMaleFemale", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.Male").value(30.181818181818183))
                .andExpect(jsonPath("$.data.Female").value(27.166666666666668))
                .andDo(document("Ages"));
    }

    @SneakyThrows
    @Test
    void getHighestPaidEmployee() {
        /**********************************************************************
         * {
         *      "timeStamp": "2022-08-19 11:53:42",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "Details of highest paid employee",
         *      "data": {
         *          "id": 17,
         *          "name": "Anuj Chettiar",
         *          "age": 31,
         *          "gender": "Male",
         *          "department": "Product Development",
         *          "yearOfJoining": 2012,
         *          "salary": 35700.0
         *      }
         * }
         **********************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getHighestPaidEmployee", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").value(17))
                .andExpect(jsonPath("$.data.salary").value(35700.0))
                .andDo(document("Highest salary"));
    }

    @SneakyThrows
    @Test
    void getNameOfEmployeesJoinedAfter() {
        /********************************************************************************
         * {
         *      "timeStamp": "2022-08-19 11:58:00",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "Names of all employees who have joined after 2015",
         *      "data": [
         *          "Iqbal Hussain",
         *          "Amelia Zoe",
         *          "Nitin Joshi",
         *          "Nicolus Den",
         *          "Ali Baig"
         *      ]
         * }
         ********************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getNameOfEmployeesJoinedAfter", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.[0]").value("Iqbal Hussain"))
                .andExpect(jsonPath("$.data.[1]").value("Amelia Zoe"))
                .andExpect(jsonPath("$.data.[2]").value("Nitin Joshi"))
                .andExpect(jsonPath("$.data.[3]").value("Nicolus Den"))
                .andExpect(jsonPath("$.data.[4]").value("Ali Baig"))
                .andDo(document("Employee"));
    }

    @SneakyThrows
    @Test
    void getNumberOfEmployeesInEachDepartment() {
        /*****************************************************************
         * {
         *      "timeStamp": "2022-08-19 12:02:35",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "Count the number of employees in each department",
         *      "data": {
         *          "Product Development": 5,
         *          "Security And Transport": 2,
         *          "Sales And Marketing": 3,
         *          "Infrastructure": 3,
         *          "HR": 2,
         *          "Account And Finance": 2
         *      }
         * }
         *****************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getNumberOfEmployeesInEachDepartment", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.['Product Development']").value(5))
                .andExpect(jsonPath("$.data.['Security And Transport']").value(2))
                .andExpect(jsonPath("$.data.['Sales And Marketing']").value(3))
                .andExpect(jsonPath("$.data.['Infrastructure']").value(3))
                .andExpect(jsonPath("$.data.['HR']").value(2))
                .andExpect(jsonPath("$.data.['Account And Finance']").value(2))
                .andDo(document("Employee count in each department"));
    }

    @SneakyThrows
    @Test
    void getAverageSalaryOfEachDepartment() {
        /****************************************************************************
         * {
         *      "timeStamp": "2022-08-19 12:08:13",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "The average salary of each department",
         *      "data": {
         *          "Product Development": 31960.0,
         *          "Security And Transport": 10750.25,
         *          "Sales And Marketing": 11900.166666666666,
         *          "Infrastructure": 15466.666666666666,
         *          "HR": 23850.0,
         *          "Account And Finance": 24150.0
         *      }
         * }
         ****************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getAverageSalaryOfEachDepartment", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.['Product Development']").value(31960.0))
                .andExpect(jsonPath("$.data.['Security And Transport']").value(10750.25))
                .andExpect(jsonPath("$.data.['Sales And Marketing']").value(11900.166666666666))
                .andExpect(jsonPath("$.data.['Infrastructure']").value(15466.666666666666))
                .andExpect(jsonPath("$.data.['HR']").value(23850.0))
                .andExpect(jsonPath("$.data.['Account And Finance']").value(24150.0))
                .andDo(document("Average salary for each department"));
    }

    @SneakyThrows
    @Test
    void getYoungestMaleEmployeesInPDDepartment() {
        /*****************************************************************************
         * {
         *      "timeStamp": "2022-08-19 12:11:00",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "Details of the youngest male employee in the product development department",
         *      "data": {
         *          "id": 12,
         *          "name": "Nitin Joshi",
         *          "age": 25,
         *          "gender": "Male",
         *          "department": "Product Development",
         *          "yearOfJoining": 2016,
         *          "salary": 28200.0
         *      }
         * }
         *****************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getYoungestMaleEmployeesInPDDepartment", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.id").value(12))
                .andExpect(jsonPath("$.data.age").value(25))
                .andExpect(jsonPath("$.data.yearOfJoining").value(2016))
                .andDo(document("Youngest employee"));
    }

    @SneakyThrows
    @Test
    void getMostWorkingExperience() {
        /***********************************************************************************
         * {
         *      "timeStamp": "2022-08-19 12:13:40",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "Who has the most working experience in the organization",
         *      "data": {
         *          "id": 7,
         *          "name": "Manu Sharma",
         *          "age": 35,
         *          "gender": "Male",
         *          "department": "Account And Finance",
         *          "yearOfJoining": 2010,
         *          "salary": 27000.0
         *      }
         *  }
         *************************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getMostWorkingExperience", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.id").value(7))
                .andExpect(jsonPath("$.data.age").value(35))
                .andExpect(jsonPath("$.data.yearOfJoining").value(2010))
                .andDo(document("Youngest employee"));
    }

    @SneakyThrows
    @Test
    void getCountOfMaleAndFemaleInSMD() {
        /******************************************************************************
         * {
         *      "timeStamp": "2022-08-19 12:15:26",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "How many male and female employees are there in the sales and marketing team",
         *      "data": {
         *          "Female": 1,
         *          "Male": 2
         *      }
         * }
         ******************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getCountOfMaleAndFemaleInSMD", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.Female").value(1))
                .andExpect(jsonPath("$.data.Male").value(2))
                .andDo(document("Count of employees"));
    }

    @SneakyThrows
    @Test
    void getAverageSalaryOfMaleAndFemale() {
        /******************************************************************************************
         * {
         *      "timeStamp": "2022-08-19 12:17:22",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "What is the average salary of male and female employees",
         *      "data": {
         *          "Male": 21300.090909090908,
         *          "Female": 20850.0
         *      }
         * }
         ******************************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getAverageSalaryOfMaleAndFemale", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.Male").value(21300.090909090908))
                .andExpect(jsonPath("$.data.Female").value(20850.0))
                .andDo(document("Average salary"));
    }

    @SneakyThrows
    @Test
    void getListEmployeesNamesInEachDepartment() {
        /*****************************************************************************
         * {
         *      "timeStamp": "2022-08-19 12:26:03",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "List down the names of all employees in each department",
         *      "data": {
         *          "Product Development": ["Murali Gowda", "Wang Liu", "Nitin Joshi", "Sanvi Pandey", "Anuj Chettiar"],
         *          "Security And Transport": ["Iqbal Hussain", "Jaden Dough" ],
         *          "Sales And Marketing": ["Paul Niksui", "Amelia Zoe", "Nicolus Den"],
         *          "Infrastructure": ["Martin Theron", "Jasna Kaur", "Ali Baig"],
         *          "HR": ["Jiya Brein", "Nima Roy"],
         *          "Account And Finance": ["Manu Sharma", "Jyothi Reddy"]
         *      }
         *  }
         **************************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getListEmployeesNamesInEachDepartment", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())

                .andExpect(jsonPath("$.data['Product Development']").isArray())
                .andExpect(jsonPath("$.data['Product Development'].[0]").value("Murali Gowda"))

                .andExpect(jsonPath("$.data['Security And Transport']").isArray())
                .andExpect(jsonPath("$.data['Security And Transport'].[0]").value("Iqbal Hussain"))

                .andExpect(jsonPath("$.data['Sales And Marketing']").isArray())
                .andExpect(jsonPath("$.data['Sales And Marketing'].[0]").value("Paul Niksui"))

                .andExpect(jsonPath("$.data['Infrastructure']").isArray())
                .andExpect(jsonPath("$.data['Infrastructure'].[0]").value("Martin Theron"))

                .andExpect(jsonPath("$.data['HR']").isArray())
                .andExpect(jsonPath("$.data['HR'].[0]").value("Jiya Brein"))

                .andExpect(jsonPath("$.data['Account And Finance']").isArray())
                .andExpect(jsonPath("$.data['Account And Finance'].[0]").value("Manu Sharma"))

                .andDo(document("Average salary"));
    }

    @SneakyThrows
    @Test
    void getAverageAndTotalSalary() {
        /*******************************************************************************************
         * {
         *      "timeStamp": "2022-08-19 14:45:38",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "What is the average salary and total salary of the whole organization",
         *      "data": [
         *          21141.235294117647,
         *          359401.0
         *      ]
         * }
         *******************************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getAverageAndTotalSalary", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0]").value(21141.235294117647))
                .andExpect(jsonPath("$.data[1]").value(359401.0))
                .andDo(document("Average an total salary"));
    }

    @SneakyThrows
    @Test
    void getSeparateEmployee() {
        /*****************************************************************************************************************************************************************
         * {
         *      "timeStamp": "2022-08-19 14:48:15",
         *      "statusCode": 200,
         *      "httpStatus": "OK",
         *      "message": "Employees who are younger or equal to 25 years, older than 25 years",
         *      "data": {
         *          "false": [
         *              {"id": 9, "name": "Amelia Zoe", "age": 24, "gender": "Female", "department": "Sales And Marketing", "yearOfJoining": 2016, "salary": 11500.0},
         *              {"id": 14, "name": "Nicolus Den", "age": 24, "gender": "Male", "department": "Sales And Marketing", "yearOfJoining": 2017, "salary": 10700.5},
         *              {"id": 15, "name": "Ali Baig", "age": 23, "gender": "Male", "department": "Infrastructure", "yearOfJoining": 2018, "salary": 12700.0}
         *          ],
         *          "true": [
         *              {"id": 1, "name": "Jiya Brein", "age": 32, "gender": "Female", "department": "HR", "yearOfJoining": 2011, "salary": 25000.0},
         *              {"id": 2, "name": "Paul Niksui", "age": 25, "gender": "Male", "department": "Sales And Marketing", "yearOfJoining": 2015, "salary": 13500.0},
         *              {"id": 3, "name": "Martin Theron", "age": 29, "gender": "Male", "department": "Infrastructure", "yearOfJoining": 2012, "salary": 18000.0},
         *              {"id": 4, "name": "Murali Gowda", "age": 28, "gender": "Male", "department": "Product Development", "yearOfJoining": 2014, "salary": 32500.0},
         *              {"id": 5, "name": "Nima Roy", "age": 27, "gender": "Female", "department": "HR", "yearOfJoining": 2013, "salary": 22700.0},
         *              {"id": 6, "name": "Iqbal Hussain", "age": 43, "gender": "Male", "department": "Security And Transport", "yearOfJoining": 2016, "salary": 10500.0},
         *              {"id": 7, "name": "Manu Sharma", "age": 35, "gender": "Male", "department": "Account And Finance", "yearOfJoining": 2010, "salary": 27000.0},
         *              {"id": 8, "name": "Wang Liu", "age": 31, "gender": "Male", "department": "Product Development", "yearOfJoining": 2015, "salary": 34500.0},
         *              {"id": 10, "name": "Jaden Dough", "age": 38, "gender": "Male", "department": "Security And Transport", "yearOfJoining": 2015, "salary": 11000.5},
         *              {"id": 11, "name": "Jasna Kaur", "age": 27, "gender": "Female", "department": "Infrastructure", "yearOfJoining": 2014, "salary": 15700.0},
         *              {"id": 12, "name": "Nitin Joshi", "age": 25, "gender": "Male", "department": "Product Development", "yearOfJoining": 2016, "salary": 28200.0},
         *              {"id": 13, "name": "Jyothi Reddy", "age": 27, "gender": "Female", "department": "Account And Finance", "yearOfJoining": 2013, "salary": 21300.0},
         *              {"id": 16, "name": "Sanvi Pandey", "age": 26, "gender": "Female", "department": "Product Development", "yearOfJoining": 2015, "salary": 28900.0},
         *              {"id": 17, "name": "Anuj Chettiar", "age": 31, "gender": "Male", "department": "Product Development", "yearOfJoining": 2012, "salary": 35700.0}
         *          ]
         *      }
         *  }
         *****************************************************************************************************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getSeparateEmployee", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.true").isArray())
                .andExpect(jsonPath("$.data.false").isArray())
                .andDo(document("Employees"));
    }

    @SneakyThrows
    @Test
    void getOldestEmployee() {
        /*********************************************************************************************
         *   {
         *       "timeStamp": "2022-08-19 15:01:14",
         *       "statusCode": 200,
         *       "httpStatus": "OK",
         *       "message": "Who is the oldest employee in the organization",
         *       "data": {
         *           "id": 6,
         *           "name": "Iqbal Hussain",
         *           "age": 43,
         *           "gender": "Male",
         *           "department": "Security And Transport",
         *           "yearOfJoining": 2016,
         *           "salary": 10500.0
         *       }
         *   }
         ***********************************************************************************************/
        mockMvc.perform(
                        get(String.format("http://localhost:%d/api/v1/employees/getOldestEmployee", serverPort))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.id").value(6))
                .andExpect(jsonPath("$.data.age").value(43))
                .andDo(document("Employees"));
    }
}
