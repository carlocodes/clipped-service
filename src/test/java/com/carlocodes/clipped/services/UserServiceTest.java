package com.carlocodes.clipped.services;

import com.carlocodes.clipped.entities.User;
import com.carlocodes.clipped.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void findByIdTest() {
        // TODO: Add games and clips
        long id = 1L;
        User user = new User();
        user.setId(id);
        user.setUsername("Gian");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> optionalResult = userService.findById(id);

        assertTrue(optionalResult.isPresent());

        User result = optionalResult.get();

        assertEquals(user, result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void saveTest() {
        // TODO: Add games and clips
        // TODO: Maybe refactor entities to take in a constructor of some sort
        User user = new User();
        user.setId(1L);
        user.setUsername("Gian");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertEquals(user, result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());

        verify(userRepository, times(1)).save(user);
    }
}
