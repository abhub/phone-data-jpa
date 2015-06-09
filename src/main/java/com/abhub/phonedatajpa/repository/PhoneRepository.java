package com.abhub.phonedatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhub.phonedatajpa.domain.People;
import com.abhub.phonedatajpa.domain.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findByOwnerIn(List<People> peoples);

    List<Phone> findByOwnerId(long id);

}
