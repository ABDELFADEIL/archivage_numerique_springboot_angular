package com.archive.service;


import com.archive.dao.CustomerRepository;
import com.archive.dto.CustomerDto;
import com.archive.entity.CustomerEntity;
import com.archive.entity.EventStatus;
import com.archive.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomerServiceImpl implements ICustomerService{

    private final CustomerRepository customerRepository;
    private final IUserService userService;
    private final IEventService eventService;


    public CustomerServiceImpl(CustomerRepository customerRepository, IUserService userService, IEventService eventService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
        this.eventService = eventService;
    }


    @Override
    public CustomerEntity findById(Integer customerId) {
        return customerRepository.findById(customerId).get();
    }

    @Override
    public CustomerEntity add(CustomerDto customerDto) {
        String client_number = createNewClientNumber();
        UserEntity user = userService.getAuthentificatedUser();

        CustomerEntity customer = new CustomerEntity(client_number, customerDto.getClient_nature_id(), customerDto.getClient_name(), customerDto.getClient_first_name(),
                customerDto.getCivility_id(), customerDto.getBirth_date(), customerDto.getBirth_dept(), customerDto.getSiren_number(), customerDto.getSiret_number(), user.getId(), EventStatus.START_CUSTOMER_RELATIONSHIP);

        return customerRepository.save(customer);
    }

    @Override
    public CustomerEntity update(CustomerDto customerDto) {
        CustomerEntity customerEntity = findById(customerDto.getId());
        if (customerEntity == null) throw new RuntimeException("id does not exist!");

        if (customerDto.getClient_name() != customerEntity.getClient_name()){
            customerEntity.setClient_name(customerDto.getClient_name());
        }
        if (customerDto.getClient_first_name() != customerEntity.getClient_first_name()){
            customerEntity.setClient_first_name(customerDto.getClient_first_name());
        }
        if (customerDto.getSiren_number() != customerEntity.getSiren_number()){
            customerEntity.setSiren_number(customerDto.getSiren_number());
        }
        if (customerDto.getSiret_number() != customerEntity.getSiret_number()){
            customerEntity.setSiret_number(customerDto.getSiret_number());
        }
        if (customerDto.getBirth_date() != customerEntity.getBirth_date()){
            customerEntity.setBirth_date(customerDto.getBirth_date());
        }
        if (customerDto.getBirth_dept() != customerEntity.getBirth_dept()){
            customerEntity.setBirth_dept(customerDto.getBirth_dept());
        }
        if (customerDto.getCivility_id() != customerEntity.getCivility_id()){
            customerEntity.setCivility_id(customerDto.getCivility_id());
        }
        if (customerDto.getStatus() != customerEntity.getStatus()){
            customerEntity.setStatus(customerDto.getStatus());
        }

        return customerRepository.save(customerEntity);
    }

    @Override
    public void delete(Integer customerId) {
         customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerEntity findOnByClientNumber(String clientNumber) {
        return customerRepository.findByClientNumber(clientNumber);
    }

    @Override
    public Set<CustomerEntity> getClientsByNameContains(String client_name) {
        return customerRepository.findByClientNumberContains(client_name);
    }

    @Override
    public Set<CustomerEntity> getClientByClientNumberContains(String clientNumber) {
        return customerRepository.findByClientNumberContains(clientNumber);
    }

    @Override
    public String createNewClientNumber() {
        String client_number_pre = customerRepository.getMaxClientNumber();
        if (client_number_pre==null){
            return "000000000001";
        }
        long number_account =  Long.parseLong(client_number_pre);
        long new_number_account = number_account + 1;
        String client_number_nex = "00000000000".substring(String.valueOf(new_number_account).length()+1)+new_number_account;
        return client_number_nex;
    }

    @Override
    public Set<CustomerEntity> findByCustomerNameOrCustomerNumberContains(String client_name, String client_number) {
        return customerRepository.findByClientNameOrClientNumberContains(client_name, client_number);
    }
}
