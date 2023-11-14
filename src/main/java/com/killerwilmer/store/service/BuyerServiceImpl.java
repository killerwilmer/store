package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Buyer;
import com.killerwilmer.store.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService {

  private final BuyerRepository buyerRepository;

  @Autowired
  public BuyerServiceImpl(BuyerRepository buyerRepository) {
    this.buyerRepository = buyerRepository;
  }

  @Override
  public List<Buyer> getAllBuyers() {
    return buyerRepository.findAll();
  }

  @Override
  public Optional<Buyer> getBuyerById(Integer id) {
    return buyerRepository.findById(id);
  }

  @Override
  public Buyer createBuyer(Buyer buyer) {
    return buyerRepository.save(buyer);
  }

  @Override
  public Optional<Buyer> updateBuyer(Integer id, Buyer updatedBuyer) {
    if (buyerRepository.existsById(id)) {
      updatedBuyer.setId(id);
      return Optional.of(buyerRepository.save(updatedBuyer));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void deleteBuyer(Integer id) {
    buyerRepository.deleteById(id);
  }
}
