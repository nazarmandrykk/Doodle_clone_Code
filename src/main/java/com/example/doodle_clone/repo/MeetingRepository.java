package com.example.doodle_clone.repo;
import com.example.doodle_clone.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MeetingRepository extends JpaRepository<Meeting,Long> { //репозиторій зустрічі
}
