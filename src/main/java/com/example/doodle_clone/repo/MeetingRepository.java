package com.example.doodle_clone.repo;
import com.example.doodle_clone.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {



public interface MeetingRepository extends JpaRepository<Meeting,Long> { //репозиторій зустрічі

}
