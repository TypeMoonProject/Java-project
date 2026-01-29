package order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
            new Order("1", "A", new BigDecimal("150"), LocalDateTime.of(2025, 1, 15, 10, 30)),
            new Order("2", "B", new BigDecimal("200"), LocalDateTime.of(2026, 1, 16, 9, 15)),
            new Order("3", "C", new BigDecimal("150"), LocalDateTime.of(2007, 1, 14, 14, 20))
        );

        orders.sort(
            Comparator.comparing(Order::getAmount).reversed()
            .thenComparing(Order::getCreateTime)   
        );

        for(Order ord: orders){
            System.err.println(ord);
        }
    }
}
