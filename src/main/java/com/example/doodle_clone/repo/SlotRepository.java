package com.example.doodle_clone.repo;

import com.example.doodle_clone.models.Meeting;
import com.example.doodle_clone.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface SlotRepository extends JpaRepository<Slot,Long> {
    List<Slot> findByMeetingId(Long id);

}




