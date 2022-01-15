package dev.emanuelm.dsmovie.services;

import dev.emanuelm.dsmovie.dto.MovieDTO;
import dev.emanuelm.dsmovie.entities.Movie;
import dev.emanuelm.dsmovie.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    @Autowired //It injects this dependency in the class
    private MovieRepository repository;

    //readOnly tells that this function ll only ready in the db. It makes the process more
    //efficent
    @Transactional(readOnly = true)
    public Page<MovieDTO> findAll(Pageable pageable){ //this pageable makes possible to make operations as pages
        Page<Movie> result = repository.findAll(pageable);
        Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
        return page;
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id){ //this pageable makes possible to make operations as pages
        Movie result = repository.findById(id).get();
        MovieDTO dto = new MovieDTO(result);
        return dto;
    }

}
