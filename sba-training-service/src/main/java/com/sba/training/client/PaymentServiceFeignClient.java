package com.sba.training.client;

import com.sba.security.model.ApiResponse;
import com.sba.training.model.PaymentCommDtls;
import com.sba.training.model.PaymentDtls;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "payment-service", fallback = PaymentServiceFallback.class)
@RibbonClient(value = "payment-service")
public interface PaymentServiceFeignClient {

	@GetMapping("/payments/findTotalPaidAmountByMentorId/{mentorId}")
	public PaymentDtls findTotalPaidAmountByMentorId(
            @PathVariable(value = "mentorId", required = true) Long mentorId,
            @PathVariable(value = "trainingId", required = true) Long trainingId);

	@PostMapping("/payments/addPayment")
	public ApiResponse<?> addPayment(
            @Valid @RequestBody PaymentDtls payment);

	@GetMapping("payments/findPaymentCommission/{id}")
	public PaymentCommDtls findPaymentCommission(
            @PathVariable(value = "id", required = true) Long id);

}

@Component
class PaymentServiceFallback implements PaymentServiceFeignClient {

	@Override
	public PaymentDtls findTotalPaidAmountByMentorId(Long mentorId, Long trainingId) {
		return new PaymentDtls();
	}

	@Override
	public ApiResponse<?> addPayment(PaymentDtls payment) {
		return new com.sba.security.model.ApiResponse<>(HttpStatus.OK.value(), null, null);
	}

	@Override
	public PaymentCommDtls findPaymentCommission(Long id) {
		return new PaymentCommDtls();
	}
}