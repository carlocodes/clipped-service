package com.carlocodes.clipped.controllers;

import com.carlocodes.clipped.dtos.ClipDto;
import com.carlocodes.clipped.exceptions.ClippedException;
import com.carlocodes.clipped.services.ClipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/get-clips/user/{id}")
    public ResponseEntity<List<ClipDto>> getClips(@PathVariable long id) throws ClippedException {
        return ResponseEntity.ok(clipService.getClips(id));
    }

    // TODO: Maybe enhance endpoint by taking in user/{id}
    // And check user's likes/watch time on certain clips/games
    // To enhance this endpoints algorithm and usability
    @GetMapping("/for-you/user/{id}")
    public ResponseEntity<List<ClipDto>> forYou(@PathVariable long id) throws ClippedException {
        return ResponseEntity.ok(clipService.forYou(id));
    }
}
