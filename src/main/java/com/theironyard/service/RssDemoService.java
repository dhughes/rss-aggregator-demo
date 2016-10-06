package com.theironyard.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.theironyard.entity.RssEntry;
import com.theironyard.entity.RssFeed;
import com.theironyard.repository.RssEntryRepository;
import com.theironyard.repository.RssFeedRepository;
import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RssDemoService {

    @Autowired
    private RssEntryRepository rssEntryRepository;

    @Autowired
    private RssFeedRepository rssFeedRepository;

    private List<String> feedUrls = Arrays.asList(
            "http://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml",
            "http://feeds.reuters.com/Reuters/domesticNews",
            "http://hosted2.ap.org/atom/APDEFAULT/3d281c11a96b4ad082fe88aa0db04305"
    );

    public void loadFeeds(){
        //List<SyndEntry> entries = new ArrayList<>();

        // get all my feeds / entries
        for(String feedUrl : feedUrls) {
            try {
                SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(feedUrl)));

                URL url = new URL(feedUrl);

                String favicon = url.getProtocol() + "://" + url.getHost() + "/favicon.ico";

                // create our rss feed entity
                RssFeed rssFeed = new RssFeed(
                        feedUrl,
                        feed.getTitle(),
                        feed.getDescription(),
                        feed.getImage() != null ? feed.getImage().getUrl() : null,
                        favicon);

                // save the feed
                rssFeedRepository.save(rssFeed);

                for (SyndEntry entry : feed.getEntries()) {

                    // get the first image (if any)
                    List<Element> images = entry.getForeignMarkup().stream().filter(element -> element.getNamespacePrefix().equals("media") && element.getName().equals("content")).collect(Collectors.toList());
                    String imageUrl = null;
                    if (images.size() > 0) {
                        imageUrl = images.get(0).getAttributeValue("url");
                    }

                    // create my entry entity
                    RssEntry rssEntry = new RssEntry(
                            entry.getUri(),
                            entry.getTitle(),
                            imageUrl,
                            entry.getDescription().getValue(),
                            entry.getAuthor(),
                            entry.getPublishedDate(),
                            entry.getLink(),
                            rssFeed
                    );

                    // save this entry to the DB
                    rssEntryRepository.save(rssEntry);
                }

            } catch (FeedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Page<RssEntry> getPageOfEntries(Pageable pageable){
        return rssEntryRepository.findAll(pageable);
    }
}
