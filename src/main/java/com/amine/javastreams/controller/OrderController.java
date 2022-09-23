package com.amine.javastreams.controller;

import com.amine.javastreams.exception.NotFoundException;
import com.amine.javastreams.model.Customer;
import com.amine.javastreams.model.Order;
import com.amine.javastreams.model.Product;
import com.amine.javastreams.service.OrdersStreamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 07/03/2022 16:00
 * @description
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    public final OrdersStreamService ordersStreamService;

    public OrderController(OrdersStreamService ordersStreamService) {
        this.ordersStreamService = ordersStreamService;
    }

    // Query 1 — Obtain a list of products belongs to category “Books” with price > 100
    @GetMapping("/getListOfProductWithCategoryBook")
    List<Product> getListOfProductWithCategoryBook() {
        return this.ordersStreamService.getListOfProductWithCategoryBook();
    }

    // Query 2 — Obtain a list of order with products belong to category “Baby”
    @GetMapping("/getListOfOrderWithCategoryBaby")
    List<Order> getListOfOrderWithCategoryBaby() {
        return this.ordersStreamService.getListOfOrderWithCategoryBaby();
    }

    // Query 3 — Obtain a list of product with category = “Toys” and then apply 10% discount
    @GetMapping("/getListOfProductWithCategoryToys10Discount")
    List<Product> getListOfProductWithCategoryToys10Discount() {
        return this.ordersStreamService.getListOfProductWithCategoryToys10Discount();
    }

    // Query 4 — Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
    @GetMapping("/getListOfProductOrderedByCustomer")
    List<Product> getListOfProductOrderedByCustomer() {
        return this.ordersStreamService.getListOfProductOrderedByCustomer();
    }

    // Query 5 — Get the cheapest products of “Books” category
    @GetMapping("/getListOfCheapesProductsOfBooks")
    Product getListOfCheapesProductsOfBooks() {
        return this.ordersStreamService.getListOfCheapesProductsOfBooks().orElseThrow(NotFoundException::new);
    }

    // Query 6 — Get the 3 most recent placed order
    @GetMapping("/getListOfMostRecentPlacedOrder")
    List<Order> getListOfMostRecentPlacedOrder() {
        return this.ordersStreamService.getListOfMostRecentPlacedOrder();
    }

    // Query 7 — Get a list of orders which were ordered on 15-Mar-2021, log the order records to the console and then return its product list
    @GetMapping("/getListOfOrderOrderedOnDate")
    List<Product> getListOfOrderOrderedOnDate() {
        return this.ordersStreamService.getListOfOrderOrderedOnDate();
    }

    // Query 8 — Calculate total lump sum of all orders placed in Feb 2021
    @GetMapping("/getSumOfAllOrdersPlaced")
    Double getSumOfAllOrdersPlaced() {
        return this.ordersStreamService.getSumOfAllOrdersPlaced();
    }

    // Query 9 — Calculate order average payment placed on 14-Mar-2021
    @GetMapping("/getOrderAveragePaymentPlaced")
    Double getOrderAveragePaymentPlaced() {
        return this.ordersStreamService.getOrderAveragePaymentPlaced();
    }

    // Query 10 — Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
    @GetMapping("/getCountMaleFemale")
    String getSumAverageMaxMinCountForProductsOfCategoryBooks() {
        return this.ordersStreamService.getSumAverageMaxMinCountForProductsOfCategoryBooks();
    }

    // Query 11 — Obtain a data map with order id and order’s product count
    @GetMapping("/getMapWithOrderRecordsGroupedByCustomer")
    Map<Long, Integer> getMapWithOrderRecordsGroupedByCustomer() {
        return this.ordersStreamService.getMapWithOrderRecordsGroupedByCustomer();
    }

    // Query 12 — Produce a data map with order records grouped by customer
    @GetMapping("/getListOfOrderRecordsGroupedByCustomer")
    Map<Customer, List<Order>> getListOfOrderRecordsGroupedByCustomer() {
        return this.ordersStreamService.getListOfOrderRecordsGroupedByCustomer();
    }

    // Query 13 — Produce a data map with order record and product total sum
    @GetMapping("/getListOfOrderRecordAndProductTotalSum")
    Map<Order, Double> getListOfOrderRecordAndProductTotalSum() {
        return this.ordersStreamService.getListOfOrderRecordAndProductTotalSum();
    }

    // Query 14 — Obtain a data map with list of product name by category
    @GetMapping("/getListOfProductNameByCategory")
    Map<String, List<String>> getListOfProductNameByCategory() {
        return this.ordersStreamService.getListOfProductNameByCategory();
    }

    // Query 15 — Get the most expensive product by category
    @GetMapping("/getListOfMostExpensiveProductByCategory")
    Map<String, Optional<Product>> getListOfMostExpensiveProductByCategory() {
        return this.ordersStreamService.getListOfMostExpensiveProductByCategory();
    }

}