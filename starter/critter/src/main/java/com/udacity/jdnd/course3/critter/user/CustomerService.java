package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public Customer saveCustomer(Customer customer) { return customerRepository.save(customer); }

    public List<Customer> getAllCustomers() { return customerRepository.findAll(); }

    public Customer getOwnerByPet(long petId) {
        return petRepository.getOne(petId).getCustomer();
    }

    public Customer getCustomerById(long customer_id) { return customerRepository.getOne(customer_id); }

}
