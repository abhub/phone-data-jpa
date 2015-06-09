package com.abhub.phonedatajpa.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhub.phonedatajpa.domain.People;
import com.abhub.phonedatajpa.service.PeopleService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private PeopleService       peopleService;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        logger.info("list all users in database");

        final List<People> peoples = peopleService.findAllPeoples();

        model.addAttribute("peoples", peoples);

        return "peoples";
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String user(@RequestParam(value = "id") Long id, Model model) {

        final List<People> peoples = new ArrayList<>();
        final People user = peopleService.find(id);
        if (user != null) {
            peoples.add(user);
        }

        model.addAttribute("peoples", peoples);

        return "peoples";

    }

    @RequestMapping(value = "/peoples", method = RequestMethod.GET)
    public String users(Model model) {
        logger.info("list all users in database");

        final List<People> peoples = peopleService.findAllPeoples();

        model.addAttribute("peoples", peoples);

        return "peoples";

    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
    public String searchUsers(@RequestParam(value = "search") String search, Model model) {

        model.addAttribute("peoples", peopleService.findPeople(search));

        return "peoples";
    }

}
