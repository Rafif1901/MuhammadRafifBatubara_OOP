package com.Muhammad.backend.repository;

import com.Muhammad.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {

    Optional<Player> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM players ORDER BY high_score DESC LIMIT :limit", nativeQuery = true)
    List<Player> findTopPlayerByHighScore(@Param("limit") int limit);

    List<Player> findByHighScoreGreaterThan(Integer minScore);

    List<Player> findAllByOrderByTotalCoinsDesc();

    List<Player> findAllByOrderByTotalDistanceTravelledDesc();
}
