package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) { return employeeRepository.save(employee); }

    public Employee getEmployee(long employeeId) { return employeeRepository.getOne(employeeId); }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {

        Set<EmployeeSkill> SkillsRequest = employeeDTO.getSkills();
        DayOfWeek DayRequest = employeeDTO.getDate().getDayOfWeek();

        List<Employee> AllEmployees = employeeRepository.findAll();
        List<Employee> MatchEmployees = new ArrayList<>();
        for (Employee employee : AllEmployees) {
            if (employee.getSkills().containsAll(SkillsRequest) && employee.getDaysAvailable().contains(DayRequest)) {
                MatchEmployees.add(employee);
            }
        }

        if(MatchEmployees.size() > 0)
            return MatchEmployees;
        else
            return null;

    }

}
