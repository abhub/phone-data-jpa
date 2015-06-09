package com.abhub.phonedatajpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhub.phonedatajpa.domain.People;
import com.abhub.phonedatajpa.repository.PeopleRepository;

@Service
@Transactional
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public People find(Long id) {
        return peopleRepository.findOne(id);
    }

    public List<People> findAllPeoples() {
        return peopleRepository.findAll();
    }

    public List<People> findPeople(String search) {
        List<People> Peoples = new ArrayList<>();
        if (search.matches("\\d*")) {
            Peoples.addAll(peopleRepository.findByIdAndActivatedTrue(Long.valueOf(search)));
        } else {
            Peoples.addAll(peopleRepository.findByLastNameContainingOrFirstNameContainingAllIgnoreCase(search, search));
        }

        return Peoples;
    }

}
