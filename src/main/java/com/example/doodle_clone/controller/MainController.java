package com.example.doodle_clone.controller;
import com.example.doodle_clone.models.Meeting;
import com.example.doodle_clone.models.Slot;
import com.example.doodle_clone.repo.Repository;
import com.example.doodle_clone.repo.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private Repository repository; //репозиторій зустрічі
    @Autowired
    private SlotRepository slotRepository;



    @GetMapping("/main_title")
    public String mainTitle(Model model){
        Iterable<Meeting> meetings = repository.findAll();
        model.addAttribute("meetings",meetings);
        return "mainMenu";
    }

    @GetMapping("/newTable")
    public String newTable(Model model){
        return "newTable";
    }



    @PostMapping("/newTable")
    public String createNewTable(@RequestParam String title,@RequestParam String text,@RequestParam String location,@RequestParam int count,
                                 @RequestParam("name") List<String> slotNames,
                                 @RequestParam("costMember") List<Integer> slotCosts,Model model){
        Meeting meetings = new Meeting(title,text,location,count);
        repository.save(meetings);
        List<Slot> slots = new ArrayList<>();
        for (int i = 0; i < slotNames.size(); i++) {
            Slot slot = new Slot(slotNames.get(i), slotCosts.get(i));
            slot.setMeeting(meetings);
            slots.add(slot);
        }
        meetings.setSlots(slots);

        // Зберігаємо слоти в базі даних
        for (Slot slot : slots) {
            slotRepository.save(slot);
        }

        return "redirect:/main_title";
    }




    @GetMapping("/meeting_info/{id}")
    public String meetingDetails(@PathVariable(value = "id") Long id, Model model){
        Optional<Meeting> meetings = repository.findById(id);
        ArrayList<Meeting> res = new ArrayList<>();
        meetings.ifPresent(res::add);
        model.addAttribute("meetings",res);

        List<Slot> slots = slotRepository.findByMeetingId(id);
        model.addAttribute("slots", slots);

        return "meeting_info";
    }

    @PostMapping("/meeting_info/{id}/remove")
    public String meetingDelete(@PathVariable(value = "id")long id,Model model){
        Meeting meeting = repository.findById(id).orElseThrow();
        repository.delete(meeting);

        return "redirect:/main_title";
    }

}


