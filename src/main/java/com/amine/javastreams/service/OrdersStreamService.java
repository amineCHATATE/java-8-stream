package com.amine.javastreams.service;

import com.amine.javastreams.exception.NotFoundException;
import com.amine.javastreams.model.Customer;
import com.amine.javastreams.model.Order;
import com.amine.javastreams.model.Product;
import com.amine.javastreams.repository.OrderRepository;
import com.amine.javastreams.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 07/03/2022 15:10
 * @description
 */
@Service
@RequiredArgsConstructor
public class OrdersStreamService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    // Query 1 — Obtain a list of products belongs to category “Books” with price > 100
    public List<Product> getListOfProductWithCategoryBook() {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("book"))
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());
    }

    // Query 2 — Obtain a list of order with products belong to category “Baby”
    public List<Order> getListOfOrderWithCategoryBaby() {
        return orderRepository.findAll()
                .stream()
                .filter(o -> o.getProducts().stream().anyMatch(p -> p.getCategory().equalsIgnoreCase("baby")))
                .collect(Collectors.toList());
    }

    // Query 3 — Obtain a list of product with category = “Toys” and then apply 10% discount
    public List<Product> getListOfProductWithCategoryToys10Discount() {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("toys"))
                .map(p -> p.withPrice(p.getPrice() * 0.9))
                .collect(Collectors.toList());
    }

    // Query 4 — Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
    public List<Product> getListOfProductOrderedByCustomer() {
        return orderRepository.findAll()
                .stream()
                .filter(o -> o.getCustomer().getTier() == 2)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
                .flatMap(o -> o.getProducts().stream())// to return products instead of orders
                .collect(Collectors.toList());
    }

    // Query 5 — Get the cheapest products of “Books” category
    public Optional<Product> getListOfCheapesProductsOfBooks() {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("book"))
                .min(Comparator.comparing(Product::getPrice));
    }

    // Query 6 — Get the 3 most recent placed order
    public List<Order> getListOfMostRecentPlacedOrder() {
        return orderRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    // Query 7 — Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list
    public List<Product> getListOfOrderOrderedOnDate() {
        return orderRepository.findAll()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
                .peek(o -> System.out.println(o.toString()))
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    // Query 8 — Calculate total lump sum of all orders placed in Feb 2021
    public Double getSumOfAllOrdersPlaced() {
        return orderRepository.findAll()
                .stream()
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
    }

    // Query 9 — Calculate order average payment placed on 14-Mar-2021
    public Double getOrderAveragePaymentPlaced() {
        return orderRepository.findAll()
                .stream()
                .filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 14)))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .average()
                .orElseThrow(() -> new NotFoundException("Order not found."));
    }

    // Query 10 — Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
    public String getSumAverageMaxMinCountForProductsOfCategoryBooks() {
        DoubleSummaryStatistics summaryStatistics = productRepository.findAll()
                .stream()
                .filter(o -> o.getCategory().equalsIgnoreCase("book"))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();

        return String.format("count = %1$d, average = %2$f, max = %3$f, min = %4$f, sum = %5$f%n",
                summaryStatistics.getCount(),
                summaryStatistics.getAverage(),
                summaryStatistics.getMax(),
                summaryStatistics.getMin(),
                summaryStatistics.getSum());
    }

    // Query 11 — Obtain a data map with order id and order’s product count
    public Map<Long, Integer> getMapWithOrderRecordsGroupedByCustomer() {
        return orderRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        o -> o.getProducts().size()
                ));
    }

    // Query 12 — Produce a data map with order records grouped by customer
    public Map<Customer, List<Order>> getListOfOrderRecordsGroupedByCustomer() {
        return orderRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
    }

    // Query 13 — Produce a data map with order record and product total sum
    public Map<Order, Double> getListOfOrderRecordAndProductTotalSum() {
        return orderRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        o -> o.getProducts().stream().mapToDouble(Product::getPrice).sum()
                ));
    }

    // Query 14 — Obtain a data map with list of product name by category
    public Map<String, List<String>> getListOfProductNameByCategory() {
        return productRepository.findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.mapping(Product::getName, Collectors.toList())
                        )
                );
    }

    // Query 15 — Get the most expensive product by category
    public Map<String, Optional<Product>> getListOfMostExpensiveProductByCategory() {
        return productRepository.findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.maxBy(Comparator.comparing(Product::getPrice))
                        )
                );
    }

}
