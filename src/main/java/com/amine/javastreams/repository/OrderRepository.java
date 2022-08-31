package com.amine.javastreams.repository;

import com.amine.javastreams.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 07/03/2022 15:10
 * @description
 */
@Component
public interface OrderRepository extends JpaRepository<Order, Long> {

}