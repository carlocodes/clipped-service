package com.carlocodes.clipped.services;

import com.carlocodes.clipped.dtos.BuddyRequestDto;
import com.carlocodes.clipped.entities.Buddy;
import com.carlocodes.clipped.entities.User;
import com.carlocodes.clipped.exceptions.ClippedException;
import com.carlocodes.clipped.repositories.BuddyRepository;
import org.springframework.stereotype.Service;

@Service
public class BuddyService {
    private final BuddyRepository buddyRepository;
    private final UserService userService;

    public BuddyService(BuddyRepository buddyRepository,
                        UserService userService) {
        this.buddyRepository = buddyRepository;
        this.userService = userService;
    }

    public void sendBuddyRequest(BuddyRequestDto buddyRequestDto) throws ClippedException {
        try {
            long senderId = buddyRequestDto.getSenderId();
            long receiverId = buddyRequestDto.getReceiverId();

            User sender = userService.findById(senderId).orElseThrow(() ->
                    new ClippedException(String.format("Sender id: %d does not exist!", senderId)));
            User receiver = userService.findById(receiverId).orElseThrow(() ->
                    new ClippedException(String.format("Receiver id: %d does not exist!", receiverId)));

            if (buddyRepository.existsBySenderAndReceiverAndAcceptedIsTrue(sender, receiver) ||
                    buddyRepository.existsBySenderAndReceiverAndAcceptedIsTrue(receiver, sender))
                throw new ClippedException("Already buddies!");

            if (buddyRepository.existsBySenderAndReceiverAndAcceptedIsNull(sender, receiver) ||
                    buddyRepository.existsBySenderAndReceiverAndAcceptedIsNull(receiver, sender))
                throw new ClippedException("Buddy request has already been sent!");

            saveBuddyRequest(sender, receiver);
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Send buddy request from sender with id: %d to receiver with id: %d failed due to %s",
                    buddyRequestDto.getSenderId(), buddyRequestDto.getReceiverId(), e.getMessage()), e);
        }
    }

    public void acceptBuddyRequest(BuddyRequestDto buddyRequestDto) throws ClippedException {
        try {
            long senderId = buddyRequestDto.getSenderId();
            long receiverId = buddyRequestDto.getReceiverId();

            User sender = userService.findById(senderId).orElseThrow(() ->
                    new ClippedException(String.format("Sender id: %d does not exist!", senderId)));
            User receiver = userService.findById(receiverId).orElseThrow(() ->
                    new ClippedException(String.format("Receiver id: %d does not exist!", receiverId)));


            if (buddyRepository.existsBySenderAndReceiverAndAcceptedIsTrue(sender, receiver) ||
                    buddyRepository.existsBySenderAndReceiverAndAcceptedIsTrue(receiver, sender))
                throw new ClippedException("Already buddies!");

            Buddy buddy = buddyRepository.findBySenderAndReceiverAndAcceptedIsNull(sender, receiver).orElseThrow(() ->
                    new ClippedException("Buddy request does not exist!"));

            buddy.setAccepted(true);
            buddyRepository.save(buddy);
        } catch (ClippedException e) {
            throw new ClippedException(String.format("Accept buddy request from sender with id: %d to receiver with id: %d failed due to %s",
                    buddyRequestDto.getSenderId(), buddyRequestDto.getReceiverId(), e.getMessage()), e);
        }
    }

    private void saveBuddyRequest(User sender, User receiver) {
        Buddy buddy = new Buddy();

        buddy.setSender(sender);
        buddy.setReceiver(receiver);

        buddyRepository.save(buddy);
    }
}
