package com.spring_batch.shopping_mall.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring_batch.shopping_mall.global.error.CustomException;
import com.spring_batch.shopping_mall.global.error.ErrorCode;
import com.spring_batch.shopping_mall.order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Timestamp paymentDate;
    private Integer amount;

    @OneToOne
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Order order;

    public static Payment createPayment(PaymentMethod paymentMethod, Integer amount, Order order) {
        return new Payment(null, paymentMethod, PaymentStatus.PENDING,
                new Timestamp(System.currentTimeMillis()), amount, order);
    }

    public void complete() {
        if (paymentStatus != PaymentStatus.PENDING) {
            throw new CustomException(ErrorCode.ILLEGAL_PAYMENT);
        }
        paymentStatus = PaymentStatus.COMPLETED;
    }

    public void fail() {
        if (paymentStatus != PaymentStatus.PENDING) {
            throw new CustomException(ErrorCode.ILLEGAL_PAYMENT);
        }
        paymentStatus = PaymentStatus.FAILED;
    }

    public void cancel() {
        switch (paymentStatus) {
            case COMPLETED -> paymentStatus = PaymentStatus.REFUNDED;
            case PENDING, FAILED -> paymentStatus = PaymentStatus.CANCELLED;
            case CANCELLED -> throw new CustomException(ErrorCode.ILLEGAL_CANCEL);
            case REFUNDED -> throw new CustomException(ErrorCode.ILLEGAL_REFUND);
        }
    }

    @JsonIgnore
    public boolean isSuccess() {
        return paymentStatus == PaymentStatus.COMPLETED;
    }
}
