package com.sumrid_k.pos.Bill.repository;

import com.sumrid_k.pos.Bill.model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {
    Bill findById(long id);
    ArrayList<Bill> findAll();
    ArrayList<Bill> findAllByDate(Date date);
    ArrayList<Bill> findAllByUserNameContains(String name);
    ArrayList<Bill> findAllByCompanyNameContains(String companyName);
}
