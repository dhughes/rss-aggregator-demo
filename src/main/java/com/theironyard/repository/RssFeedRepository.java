package com.theironyard.repository;

import com.theironyard.entity.RssEntry;
import com.theironyard.entity.RssFeed;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RssFeedRepository extends JpaRepository<RssFeed, String> {
}
