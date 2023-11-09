package com.carlocodes.clipped.controllers;

import com.carlocodes.clipped.dtos.ClipDto;
import com.carlocodes.clipped.exceptions.ClippedException;
import com.carlocodes.clipped.services.ClipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clip")
public class ClipController {
    private final ClipService clipService;

    public ClipController(ClipService clipService) {
        this.clipService = clipService;
    }

    @PostMapping("/create-clip")
    public ResponseEntity<ClipDto> createClip(@RequestBody ClipDto clipDto) throws ClippedException {
        return ResponseEntity.ok(clipService.createClip(clipDto));
    }

    @PutMapping("/edit-clip")
    public ResponseEntity<ClipDto> editClip(@RequestBody ClipDto clipDto) throws ClippedException {
        return ResponseEntity.ok(clipService.editClip(clipDto));
    }

    @DeleteMapping("/delete-clip")
    public ResponseEntity<String> deleteClip(@RequestBody ClipDto clipDto) throws ClippedException {
        clipService.deleteClip(clipDto);
        return ResponseEntity.ok("Clip deleted!");
    }
}
