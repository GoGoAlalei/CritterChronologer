package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    public Schedule createSchedule(Schedule schedule) { return scheduleRepository.save(schedule); }

    public List<Schedule> getAllSchedules() { return scheduleRepository.findAll(); }

    public List<Schedule> getScheduleForPet(long petId) { return scheduleRepository.findAllByPets_Id(petId);  }

    public List<Schedule> getScheduleForEmployee(long employeeId) { return scheduleRepository.findAllByEmployees_Id(employeeId); }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        List<Schedule> schedules = new ArrayList<>();
        petRepository.findAllByCustomer_Id(customerId).forEach( pet -> schedules.addAll(scheduleRepository.findAllByPets_Id(pet.getId())));
        return schedules;
    }


}
