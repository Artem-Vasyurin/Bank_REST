package vasyurin.work.bank_rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vasyurin.work.bank_rest.entities.Card;
import vasyurin.work.bank_rest.repositories.CardRepository;
import vasyurin.work.bank_rest.util.CardUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final CardRepository cardRepository;

    @Transactional
    public void transfer(Long fromCardId, Long toCardId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Некорректная сумма перевода");
        }

        Card from = cardRepository.findById(fromCardId)
                .orElseThrow(() -> new RuntimeException("Карта списания не найдена"));
        Card to = cardRepository.findById(toCardId)
                .orElseThrow(() -> new RuntimeException("Карта зачисления не найдена"));

        if (!from.getOwnerId().equals(to.getOwnerId())) {
            throw new RuntimeException("Перевод возможен только между своими картами");
        }

        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Недостаточно средств на карте");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        Timestamp now = new Timestamp(System.currentTimeMillis());
        from.setUpdated(now);
        to.setUpdated(now);

        cardRepository.save(from);
        cardRepository.save(to);
    }
}
