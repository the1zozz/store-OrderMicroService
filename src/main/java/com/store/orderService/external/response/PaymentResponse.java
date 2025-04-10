package com.store.orderService.external.response;

import com.store.orderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PaymentResponse {

    private long paymentId ;
    private String status ;
    private  long amount ;
    private long orderId ;
    private PaymentMode paymentMode ;
    private Instant paymentDate ;

}
