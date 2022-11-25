package com.othello.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.othello.model.Grid;

public interface GridRepository extends MongoRepository<Grid, String> {

    Optional<Grid> findTopByOrderByIdDesc();
    
}
