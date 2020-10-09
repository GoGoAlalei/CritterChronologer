package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet savePet(Pet pet) {

        Pet savepet = petRepository.save(pet);
        Customer customer = savepet.getCustomer();
        List<Pet> pets = customer.getPets();
        if (pets == null) {
            pets = new ArrayList<>();
        }
        pets.add(pet);
        customer.setPets(pets);
        customerRepository.save(customer);

        return savepet;

    }

    public Pet getPet(long petId) { return petRepository.getOne(petId); }

    public List<Pet> getPets() { return petRepository.findAll(); }

    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.findAllByCustomer_Id(ownerId);
    }

}
