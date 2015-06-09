package com.abhub.phonedatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abhub.phonedatajpa.domain.Phone;
import com.abhub.phonedatajpa.service.PhoneService;

@Controller
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value = "/phones/{id}", method = RequestMethod.GET)
    public String searchPhoneOwner(@PathVariable("id") final long id, Model model) {

        final List<Phone> phones = phoneService.searchPhoneOwner(id);

        model.addAttribute("phones", phones);

        return "phones";

    }
}
