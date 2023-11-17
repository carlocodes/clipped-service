package com.carlocodes.clipped.repositories;

import com.carlocodes.clipped.entities.Buddy;
import com.carlocodes.clipped.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> {
    boolean existsBySenderAndReceiverAndAcceptedIsTrue(User sender, User receiver);

    boolean existsBySenderAndReceiverAndAcceptedIsNull(User sender, User receiver);

    Optional<Buddy> findBySenderAndReceiverAndAcceptedIsNull(User sender, User receiver);

    Optional<Buddy> findBySenderAndReceiverAndAcceptedIsTrue(User sender, User receiver);

    Set<Buddy> findByReceiverAndAcceptedIsNull(User user);
}
