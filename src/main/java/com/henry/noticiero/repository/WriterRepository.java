package com.henry.noticiero.repository;

import com.henry.noticiero.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Integer> {
}
