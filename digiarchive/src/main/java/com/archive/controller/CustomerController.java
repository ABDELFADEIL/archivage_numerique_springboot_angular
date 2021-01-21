package com.archive.controller;


import com.archive.dto.CustomerDto;
import com.archive.entity.CustomerEntity;
import com.archive.service.ICustomerService;
import com.archive.service.IDocumentService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

    private final ICustomerService customerService;
    private final IDocumentService documentService;

    public CustomerController(ICustomerService customerService, IDocumentService documentService) {
        this.customerService = customerService;
        this.documentService = documentService;
    }


    @PostMapping("add-customer")
    public CustomerEntity addCustomer(@RequestBody(required = true) CustomerDto customerDto) {
        return customerService.add(customerDto);
    }



    @GetMapping(value="get-customer-by-id", produces = { "application/json;charset=UTF-8" }, consumes = {"application/json;charset=UTF-8" })
    public CustomerEntity findByCientId(@RequestParam(name = "customerId",  required = true) Integer customerId) {
        return customerService.findById(customerId);
    }

    @GetMapping("get-customer-by-number")
    public ResponseEntity<CustomerEntity> findOnByClientNumber(@RequestParam(name = "customer_number", required = true) String customer_number) {
        CustomerEntity customerEntity = customerService.findOnByClientNumber(customer_number);
        return new ResponseEntity<CustomerEntity>(customerEntity, HttpStatus.OK);
    }

    @PutMapping("update")
    public CustomerEntity updateCustomer(@RequestBody(required = true) CustomerDto customerDto) {
        return customerService.update(customerDto);
    }


    @DeleteMapping("/delete-customer")
    public void removeClient(@RequestParam(required = true, name = "customerId") Integer customerId) {
        customerService.delete(customerId);
    }

    @GetMapping(value="/get-customers-by-name", produces =  {"application/json;charset=UTF-8" })
    public Set<CustomerEntity> getClientsByNameContains(@RequestParam(name = "customerName",required = true) String customerName) {
        return customerService.getClientsByNameContains(customerName);
    }

    @GetMapping("/get-customers-by-account-number")
    public Set<CustomerEntity> getClientByClientNumberContains(@RequestParam(name="clientNumber", required = true) String clientNumber) {
        return customerService.getClientByClientNumberContains(clientNumber);
    }

    @GetMapping("/get-customers-by-client-name-number")
    public Set<CustomerEntity> getClientByClientNameOrClientNumber(@RequestParam(name="client_name") String clientName, @RequestParam(name="client_number")String clientNumber) {
        System.out.println(clientName);
        System.out.println(clientNumber);
        if (clientName == "undefined" || clientName == null){
            clientName = "";
        }
        if (clientNumber == "undefined" || clientNumber == null){
            clientNumber = "";
        }
        return customerService.findByCustomerNameOrCustomerNumberContains(clientName, clientNumber);
    }

}
