package com.carlocodes.clipped.repositories;

import com.carlocodes.clipped.entities.Buddy;
import com.carlocodes.clipped.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, Long> {
    boolean existsBySenderAndReceiverAndAcceptedIsTrue(User sender, User receiver);

    boolean existsBySenderAndReceiverAndAcceptedIsNull(User sender, User receiver);
}
