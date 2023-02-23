package com.noritakakagei.sample;

import java.util.logging.Logger;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.noritakakagei.sample.entity.Member;
import com.noritakakagei.sample.repository.MemberCrudRepository;

/**
 * Spring Boot Launcher class
 */
@SpringBootApplication
public class SpringDataJdbcSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJdbcSampleApplication.class, args)
            .getBean(SpringDataJdbcSampleApplication.class)
            .execute();;
    }

    private static final Logger logger = Logger.getLogger(SpringDataJdbcSampleApplication.class.getName());
    
    /**
     * Dependency Injection
     */
    @Autowired
    MemberCrudRepository repo;

    private void execute() {
        executeInsert();
        executeSelect();
    }

    /**
     * Insert(Register) data to DB
     */
    private void executeInsert() {
        Member member = new Member(null, "Hoge");
        member = repo.save(member);
        logger.log(Level.INFO, String.format("Register Entity: %s", member));
    }

    /**
     * Select(Get) data list on DB
     */
    private void executeSelect() {
        logger.log(Level.INFO, "=== ALL registered entities ===");
        Iterable<Member> members = repo.findAll();
        for (Member member : members) {
            logger.log(Level.INFO, String.format("%s", member));
        }
    }
}