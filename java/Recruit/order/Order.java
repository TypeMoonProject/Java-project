package order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private String orderId;
    private String userId;
    private BigDecimal amount;
    private LocalDateTime createTime;

    public Order(String orderId, String userId, BigDecimal amount, LocalDateTime createTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.createTime = createTime;
    }

    public BigDecimal getAmount() { 
        return amount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getUserId(){
        return userId;
    }

    public String toString() {
        return orderId + " | " + amount + " | " + createTime;
    }
}
