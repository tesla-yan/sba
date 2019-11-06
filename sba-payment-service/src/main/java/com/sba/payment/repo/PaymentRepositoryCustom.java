package com.sba.payment.repo;

import com.sba.payment.model.PaymentDtls;

public interface PaymentRepositoryCustom {

    PaymentDtls aggregateByMentorId(Long mentorId, Long trainingId);
    
}
