package com.abhub.phonedatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhub.phonedatajpa.domain.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

    List<People> findByLastNameContainingOrFirstNameContainingAllIgnoreCase(String lastname, String firstname);

    List<People> findByIdAndActivatedTrue(Long id);
}
