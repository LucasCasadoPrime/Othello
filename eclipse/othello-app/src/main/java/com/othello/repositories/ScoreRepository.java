package com.othello.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.othello.model.Score;

public interface ScoreRepository extends MongoRepository<Score, String> {

}
