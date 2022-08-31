package com.amine.javastreams.repository;

import com.amine.javastreams.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 07/03/2022 15:10
 * @description
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
