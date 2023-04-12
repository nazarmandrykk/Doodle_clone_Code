package com.example.doodle_clone.controller;

import com.example.doodle_clone.models.Meeting;
import com.example.doodle_clone.models.Slot;
import com.example.doodle_clone.repo.MeetingRepository;
import com.example.doodle_clone.repo.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    @Autowired
    private MeetingRepository meetingRepository; //репозиторій зустрічі
    @Autowired
    private SlotRepository slotRepository;


    @GetMapping("/main_title")
    public String mainTitle(Model model) {
        Iterable<Meeting> meetings = meetingRepository.findAll();
        model.addAttribute("meetings", meetings);
        return "mainMenu";
    }

    @GetMapping("/newTable")
    public String newTable(Model model) {
        return "newTable";
    }


    @PostMapping("/newTable")
    public String createNewTable(@RequestParam String title, @RequestParam String text, @RequestParam String location, @RequestParam int count,
                                 @RequestParam("name") List<String> slotNames,
                                 @RequestParam("costMember") List<Integer> slotCosts, Model model) {
        Meeting meetings = new Meeting(title, text, location, count);
        meetingRepository.save(meetings);
        List<Slot> slots = IntStream.range(0, slotNames.size())
                .mapToObj(i -> {
                    Slot slot = new Slot(slotNames.get(i), slotCosts.get(i));
                    slot.setMeeting(meetings);
                    return slot;
                })
                .collect(Collectors.toList());
        meetings.setSlots(slots);
        slotRepository.saveAll(slots);
        return "redirect:/main_title";
    }


    @GetMapping("/meeting_info/{id}")
    public String meetingDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Meeting> meetings = meetingRepository.findById(id);
        ArrayList<Meeting> res = new ArrayList<>();
        meetings.ifPresent(res::add);
        model.addAttribute("meetings", res);

        List<Slot> slots = slotRepository.findByMeetingId(id);
        model.addAttribute("slots", slots);

        return "meeting_info";
    }

    @PostMapping("/meeting_info/{id}/remove")
    public String meetingDelete(@PathVariable(value = "id") long id, Model model) {
        Meeting meeting = meetingRepository.findById(id).orElseThrow();
        meetingRepository.delete(meeting);
        //cascad remove реалізувати
        return "redirect:/main_title";
    }

}


