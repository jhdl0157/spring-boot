package com.ll.exam.sbb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {


    @Transactional
    @Modifying
    @Query(
            value = "truncate answer",
            nativeQuery = true
    )
    void truncateMyTable();

    @Transactional
    @Modifying
    @Query(
            value = "SET FOREIGN_KEY_CHECKS = 0",
            nativeQuery = true
    )
    void disableForeignKeyCheck();

    @Transactional
    @Modifying
    @Query(
            value = "SET FOREIGN_KEY_CHECKS = 1",
            nativeQuery = true
    )
    void enableForeignKeyCheck();
}
