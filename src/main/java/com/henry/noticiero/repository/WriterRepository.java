package com.henry.noticiero.repository;

import com.henry.noticiero.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Integer> {
}
