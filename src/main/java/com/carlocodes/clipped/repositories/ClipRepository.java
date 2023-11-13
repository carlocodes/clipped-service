package com.carlocodes.clipped.repositories;

import com.carlocodes.clipped.entities.Clip;
import com.carlocodes.clipped.entities.Game;
import com.carlocodes.clipped.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface ClipRepository extends JpaRepository<Clip, Long> {
    List<Clip> findByUser(User user);

    // TODO: Revisit this query, it does not work. Refer to query below
    LinkedHashSet<Clip> findByCreatedDateTimeGreaterThanEqualOrderByLikesDescCreatedDateTimeDesc(LocalDateTime past);

    @Query("SELECT c, COUNT(l) as likeCount " +
            "FROM Clip c " +
            "LEFT JOIN c.likes l " +
            "WHERE c.createdDateTime >= :past AND c.game IN :games " +
            "GROUP BY c " +
            "ORDER BY likeCount DESC, c.createdDateTime DESC")
    LinkedHashSet<Clip> watching(@Param("past") LocalDateTime past, @Param("games") Set<Game> games);
}
