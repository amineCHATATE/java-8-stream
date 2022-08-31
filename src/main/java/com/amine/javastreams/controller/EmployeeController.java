package com.amine.javastreams.controller;

import com.amine.javastreams.model.Employee;
import com.amine.javastreams.model.Response;
import com.amine.javastreams.service.EmployeeStreamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 07/03/2022 15:10
 * @description
 */
@RestController
@RequestMapping("/api/v1/employees")
@ApiOperation(value = "Employee API", notes = "Return 15 type of java 8 stream queries")
public class EmployeeController {

    public final EmployeeStreamService employeeService;

    public EmployeeController(EmployeeStreamService employeeService) {
        this.employeeService = employeeService;
    }

    // Query 1 : How many male and female employees are there in the organization
    @GetMapping("/getCountMaleFemale")
    @ApiOperation("Query 1 : How many male and female employees are there in the organization")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "Get Count of males and females",
            description = "Get Count of males and females, the response on json format",
            tags = {"Count"}
    )
    ResponseEntity<?> getCountMaleFemale() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getCountMaleFemale())
                                .message("Count of male and female")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 2 : Get the name of all departments in the organization
    @GetMapping("/getNameOfDepartment")
    @ApiOperation("Query 2 : Get the name of all departments in the organization")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "Get the name of all departments",
            description = "Get the name of all departments in the organization",
            tags = {"Name"}
    )
    ResponseEntity<?> getNameOfDepartment() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getNameOfDepartement())
                                .message("Name of all departments in the organization")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 3 : What is the average age of male and female employees
    @GetMapping("/getAverageAgeMaleFemale")
    @ApiOperation("Query 3 : What is the average age of male and female employees")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "The average age of male and female employees",
            description = "Query 3 : What is the average age of male and female employees",
            tags = {"Average"}
    )
    ResponseEntity<?> getAverageAgeMaleFemale() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getAverageAgeMaleFemale())
                                .message("The average age of male and female employees")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 4 : Get the details of highest paid employee in the organization
    @GetMapping("/getHighestPaidEmployee")
    @ApiOperation("Query 4 : Get the details of highest paid employee in the organization")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "Details of highest paid employee",
            description = "Details of highest paid employee in the organization",
            tags = {"Salary"}
    )
    ResponseEntity<?> getHighestPaidEmployee() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getHighiestPaidEmployee())
                                .message("Details of highest paid employee")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 5 : Get the names of all employees who have joined after 2015
    @GetMapping("/getNameOfEmployeesJoinedAfter")
    @ApiOperation("Query 5 : Get the names of all employees who have joined after 2015")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "The names of all employees who have joined after 2015",
            description = "Get the names of all employees who have joined after 2015",
            tags = {"Names"}
    )
    ResponseEntity<?> getNameOfEmployeesJoinedAfter() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getNameOfEmployeesJoinedAfter())
                                .message("Names of all employees who have joined after 2015")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 6 : Count the number of employees in each department
    @GetMapping("/getNumberOfEmployeesInEachDepartment")
    @ApiOperation("Query 6 : Count the number of employees in each department")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "Count the number of employees",
            description = "Count the number of employees in each department",
            tags = {"Count"}
    )
    ResponseEntity<?> getNumberOfEmployeesInEachDepartment() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getNumberOfEmployeesInEachDepartement())
                                .message("Count the number of employees in each department")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 7 : What is the average salary of each department
    @GetMapping("/getAverageSalaryOfEachDepartment")
    @ApiOperation("What is the average salary of each department")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "The average salary of each department",
            description = "What is the average salary of each department",
            tags = {"Average"}
    )
    ResponseEntity<?> getAverageSalaryOfEachDepartment() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getAverageSalaryOfEachDepartement())
                                .message("The average salary of each department")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 8 : Get the details of the youngest male employee in the product development department
    @GetMapping("/getYoungestMaleEmployeesInPDDepartment")
    @ApiOperation("Get the details of the youngest male employee in the product development department")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "Details of the youngest male employee in the product development department",
            description = "Get the details of the youngest male employee in the product development department",
            tags = {"Details"}
    )
    ResponseEntity<?> getYoungestMaleEmployeesInPDDepartment() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getYougestMaleEmployeesInPDDepartement())
                                .message("Details of the youngest male employee in the product development department")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 9 : Who has the most working experience in the organization
    @GetMapping("/getMostWorkingExperience")
    @ApiOperation("Who has the most working experience in the organization")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "Who has the most working experience",
            description = "Who has the most working experience in the organization",
            tags = {"Experience"}
    )
    ResponseEntity<?> getMostWorkingExperience() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getMostWorkingExperience())
                                .message("Who has the most working experience in the organization")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 10 : How many male and female employees are there in the sales and marketing team
    @GetMapping("/getCountOfMaleAndFemaleInSMD")
    @ApiOperation("How many male and female employees are there in the sales and marketing team")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "How many male and female employees are there in the sales and marketing team",
            description = "How many male and female employees are there in the sales and marketing team",
            tags = {"Count"}
    )
    ResponseEntity<?> getCountOfMaleAndFemaleInSMD() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getCountOfMaleAndFemaleInSMD())
                                .message("How many male and female employees are there in the sales and marketing team")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 11 : What is the average salary of male and female employees
    @GetMapping("/getAverageSalaryOfMaleAndFemale")
    @ApiOperation("What is the average salary of male and female employees")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "What is the average salary",
            description = "What is the average salary of male and female employees",
            tags = {"Average"}
    )
    ResponseEntity<?> getAverageSalaryOfMaleAndFemale() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getAverageSalaryOfMaleAndFemale())
                                .message("What is the average salary of male and female employees")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 12 : List down the names of all employees in each department
    @GetMapping("/getListEmployeesNamesInEachDepartment")
    @ApiOperation("List down the names of all employees in each department")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "List of names of all employees in each department",
            description = "List down the names of all employees in each department",
            tags = {"List"}
    )
    ResponseEntity<?> getListEmployeesNamesInEachDepartment() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getListEmployeesNamesInEachDepartement())
                                .message("List down the names of all employees in each department")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 13 : What is the average salary and total salary of the whole organization
    @GetMapping("/getAverageAndTotalSalary")
    @ApiOperation("What is the average salary and total salary of the whole organization")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "The average salary and total salary",
            description = "What is the average salary and total salary of the whole organization",
            tags = {"Average"}
    )
    ResponseEntity<?> getAverageAndTotalSalary() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getAverageAndTotalSalary())
                                .message("What is the average salary and total salary of the whole organization")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
    @GetMapping("/getSeparateEmployee")
    @ApiOperation("Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "Employees who are younger or equal to 25 years, older than 25 years",
            description = "Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years",
            tags = {"List"}
    )
    ResponseEntity<?> getSeparateEmployee() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getSeparateEmployee())
                                .message("Employees who are younger or equal to 25 years, older than 25 years")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }

    // Query 15 : Who is the oldest employee in the organization What is his age and which department he belongs to
    @GetMapping("/getOldestEmployee")
    @ApiOperation("Who is the oldest employee in the organization What is his age and which department he belongs to")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))}
                    ),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    @Operation(
            summary = "Who is the oldest employee in the organization",
            description = "Who is the oldest employee in the organization What is his age and which department he belongs to",
            tags = {"Employee"}
    )
    ResponseEntity<?> getOldestEmployee() {
        return ResponseEntity
                .ok(
                        Response
                                .builder()
                                .timeStamp(LocalDateTime.now())
                                .data(employeeService.getOldestEmployee())
                                .message("Who is the oldest employee in the organization")
                                .httpStatus(OK)
                                .statusCode(OK.value())
                                .build()
                );
    }
}
