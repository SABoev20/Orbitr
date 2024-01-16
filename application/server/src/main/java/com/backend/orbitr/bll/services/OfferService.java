package com.backend.orbitr.bll.services;

import com.backend.orbitr.dal.models.Offer;
import com.backend.orbitr.dal.models.Transaction;
import com.backend.orbitr.dal.models.User;
import com.backend.orbitr.dal.repositories.OfferRepository;
import com.backend.orbitr.dal.repositories.SpaceshipRepository;
import com.backend.orbitr.dal.repositories.TransactionRepository;
import com.backend.orbitr.dal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    private final UserRepository userRepository;
    private final SpaceshipRepository spaceshipRepository;
    private final TransactionRepository transactionRepository;

    public OfferService(OfferRepository offerRepository, UserRepository userRepository, SpaceshipRepository spaceshipRepository, TransactionRepository transactionRepository){
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.spaceshipRepository = spaceshipRepository;
        this.transactionRepository = transactionRepository;
    }

    public Offer saveOffer(Offer offer){
        return offerRepository.save(offer);
    }

    public void purchaseOffer(User user, Offer offer){
        if(user.getCredits() < offer.getPrice()) return;

        user.setCredits(user.getCredits() - offer.getPrice());
        userRepository.save(user);

        offer.getUser().setCredits(offer.getUser().getCredits() - offer.getPrice());
        userRepository.save(offer.getUser());

        offer.getSpaceship().setUser(user);
        spaceshipRepository.save(offer.getSpaceship());

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Transaction transaction = new Transaction();
        transaction.setUserId(user.getID());
        transaction.setSpaceship(offer.getSpaceship());
        transaction.setTransactionType("Purchase");
        transaction.setDescription(user.getUsername() + "You bought \"" + offer.getSpaceship().getName() + "\" spaceship from " + offer.getUser().getUsername());
        transaction.setPrice(offer.getPrice());
        transaction.setTransactionDate(formattedDateTime);
        transactionRepository.save(transaction);
        Transaction transaction2 = new Transaction();
        transaction2.setUserId(offer.getUser().getID());
        transaction2.setSpaceship(offer.getSpaceship());
        transaction2.setTransactionType("Sale");
        transaction2.setDescription("You sold \"" + offer.getSpaceship().getName() + "\" spaceship to " + user.getUsername());
        transaction2.setPrice(offer.getPrice());
        transaction2.setTransactionDate(formattedDateTime);
        transactionRepository.save(transaction);

        offerRepository.delete(offer);
    }

    public List<Offer> getAvailableOffers(User user){
        return offerRepository.findAll();
    }
}
