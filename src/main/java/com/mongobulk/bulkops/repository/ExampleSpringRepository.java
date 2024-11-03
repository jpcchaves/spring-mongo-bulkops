package com.mongobulk.bulkops.repository;

import com.mongobulk.bulkops.model.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleSpringRepository extends MongoRepository<Example, String> {

}
