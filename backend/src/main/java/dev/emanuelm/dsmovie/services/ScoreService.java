package dev.emanuelm.dsmovie.services;


import dev.emanuelm.dsmovie.dto.MovieDTO;
import dev.emanuelm.dsmovie.dto.ScoreDTO;
import dev.emanuelm.dsmovie.entities.Movie;
import dev.emanuelm.dsmovie.entities.Score;
import dev.emanuelm.dsmovie.entities.User;
import dev.emanuelm.dsmovie.repositories.MovieRepository;
import dev.emanuelm.dsmovie.repositories.ScoreRepository;
import dev.emanuelm.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired //It injects this dependency in the class
    private MovieRepository movieRepository;

    @Autowired //It injects this dependency in the class
    private UserRepository userRepository;

    @Autowired //It injects this dependency in the class
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO dto){
        User user = userRepository.findByEmail(dto.getEmail());

        if(user == null){
            user = new User();
            user.setEmail(dto.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(dto.getMovieId()).get();

        Score score = new Score();

        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());

        score = scoreRepository.saveAndFlush(score);

        double sum = 0.0;
        for (Score s : movie.getScores()){
            sum =  sum + s.getValue();
        }
        double avg = sum / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());

        movie = movieRepository.save(movie);
        return new MovieDTO(movie);
    }

}
