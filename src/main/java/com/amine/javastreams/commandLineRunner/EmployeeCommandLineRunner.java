package com.amine.javastreams.commandLineRunner;

import com.amine.javastreams.model.Employee;
import com.amine.javastreams.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Amine Chaatte
 * @version 1.0
 * @date 07/03/2022 15:10
 * @description
 */
@Component
public class EmployeeCommandLineRunner implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeCommandLineRunner.class);

    public EmployeeCommandLineRunner(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Jiya Brein", 32, "Female", "HR", 2011, 25000.0),
                new Employee("Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0),
                new Employee("Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0),
                new Employee("Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0),
                new Employee("Nima Roy", 27, "Female", "HR", 2013, 22700.0),
                new Employee("Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0),
                new Employee("Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0),
                new Employee("Wang Liu", 31, "Male", "Product Development", 2015, 34500.0),
                new Employee("Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0),
                new Employee("Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5),
                new Employee("Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0),
                new Employee("Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0),
                new Employee("Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0),
                new Employee("Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5),
                new Employee("Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0),
                new Employee("Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0),
                new Employee("Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0)
        );

        employeeRepository.deleteAll();
        employeeRepository.saveAll(employeeList);

        logger.info(String.format("Employee list : %s", employeeList));
    }
}
