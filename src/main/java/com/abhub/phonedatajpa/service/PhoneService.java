package com.abhub.phonedatajpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhub.phonedatajpa.domain.Phone;
import com.abhub.phonedatajpa.repository.PhoneRepository;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Transactional(readOnly = true)
    public List<Phone> searchPhoneOwner(long id) {
        return phoneRepository.findByOwnerId(id);
    }
}
