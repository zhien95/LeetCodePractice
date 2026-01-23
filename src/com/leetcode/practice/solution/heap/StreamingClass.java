package com.leetcode.practice.solution.heap;

import java.util.*;

public class StreamingClass {
    public static void main(String[] args) {
        AnalyticsService service = new AnalyticsService();

        // Process transactions for Food category
        service.process(new Transaction("C1", "Food", 100));
        service.process(new Transaction("C2", "Food", 300));
        service.process(new Transaction("C3", "Food", 200));
        service.process(new Transaction("C4", "Food", 500));
        service.process(new Transaction("C5", "Food", 400));
        service.process(new Transaction("C6", "Food", 50));
        service.process(new Transaction("C7", "Food", 600)); // Additional to have more than 5
        service.process(new Transaction("C8", "Food", 450)); // Additional to have more than 5
        service.process(new Transaction("C1", "Food", 1000)); // Additional to have more than 5


        // Process transactions for Travel category
        service.process(new Transaction("C1", "Travel", 800));
        service.process(new Transaction("C2", "Travel", 200));
        service.process(new Transaction("C3", "Travel", 600));
        service.process(new Transaction("C4", "Travel", 900)); // Additional to have more than 5
        service.process(new Transaction("C5", "Travel", 700)); // Additional to have more than 5
        service.process(new Transaction("C6", "Travel", 400)); // Additional to have more than 5
        service.process(new Transaction("C7", "Travel", 550)); // Additional to have more than 5

        // Print top 5 customers for Food category
        System.out.println("Top 5 customers in Food:");
        List<CustomerStat> foodTop5 = service.getTop5Customer("Food");
        for (int i = 0; i < foodTop5.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + foodTop5.get(i));
        }

        System.out.println();

        // Print top 5 customers for Travel category
        System.out.println("Top 5 customers in Travel:");
        List<CustomerStat> travelTop5 = service.getTop5Customer("Travel");
        for (int i = 0; i < travelTop5.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + travelTop5.get(i));
        }
    }

    static class Transaction {
        String customerId;
        String category;
        double amount;

        Transaction(String customerId, String category, double amount) {
            this.customerId = customerId;
            this.category = category;
            this.amount = amount;
        }
    }

    static class CustomerStat {
        String customerId;
        double totalAmount;
        Map<String, Double> categoryAmount;

        CustomerStat(String customerId) {
            this.customerId = customerId;
            this.totalAmount = 0.0;
            this.categoryAmount = new HashMap<>();
        }

        double getAmountForCategory(String category) {
            return categoryAmount.getOrDefault(category, 0.0);
        }

        @Override
        public String toString() {
            return "CustomerStat{" +
                    "customerId='" + customerId + '\'' +
                    ", totalAmount=" + totalAmount +
                    ", categoryAmount=" + categoryAmount +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CustomerStat that = (CustomerStat) obj;
            return Objects.equals(customerId, that.customerId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(customerId);
        }
    }

    static class AnalyticsService {
        // customerId -> CustomerStat
        private final Map<String, CustomerStat> customerStats = new HashMap<>();

        // category -> priority queue of CustomerStats for that category
        Map<String, PriorityQueue<CustomerStat>> topKByCategory = new HashMap<>();


        public void process(Transaction tx) {
            String category = tx.category;
            String customerId = tx.customerId;
            Double amount = tx.amount;

            // 1. Update the master record (still needed to track growth)
            CustomerStat stat = customerStats.computeIfAbsent(customerId, CustomerStat::new);
            stat.totalAmount += amount;
            double newAmount = stat.categoryAmount.getOrDefault(category, 0.0) + amount;
            stat.categoryAmount.put(category, newAmount);

            // 2. Get the Min-Heap for this category (Smallest of the Top-5 is at the head)
            PriorityQueue<CustomerStat> pq = topKByCategory.computeIfAbsent(category,
                    k -> new PriorityQueue<CustomerStat>((a, b) -> Double.compare(a.categoryAmount.get(category), b.categoryAmount.get(category))));

            // Only add if the heap isn't full, OR if this customer is now better than the current 5th place
            if (pq.size() < 5) {
                pq.offer(stat);
            } else if (newAmount > pq.peek().getAmountForCategory(tx.category)) {
                pq.poll(); // Remove the old 5th place
                pq.offer(stat); // Add the new contender
            }
        }

        public List<CustomerStat> getTop5Customer(String category) {
            PriorityQueue<CustomerStat> pq = topKByCategory.get(category);

            if (pq == null || pq.isEmpty()) {
                return new ArrayList<>();
            }

            // 1. Create a copy of the 5 elements to avoid modifying the live heap
            List<CustomerStat> result = new ArrayList<>();

            for (int i = pq.size() - 1; i >= 0; i--) {
                result.add(pq.poll());
            }
            Collections.reverse(result);

            return result;
        }
    }
}