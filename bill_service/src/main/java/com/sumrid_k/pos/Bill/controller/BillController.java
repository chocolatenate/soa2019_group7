package com.sumrid_k.pos.Bill.controller;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sumrid_k.pos.Bill.model.Bill;
import com.sumrid_k.pos.Bill.model.Product;
import com.sumrid_k.pos.Bill.model.ProductQuantity;
import com.sumrid_k.pos.Bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private Gson gson;

    @GetMapping("/")
    public String index() {
        return "<h1>Hello, this is bill service</h1>";
    }

    @GetMapping("/bills")
    @HystrixCommand(fallbackMethod = "fallbackBills")
    public ArrayList<Bill> getBills(){
        return billService.getAll();
    }

    @GetMapping("/bills/{id}")
    @HystrixCommand(fallbackMethod = "fallbackBill")
    public Bill getBill(@PathVariable long id){
        return billService.getBill(id);
    }

    @PostMapping("/bills")
    public ResponseEntity createBill(@RequestBody Bill bill){
        billService.saveBill(bill);
        return ResponseEntity.status(HttpStatus.CREATED).body(bill);
    }

    @PutMapping("/bills/{id}")
    public ResponseEntity updateBills(@PathVariable int id, @RequestBody Bill bill){
        HttpStatus status;
        if(billService.updateBill(id, bill)) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(status).body(bill);
    }

    @DeleteMapping("/bills/{id}")
    public ResponseEntity deleteBills(@PathVariable int id){
        if(billService.deleteBill(id)) {
            return new ResponseEntity("Bill is deleted successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity("Deleted fail", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bills/name/{name}")
    public ArrayList<Bill> getByName(@PathVariable String name) {
        return billService.getByName(name);
    }

    @GetMapping("/bills/date/{date}")
    public ArrayList<Bill> getByDate(@PathVariable String date) {
        return billService.getByDate(date);
    }

    @GetMapping("/bills/company/{name}")
    public ArrayList<Bill> getByCompanyName(@PathVariable String name) {
        return billService.getByCompanyName(name);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        Product p1 = new Product();
        p1.setDetail("for test product");
        p1.setName("Mock product 1");
        p1.setPrice(199.9);
        p1.setQuantity(50);
        p1.setImg("www.test.com/test.png");

        List<ProductQuantity> productQuantities = new ArrayList<>();
        productQuantities.add(new ProductQuantity(gson.toJson(p1),1));

        Bill bill = new Bill();
        bill.setDate(new Date());
        bill.setProductQuantities(productQuantities);
        bill.setCompanyName("Apple Inc.");
        bill.setUserName("Jo Samon");
        billService.saveBill(bill);

        return ResponseEntity.status(HttpStatus.CREATED).body(bill);
    }

    public ArrayList<Bill> fallbackBills() {
        ArrayList<Bill> bills = new ArrayList<>();
        ArrayList<ProductQuantity> productQuantities = new ArrayList<>();
        Bill bill = new Bill();
        bill.setUserName("Request fails.");
        bill.setDate(new Date());
        bill.setCompanyName("Please try again.");
        bill.setTotalPrice(0);
        bill.setProductQuantities(productQuantities);
        bills.add(bill);
        return bills;
    }

    public Bill fallbackBill(long id) {
        Bill bill = new Bill();
        bill.setId(id);
        bill.setCompanyName("Request fails.");
        bill.setUserName("Please try again.");
        return bill;
    }
}