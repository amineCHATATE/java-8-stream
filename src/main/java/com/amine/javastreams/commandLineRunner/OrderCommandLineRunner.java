package com.amine.javastreams.commandLineRunner;

import com.amine.javastreams.model.Customer;
import com.amine.javastreams.model.Order;
import com.amine.javastreams.model.Product;
import com.amine.javastreams.repository.CustomerRepository;
import com.amine.javastreams.repository.OrderRepository;
import com.amine.javastreams.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 08/03/2022 10:10
 * @description
 */
@Component
public class OrderCommandLineRunner implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    Logger logger = LoggerFactory.getLogger(OrderCommandLineRunner.class);

    public OrderCommandLineRunner(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        customerRepository.deleteAllInBatch();

        Customer customer1 = new Customer("name", 1);
        Customer customer2 = new Customer("name", 1);
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Order order1 = new Order(LocalDate.of(2021, 2, 1), LocalDate.now().plusDays(3), "Open", customer1);
        Order order2 = new Order(LocalDate.of(2021, 4, 1), LocalDate.now().plusDays(3), "Open", customer2);
        orderRepository.save(order1);
        orderRepository.save(order2);

        Product product1 = new Product("Biography", "Books", 100.00);
        Product product2 = new Product("Baby Stand", "Baby", 50.00);
        Product product3 = new Product("Mini Car", "Toys", 200.00);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        order1.getProducts().addAll(Set.of(product1, product2));
        order2.getProducts().addAll(Set.of(product2, product3));

        orderRepository.save(order1);
        orderRepository.save(order2);

        logger.info(String.format("Custom 1 : %s", customer1));
        logger.info(String.format("Custom 2 : %s", customer2));

        logger.info(String.format("Product 1 : %s", product1));
        logger.info(String.format("Product 2 : %s", product2));
        logger.info(String.format("Product 3 : %s", product3));

        logger.info(String.format("Order 1 : %s", order1));
        logger.info(String.format("Order 2 : %s", order2));

    }
}