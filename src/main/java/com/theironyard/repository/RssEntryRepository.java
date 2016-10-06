package com.theironyard.repository;

import com.theironyard.entity.RssEntry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RssEntryRepository extends JpaRepository<RssEntry, String> {
}
