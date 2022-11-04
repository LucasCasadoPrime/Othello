package com.othello.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.othello.model.Score;

public interface ScoreRepository extends MongoRepository<Score, String> {
    @Query("{ 'name' : ?0 }")
    Score findByName(String name);

    @Query("{ 'score' : ?0 }")
    Score findByScore(int score);

    @Query("{ 'id' : ?0 }")
    Optional<Score> findById(String id);
}
