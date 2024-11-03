package com.mongobulk.bulkops.service;

import com.mongobulk.bulkops.model.Example;
import com.mongobulk.bulkops.repository.ExampleMongoOpsRepository;
import com.mongobulk.bulkops.repository.ExampleSpringRepository;
import com.mongobulk.bulkops.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private static final Logger logger = LoggerFactory.getLogger(ExampleService.class);
    private final ExampleMongoOpsRepository exampleRepository;
    private final ExampleSpringRepository exampleSpringRepository;
    private final Faker faker;

    public ExampleService(ExampleMongoOpsRepository exampleRepository,
            ExampleSpringRepository exampleSpringRepository, Faker faker) {
        this.exampleRepository = exampleRepository;
        this.exampleSpringRepository = exampleSpringRepository;
        this.faker = faker;
    }

    public String getSaveMetric() {

        List<Example> seededExamples = listAll();

        logger.info("Started save metrics....");

        List<Example> examples = new ArrayList<>();

        logger.info("Generating examples array with 10000 examples....");

        for (int i = 0; i < 10000; i++) {

            examples.add(
                    new Example(
                            faker.name().fullName(),
                            faker.internet().emailAddress(),
                            faker.phoneNumber().phoneNumber()
                    )
            );
        }

        logger.info("Addind seeded examples to new examples list..");
        examples.addAll(seededExamples);

        logger.info("Finished generating array....");

        long startTime = System.currentTimeMillis();

        logger.info("Start saving one by one....");

        examples.forEach(exampleRepository::save);

        long endTime = System.currentTimeMillis();

        long duration = TimeUtils.calcDurationInSeconds(startTime, endTime);

        logger.info("Process finished, total duration: {} seconds...", duration);

        return String.format("This operation took %s seconds to finish", duration);
    }


    public String getSaveBulkMetric() {

        List<Example> seededExamples = listAll();

        logger.info("Started save bulk metrics....");

        List<Example> examples = new ArrayList<>();

        logger.info("Generating examples array with 10000 examples....");

        for (int i = 0; i < 10000; i++) {

            examples.add(
                    new Example(
                            faker.name().fullName(),
                            faker.internet().emailAddress(),
                            faker.phoneNumber().phoneNumber()
                    )
            );
        }

        logger.info("Finished generating array....");

        logger.info("Addind seeded examples to new examples list..");
        examples.addAll(seededExamples);

        long startTime = System.currentTimeMillis();

        logger.info("Start saving in bulk....");

        exampleRepository.insertOrUpdateBulk(examples);

        long endTime = System.currentTimeMillis();

        long duration = TimeUtils.calcDurationInSeconds(startTime, endTime);

        logger.info("Process finished, total duration: {} seconds...", duration);

        return String.format("This operation took %s seconds to finish", duration);
    }

    public String insertOrUpdateBulkInsertAll() {

        List<Example> seededExamples = listAll();

        logger.info("Started save bulk metrics....");

        List<Example> examples = new ArrayList<>();

        logger.info("Generating examples array with 10000 examples....");

        for (int i = 0; i < 10000; i++) {

            examples.add(
                    new Example(
                            faker.name().fullName(),
                            faker.internet().emailAddress(),
                            faker.phoneNumber().phoneNumber()
                    )
            );
        }

        logger.info("Finished generating array....");

        logger.info("Addind seeded examples to new examples list..");
        examples.addAll(seededExamples);

        long startTime = System.currentTimeMillis();

        logger.info("Start saving in bulk....");

        exampleRepository.insertOrUpdateBulkInsertAll(examples);

        long endTime = System.currentTimeMillis();

        long duration = TimeUtils.calcDurationInSeconds(startTime, endTime);

        logger.info("Process finished, total duration: {} seconds...", duration);

        return String.format("This operation took %s seconds to finish", duration);
    }

    public String seedDb() {

        List<Example> examples = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {

            examples.add(
                    new Example(
                            faker.name().fullName(),
                            faker.internet().emailAddress(),
                            faker.phoneNumber().phoneNumber()
                    )
            );
        }

        exampleRepository.insertOrUpdateBulk(examples);

        return "Finished seeding db";
    }

    public List<Example> listAll() {
        List<Example> examplesList = exampleRepository.listAll();

        examplesList.forEach(e -> e.setName(faker.name().fullName()));

        return examplesList;
    }

    public Page<Example> listAll(Pageable pageable) {

        return exampleSpringRepository.findAll(pageable);
    }
}
