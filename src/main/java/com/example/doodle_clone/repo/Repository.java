package com.example.doodle_clone.repo;
import com.example.doodle_clone.models.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends  CrudRepository<Meeting,Long>{
}
