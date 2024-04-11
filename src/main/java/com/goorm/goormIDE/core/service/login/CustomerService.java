//package com.goorm.goormIDE.core.service.customer;
//
//import com.example.muk_table.core.common.exception.BusinessException;
//import com.example.muk_table.core.common.response.ResponseCode;
//import com.example.muk_table.domain.primary.customer.model.entity.Customer;
//import com.example.muk_table.domain.primary.customer.repository.CustomerRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class CustomerService {
//
//    private final CustomerRepository customerRepository;
//
//    public Customer getCustomer(long customerId) {
//        return customerRepository.findById(customerId).orElseThrow(()-> new BusinessException(ResponseCode.CUSTOMER_NOT_FOUND));
//    }
//
//
//}
