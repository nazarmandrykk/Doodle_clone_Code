package com.example.doodle_clone.repo;

import com.example.doodle_clone.models.Meeting;
import com.example.doodle_clone.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository

public interface SlotRepository extends JpaRepository<Slot,Long> {
    List<Slot> findByMeetingId(Long id);

}




