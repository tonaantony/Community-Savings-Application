package com.communitySavings.fundManagement.controller;

import com.communitySavings.fundManagement.model.Payout;
import com.communitySavings.fundManagement.service.PayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payouts")
public class PayoutController {
    @Autowired
    private PayoutService payoutService;

    @PostMapping
    public ResponseEntity<Payout> addPayout(@RequestBody Payout payout) {
        Payout savedPayout = payoutService.savePayout(payout);
        return ResponseEntity.ok(savedPayout);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Payout>> getPayoutsByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(payoutService.getPayoutsByGroupId(groupId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payout>> getPayoutsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(payoutService.getPayoutsByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<Payout>> getAllPayouts() {
        return ResponseEntity.ok(payoutService.getPayouts());
    }
}

