package com.killerwilmer.store.controller;

import com.killerwilmer.store.entity.Buyer;
import com.killerwilmer.store.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

  private final BuyerService buyerService;

  @Autowired
  public BuyerController(BuyerService buyerService) {
    this.buyerService = buyerService;
  }

  @GetMapping
  public List<Buyer> getAllBuyers() {
    return buyerService.getAllBuyers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Buyer> getBuyerById(@PathVariable Integer id) {
    return buyerService
        .getBuyerById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
    Buyer createdBuyer = buyerService.createBuyer(buyer);
    return ResponseEntity.ok(createdBuyer);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Buyer> updateBuyer(
      @PathVariable Integer id, @RequestBody Buyer updatedBuyer) {
    return buyerService
        .updateBuyer(id, updatedBuyer)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBuyer(@PathVariable Integer id) {
    buyerService.deleteBuyer(id);
    return ResponseEntity.noContent().build();
  }
}
