package com.deathroll.DeathRoll.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Payment")
@RequiredArgsConstructor
public class PaymentsController {

    @PostMapping("/Deposit")
    public ResponseEntity<String> depositCashAndConvertToGold(@RequestBody float cash){
        // TODO DepositProcess using stripe

        return ResponseEntity.ok("Gold has been added to your account");
    }

    @PostMapping("/Withdraw")
    public ResponseEntity<String> withdrawCash(@RequestBody float userGold){
        // TODO convert userGold to cash and withdraw using stripe

        return ResponseEntity.ok("Your money has been withdrawn");
    }
}
