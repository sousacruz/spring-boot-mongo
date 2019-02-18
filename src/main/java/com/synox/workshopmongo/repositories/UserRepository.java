package com.synox.workshopmongo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.synox.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
