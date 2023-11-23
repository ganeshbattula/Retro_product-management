package com.srum.ceremony.demo.controller;

import com.srum.ceremony.demo.model.FeedbackItemModel;
import com.srum.ceremony.demo.model.Retrospective;
import com.srum.ceremony.demo.service.RetrospectiveService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/retrospectives")
public class RetrospectiveController {

    @Autowired
    private RetrospectiveService retrospectiveService;
    private static final Logger logger = LoggerFactory.getLogger(RetrospectiveController.class);

    @PostMapping
    public ResponseEntity<?> createRetrospective(@RequestBody Retrospective retrospective) {
        logger.info("Received request to create a new retrospective: {}", retrospective.getName());
        ResponseEntity<?> response = retrospectiveService.createRetrospective(retrospective);
        logger.info("Response for creating retrospective {}: {}", retrospective.getName(), response.getStatusCode());
        return response;
    }

    @PostMapping("/{name}/feedback")
    public ResponseEntity<?> addFeedback(@PathVariable String name, @RequestBody FeedbackItemModel feedbackItem) {
        logger.info("Received request to add feedback to retrospective: {}", name);
        ResponseEntity<?> response = retrospectiveService.addFeedback(name, feedbackItem);
        logger.info("Response for adding feedback to retrospective {}: {}", name, response.getStatusCode());
        return response;
    }

    @PutMapping("/{name}/feedback/{feedbackId}")
    public ResponseEntity<?> updateFeedback(@PathVariable String name, @PathVariable int feedbackId, @RequestBody FeedbackItemModel feedbackItem) {
        logger.info("Received request to update feedback for retrospective: {}, Feedback ID: {}", name, feedbackId);
        ResponseEntity<?> response = retrospectiveService.updateFeedback(name, feedbackId, feedbackItem);
        logger.info("Response for updating feedback for retrospective {}: {}, Feedback ID: {}", name, feedbackId, response.getStatusCode());
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Retrospective>> searchRetrospectives(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("Received request to search retrospectives", page, size);
        List<Retrospective> retrospectives = retrospectiveService.searchRetrospectives(page, size);
        logger.info("Found {} retrospectives", retrospectives.size());
        return ResponseEntity.ok(retrospectives);
    }
    
    @GetMapping("/searchByDate")
    public ResponseEntity<List<Retrospective>> searchRetrospectivesByDate(
            @RequestParam String date) {
        logger.info("Received request to search retrospectives by date: {}", date);
        List<Retrospective> retrospectives = retrospectiveService.searchRetrospectivesByDate(date);
        logger.info("Found {} retrospectives on date {}", retrospectives.size(), date);
        return ResponseEntity.ok(retrospectives);
    }

}
