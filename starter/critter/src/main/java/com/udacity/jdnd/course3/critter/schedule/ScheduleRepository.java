package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    //https://stackoverflow.com/questions/40821449/how-can-i-convert-this-3-join-query-into-a-spring-data-jpa-named-query-method
    public List<Schedule> findAllByPets_Id(Long pet_id);

    public List<Schedule> findAllByEmployees_Id(Long employee_id);

}
