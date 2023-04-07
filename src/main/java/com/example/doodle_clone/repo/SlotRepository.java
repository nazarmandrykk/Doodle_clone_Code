package com.example.doodle_clone.repo;

import com.example.doodle_clone.models.Meeting;
import com.example.doodle_clone.models.Slot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SlotRepository extends CrudRepository<Slot,Long> {
    List<Slot> findByMeetingId(Long id);
}




