package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return convertScheduleToScheduleDTO(scheduleService.createSchedule(convertScheduleDTOToSchedule(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        scheduleService.getAllSchedules().forEach( schedule -> scheduleDTOS.add(convertScheduleToScheduleDTO(schedule)));
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        scheduleService.getScheduleForPet(petId).forEach( schedule -> scheduleDTOS.add(convertScheduleToScheduleDTO(schedule)));
        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        scheduleService.getScheduleForEmployee(employeeId).forEach( schedule -> scheduleDTOS.add(convertScheduleToScheduleDTO(schedule)));
        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        scheduleService.getScheduleForCustomer(customerId).forEach( schedule -> scheduleDTOS.add(convertScheduleToScheduleDTO(schedule)));
        return scheduleDTOS;
    }

    public ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        if(schedule.getEmployees() != null) {
            List<Long> employeeIds = new ArrayList<>();
            schedule.getEmployees().forEach( employee -> employeeIds.add(employee.getId()));
            scheduleDTO.setEmployeeIds(employeeIds);
        }
        if(schedule.getPets() != null) {
            List<Long> petIds = new ArrayList<>();
            schedule.getPets().forEach( pet -> petIds.add(pet.getId()));
            scheduleDTO.setPetIds(petIds);
        }
        return scheduleDTO;
    }

    public Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        if(scheduleDTO.getEmployeeIds() != null) {
            List<Employee> employees = new ArrayList<>();
            scheduleDTO.getEmployeeIds().forEach( employeeId -> employees.add(employeeService.getEmployee(employeeId)));
            schedule.setEmployees(employees);
        }
        if(scheduleDTO.getPetIds() != null) {
            List<Pet> pets = new ArrayList<>();
            scheduleDTO.getPetIds().forEach( petId -> pets.add(petService.getPet(petId)));
            schedule.setPets(pets);
        }
        return schedule;
    }

}
