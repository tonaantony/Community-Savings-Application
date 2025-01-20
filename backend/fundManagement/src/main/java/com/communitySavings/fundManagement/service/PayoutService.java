package com.communitySavings.fundManagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communitySavings.fundManagement.model.Payout;
import com.communitySavings.fundManagement.repository.PayoutRepository;

import java.util.List;

@Service
public class PayoutService {
    @Autowired
    private PayoutRepository payoutRepository;

    public PayoutService(PayoutRepository payoutRepository) {
        this.payoutRepository = payoutRepository;
    }

    public Payout savePayout(Payout payout) {
        return payoutRepository.save(payout);
    }

    public List<Payout> getPayoutsByGroupId(Long groupId) {
        return payoutRepository.findByGroupId(groupId); // Filter for groupId if needed
    }

    public List<Payout> getPayoutsByUserId(Long userId) {
        return payoutRepository.findByUserId(userId); // Filter for userId if needed
    }

    public List<Payout> getPayouts(){
        return payoutRepository.findAll();
    }
}
