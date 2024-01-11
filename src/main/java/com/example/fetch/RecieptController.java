package com.example.fetch;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RecieptController {

    @Autowired
    RecieptService recieptService;

    @PostMapping("/receipts/process")
    public ResponseEntity addReciept(@RequestBody Reciept reciept) {
        Map<String, UUID> data = new HashMap<>();
        recieptService.saveReceipt(reciept);

        data.put("id", reciept.getId());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/receipts/{id}/points")
    public ResponseEntity getUserById(@PathVariable UUID id) {
        Map<String, Double> data = new HashMap<>();
        data.put("points", recieptService.findReceiptById(id));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
