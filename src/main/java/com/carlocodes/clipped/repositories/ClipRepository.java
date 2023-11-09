package com.carlocodes.clipped.repositories;

import com.carlocodes.clipped.entities.Clip;
import com.carlocodes.clipped.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClipRepository extends JpaRepository<Clip, Long> {
    List<Clip> findByUser(User user);
}
