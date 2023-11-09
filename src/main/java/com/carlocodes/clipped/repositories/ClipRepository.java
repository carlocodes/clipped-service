package com.carlocodes.clipped.repositories;

import com.carlocodes.clipped.entities.Clip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClipRepository extends JpaRepository<Clip, Long> {
}
