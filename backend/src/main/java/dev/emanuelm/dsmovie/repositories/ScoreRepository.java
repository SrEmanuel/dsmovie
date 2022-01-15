package dev.emanuelm.dsmovie.repositories;

import dev.emanuelm.dsmovie.entities.Score;
import dev.emanuelm.dsmovie.entities.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}
