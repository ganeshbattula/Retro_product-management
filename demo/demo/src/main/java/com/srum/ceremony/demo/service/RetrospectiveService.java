package com.srum.ceremony.demo.service;

import com.srum.ceremony.demo.exception.InvalidFeedbackIdException;
import com.srum.ceremony.demo.exception.RetrospectiveNotFoundException;
import com.srum.ceremony.demo.model.FeedbackItemModel;
import com.srum.ceremony.demo.model.Retrospective;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RetrospectiveService {

    private final List<Retrospective> retrospectives = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(RetrospectiveService.class);
    
    public ResponseEntity<?> createRetrospective(Retrospective newRetrospective) {
        logger.info("Creating new retrospective: {}", newRetrospective.getName());

        for (Retrospective retro : retrospectives) {
            if (retro.getName().equals(newRetrospective.getName())) {
                logger.warn("Attempted to create a retrospective with an existing name: {}", newRetrospective.getName());
                return ResponseEntity.badRequest().body("Retrospective name already exists");
            }
        }

        retrospectives.add(newRetrospective);
        logger.info("Retrospective created successfully: {}", newRetrospective.getName());
        return ResponseEntity.ok(newRetrospective);
    }


    public ResponseEntity<?> addFeedback(String name, FeedbackItemModel feedbackItem) {
        logger.info("Attempting to add feedback to retrospective: {}", name);

        for (Retrospective retro : retrospectives) {
            if (retro.getName().equals(name)) {
                logger.info("Found retrospective. Adding feedback provided by: {}", feedbackItem.getName());
                retro.getFeedbackItems().add(feedbackItem);
                return ResponseEntity.ok(retro);
            }
        }

        logger.error("Retrospective not found with name: {}", name);
        throw new RetrospectiveNotFoundException("Retrospective not found with name: " + name);
    }

    public ResponseEntity<?> updateFeedback(String name, int feedbackId, FeedbackItemModel feedbackItem) {
        for (Retrospective retro : retrospectives) {
            if (retro.getName().equals(name)) {
                if (feedbackId < retro.getFeedbackItems().size()) {
                    logger.info("Updating feedback for retrospective: {}", name);
                    retro.getFeedbackItems().set(feedbackId, feedbackItem);
                    return ResponseEntity.ok(retro);
                } else {
                    logger.error("Invalid feedback ID: {} for retrospective: {}", feedbackId, name);
                    throw new InvalidFeedbackIdException("Invalid feedback ID: " + feedbackId);
                }
            }
        }
        logger.error("Retrospective not found: {}", name);
        throw new RetrospectiveNotFoundException("Retrospective not found: " + name);
    }

    public List<Retrospective> searchRetrospectives(int page, int size) {
        logger.info("Initiating search for retrospectives", page, size);

        try {
            List<Retrospective> foundRetrospectives = new ArrayList<>(retrospectives);
            List<Retrospective> updatedRetrospectives = new ArrayList<>();
            int start = page * size;
            int end = Math.min((page + 1) * size, foundRetrospectives.size());
            for (int i = start; i < end; i++) {
                updatedRetrospectives.add(foundRetrospectives.get(i));
            }
            logger.info("Retrospectives search completed. Found {} retrospectives.", updatedRetrospectives.size());
            return updatedRetrospectives;
        } catch (Exception e) {
            logger.error("Error occurred during the search for retrospectives: {}", e.getMessage());
            throw e; 
        }
    }

    public List<Retrospective> searchRetrospectivesByDate(String date) {
        logger.info("Searching for retrospectives on date: {}", date);
        List<Retrospective> filteredRetrospectives = new ArrayList<>();
        for (Retrospective retro : retrospectives) {
            if (retro.getDate().toString().equals(date)) {
                filteredRetrospectives.add(retro);
            }
        }
        return filteredRetrospectives;
    }

}
