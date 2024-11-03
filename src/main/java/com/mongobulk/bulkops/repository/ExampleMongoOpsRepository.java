package com.mongobulk.bulkops.repository;

import com.mongobulk.bulkops.model.Example;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleMongoOpsRepository {

    private final MongoOperations mongoOps;

    public ExampleMongoOpsRepository(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    public void save(Example example) {
        mongoOps.save(example);
    }

    public void insertOrUpdateBulk(List<Example> examplesList) {
        BulkOperations bulkOps = mongoOps.bulkOps(BulkMode.UNORDERED, Example.class);

        examplesList.forEach(example -> {

            if (example.getId() == null) {
                example.setId(UUID.randomUUID().toString());
            }

            Query query = new Query(Criteria.where("id").is(example.getId()));

            Update update = new Update()
                    .set("name", example.getName())
                    .set("email", example.getEmail())
                    .set("phone", example.getPhone());

            bulkOps.upsert(query, update);
        });

        bulkOps.execute();
    }

    public void insertOrUpdateBulkInsertAll(List<Example> examplesList) {
        BulkOperations bulkOps = mongoOps.bulkOps(BulkMode.UNORDERED, Example.class);

        examplesList.forEach(example -> {

            if (example.getId() == null) {
                example.setId(UUID.randomUUID().toString());
            }

            Query query = new Query(Criteria.where("id").is(example.getId()));

            Update update = new Update()
                    .set("name", example.getName())
                    .set("email", example.getEmail())
                    .set("phone", example.getPhone());

            bulkOps.upsert(query, update);
        });

        bulkOps.execute();
    }

    public List<Example> listAll() {

        return mongoOps.findAll(Example.class);
    }
}
