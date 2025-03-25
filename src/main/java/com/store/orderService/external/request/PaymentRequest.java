package com.store.orderService.external.request;

import com.store.orderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PaymentRequest {
    private Long orderId ;
    private long amount ;
    private String referenceNum ;
    private PaymentMode paymentMode ;
}
