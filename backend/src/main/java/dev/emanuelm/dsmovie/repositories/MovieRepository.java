package dev.emanuelm.dsmovie.repositories;

import dev.emanuelm.dsmovie.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
