package com.othello.repositories;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.othello.model.Score;

public interface ScoreRepository extends MongoRepository<Score, String> {

    @Query("{ 'score' : { $gt : 0 } }")
    ArrayList<Score> findTop10ByOrderByScoreDesc();
    
}
