package com.carlocodes.clipped.repositories;

import com.carlocodes.clipped.entities.Clip;
import com.carlocodes.clipped.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;

@Repository
public interface ClipRepository extends JpaRepository<Clip, Long> {
    List<Clip> findByUser(User user);

    LinkedHashSet<Clip> findByCreatedDateTimeGreaterThanEqualOrderByLikesDescCreatedDateTimeDesc(LocalDateTime past);
}
