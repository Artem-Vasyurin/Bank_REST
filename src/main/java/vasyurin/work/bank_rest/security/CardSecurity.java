package vasyurin.work.bank_rest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vasyurin.work.bank_rest.services.CardService;
import vasyurin.work.bank_rest.services.UserService;

@Component("cardSecurity")
@RequiredArgsConstructor
public class CardSecurity {

    private final CardService cardService;
    private final UserService userService;

    public boolean isCardOwner(String username, Long cardId) {
        var user = userService.findByUsername(username);
        if (user == null) return false;
        return cardService.getCardsByOwnerId(user.getId())
                .stream()
                .anyMatch(card -> card.getId().equals(cardId));
    }

    public boolean isOwner(String username, Long ownerId) {
        var user = userService.findByUsername(username);
        return user != null && user.getId().equals(ownerId);
    }
}
