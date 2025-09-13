package com.spring_batch.shopping_mall.transaction.aspect;

import com.spring_batch.shopping_mall.order.dto.OrderResult;
import com.spring_batch.shopping_mall.transaction.domain.TransactionStatus;
import com.spring_batch.shopping_mall.transaction.domain.TransactionType;
import com.spring_batch.shopping_mall.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TransactionLoggingAspect {

    private final TransactionService transactionService;

    //order 호출
    @Pointcut("execution(* com.spring_batch.shopping_mall.order.service.OrderService.order(..))")
    public void orderCreation() {
    }

    //성공했을 때 생성된 로그 newOrder
    @AfterReturning(pointcut = "orderCreation()", returning = "newOrder")
    public void logOrderCreationSuccess(Object newOrder) {
        transactionService.logTransaction(
                TransactionType.ORDER_CREATION,
                TransactionStatus.SUCCESS,
                "주문이 성공적으로 생성되었습니다. 결제를 완료해주세요.",
                (OrderResult) newOrder
        );
    }

    //orderCreation()가 실행되다가 중간에 에러 났을때 로그
    @AfterThrowing(pointcut = "orderCreation()", throwing = "exception")
    public void logOrderCreationFailure(Exception exception) {
        transactionService.logTransaction(
                TransactionType.ORDER_CREATION,
                TransactionStatus.FAILURE,
                "주문 생성 중 오류 발생: " + exception.getMessage(),
                null
        );
    }

    @Pointcut("execution(* com.spring_batch.shopping_mall.order.service.OrderService.completePayment(..))")
    public void paymentCompletion() {
    }

    // 결제 성공 시 로그 기록
    @AfterReturning(pointcut = "paymentCompletion()", returning = "updatedOrder")
    public void logPaymentCompletionSuccess(Object updatedOrder) {
        if (((OrderResult) updatedOrder).isPaymentSuccess()) {
            transactionService.logTransaction(
                    TransactionType.PAYMENT_COMPLETION,
                    TransactionStatus.SUCCESS,
                    "결제 처리가 완료되었습니다.",
                    (OrderResult) updatedOrder);
        } else {
            transactionService.logTransaction(
                    TransactionType.PAYMENT_COMPLETION,
                    TransactionStatus.FAILURE,
                    "결제 처리가 실패되었습니다.",
                    (OrderResult) updatedOrder);
        }
    }

    @AfterThrowing(pointcut = "paymentCompletion()", throwing = "exception")
    public void logPaymentCompletionFailure(Exception exception) {
        transactionService.logTransaction(
                TransactionType.PAYMENT_COMPLETION,
                TransactionStatus.FAILURE,
                "결제 처리 중 오류 발생: " + exception.getMessage(),
                null
        );
    }


    @Pointcut("execution(* com.spring_batch.shopping_mall.order.service.OrderService.completeOrder(..))")
    public void orderComplete() {
    }

    @AfterReturning(pointcut = "orderComplete()", returning = "completedOrder")
    public void logOrderCompletionSuccess(Object completedOrder) {
        transactionService.logTransaction(
                TransactionType.ORDER_COMPLETION,
                TransactionStatus.SUCCESS,
                "주문이 성공적으로 완료되었습니다.",
                (OrderResult) completedOrder
        );
    }

    @AfterThrowing(pointcut = "orderComplete()", throwing = "exception")
    public void logOrderCompletionFailure(Exception exception) {
        transactionService.logTransaction(
                TransactionType.ORDER_COMPLETION,
                TransactionStatus.FAILURE,
                "주문 완료 중 오류 발생: " + exception.getMessage(),
                null
        );
    }

    @Pointcut("execution(* com.spring_batch.shopping_mall.order.service.OrderService.cancelOrder(..))")
    public void orderCancel() {
    }

    @AfterReturning(pointcut = "orderCancel()", returning = "cancelledOrder")
    public void logOrderCancellationSuccess(Object cancelledOrder) {
        transactionService.logTransaction(
                TransactionType.ORDER_CANCELLATION,
                TransactionStatus.SUCCESS,
                "주문이 성공적으로 취소되었습니다.",
                (OrderResult) cancelledOrder
        );
    }

    @AfterThrowing(pointcut = "orderCancel()", throwing = "exception")
    public void logOrderCancellationFailure(Exception exception) {
        transactionService.logTransaction(
                TransactionType.ORDER_CANCELLATION,
                TransactionStatus.FAILURE,
                "주문 취소 중 오류 발생: " + exception.getMessage(),
                null
        );
    }

}