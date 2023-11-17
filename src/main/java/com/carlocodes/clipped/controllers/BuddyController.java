package com.carlocodes.clipped.controllers;

import com.carlocodes.clipped.dtos.BuddyDto;
import com.carlocodes.clipped.dtos.BuddyRequestDto;
import com.carlocodes.clipped.exceptions.ClippedException;
import com.carlocodes.clipped.services.BuddyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/buddy")
public class BuddyController {
    private final BuddyService buddyService;

    public BuddyController(BuddyService buddyService) {
        this.buddyService = buddyService;
    }

    @PostMapping("/send-buddy-request")
    public ResponseEntity<String> sendBuddyRequest(@RequestBody BuddyRequestDto buddyRequestDto) throws ClippedException {
        buddyService.sendBuddyRequest(buddyRequestDto);
        return ResponseEntity.ok("Buddy request sent!");
    }

    @PutMapping("/accept-buddy-request")
    public ResponseEntity<String> acceptBuddyRequest(@RequestBody BuddyRequestDto buddyRequestDto) throws ClippedException {
        buddyService.acceptBuddyRequest(buddyRequestDto);
        return ResponseEntity.ok("Buddy request accepted!");
    }

    @PutMapping("/decline-buddy-request")
    public ResponseEntity<String> declineBuddyRequest(@RequestBody BuddyRequestDto buddyRequestDto) throws ClippedException {
        buddyService.declineBuddyRequest(buddyRequestDto);
        return ResponseEntity.ok("Buddy request declined!");
    }

    @DeleteMapping("/remove-buddy")
    public ResponseEntity<String> removeBuddy(@RequestBody BuddyRequestDto buddyRequestDto) throws ClippedException {
        buddyService.removeBuddy(buddyRequestDto);
        return ResponseEntity.ok("Buddy removed!");
    }

    @GetMapping("/get-pending-buddy-requests/user/{id}")
    public ResponseEntity<Set<BuddyDto>> getPendingBuddyRequests(@PathVariable long id) throws ClippedException {
        return ResponseEntity.ok(buddyService.getPendingBuddyRequests(id));
    }
}
