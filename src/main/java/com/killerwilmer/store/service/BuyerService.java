package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Buyer;

import java.util.List;
import java.util.Optional;

public interface BuyerService {

  List<Buyer> getAllBuyers();

  Optional<Buyer> getBuyerById(Integer id);

  Buyer createBuyer(Buyer buyer);

  Optional<Buyer> updateBuyer(Integer id, Buyer updatedBuyer);

  void deleteBuyer(Integer id);
}
