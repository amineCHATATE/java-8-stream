package com.amine.javastreams.service;

import com.amine.javastreams.exception.NotFoundException;
import com.amine.javastreams.model.Employee;
import com.amine.javastreams.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 07/03/2022 15:10
 * @description
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeStreamService {

    private final EmployeeRepository employeeRepository;

    // Query 1 : How many male and female employees are there in the organization?
    public Map<String, Long> getCountMaleFemale() {
        return employeeRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender,
                                Collectors.counting()
                        )
                );
    }

    // Query 2 : Print the name of all departments in the organization?
    public List<String> getNameOfDepartement() {
        return employeeRepository
                .findAll()
                .stream()
                .map(Employee::getDepartment)
                .distinct()
                .toList();
    }

    // Query 3 : What is the average age of male and female employees?
    public Map<String, Double> getAverageAgeMaleFemale() {
        return employeeRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender,
                                Collectors.averagingDouble(Employee::getAge)
                        )
                );
    }

    // Query 4 : Get the details of highest paid employee in the organization?
    public Employee getHighiestPaidEmployee() {
        return employeeRepository
                .findAll()
                .stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new NotFoundException("No employee found."));
    }

    // Query 5 : Get the names of all employees who have joined after 2015?
    public List<String> getNameOfEmployeesJoinedAfter() {
        return employeeRepository
                .findAll()
                .stream()
                .filter(e -> e.getYearOfJoining() > 2015)
                .map(Employee::getName)
                .distinct()
                .toList();
    }

    // Query 6 : Count the number of employees in each department?
    public Map<String, Long> getNumberOfEmployeesInEachDepartement() {
        return employeeRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting())
                );
    }

    // Query 7 : What is the average salary of each department?
    public Map<String, Double> getAverageSalaryOfEachDepartement() {
        return employeeRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.averagingDouble(Employee::getSalary)
                        )
                );
    }

    // Query 8 : Get the details of youngest male employee in the product
    // development department?
    public Employee getYougestMaleEmployeesInPDDepartement() {
        return employeeRepository
                .findAll()
                .stream()
                .filter(e ->
                        e.getGender().equalsIgnoreCase("Male")
                                &&
                                e.getDepartment().equalsIgnoreCase("Product Development"))
                .min(Comparator.comparingInt(Employee::getAge))
                .orElseThrow(() -> new NotFoundException("No employee found."));
    }

    // Query 9 : Who has the most working experience in the organization?
    public Employee getMostWorkingExperience() {
        return employeeRepository
                .findAll()
                .stream()
                .min(Comparator.comparingInt(Employee::getYearOfJoining))
                .orElseThrow(() -> new NotFoundException("No employee found."));
    }

    // Query 10 : How many male and female employees are there in the sales and
    // marketing team?
    public Map<String, Long> getCountOfMaleAndFemaleInSMD() {
        return employeeRepository
                .findAll()
                .stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("Sales And Marketing"))
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender,
                                Collectors.counting()
                        )
                );
    }

    // Query 11 : What is the average salary of male and female employees?
    public Map<String, Double> getAverageSalaryOfMaleAndFemale() {
        return employeeRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender,
                                Collectors.averagingDouble(Employee::getSalary)
                        )
                );
    }

    // Query 12 : List down the names of all employees in each department?
    public Map<String, List<String>> getListEmployeesNamesInEachDepartement() {
        return employeeRepository
                .findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.mapping(Employee::getName, Collectors.toList())
                        )
                );
    }

    // Query 13 : What is the average salary and total salary of the whole organization?
    public List<Double> getAverageAndTotalSalary() {
        DoubleSummaryStatistics employeeSummary = employeeRepository
                .findAll()
                .stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        return Arrays.asList(employeeSummary.getAverage(), employeeSummary.getSum());
    }

    // Query 14 : Separate the employees who are younger or equal to 25 years from
    // those employees who are older than 25 years.
    public Map<Boolean, List<Employee>> getSeparateEmployee() {
        return employeeRepository
                .findAll()
                .stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() >= 25));
    }

    // Query 15 : Who is the oldest employee in the organization? What is his age
    // and which department he belongs to?
    public Employee getOldestEmployee() {
        return employeeRepository
                .findAll()
                .stream()
                .max(Comparator.comparingInt(Employee::getAge))
                .orElseThrow(() -> new NotFoundException("No employee found."));
    }
}
