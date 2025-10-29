package vasyurin.work.bank_rest.services;

import static vasyurin.work.bank_rest.util.CardUtils.maskCardNumber;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vasyurin.work.bank_rest.entities.Card;
import vasyurin.work.bank_rest.dto.CardDto;
import vasyurin.work.bank_rest.dto.CardRequestDto;
import vasyurin.work.bank_rest.entities.CardStatus;
import vasyurin.work.bank_rest.repositories.CardRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional(readOnly = true)
    public List<CardDto> getAllCards() {
        return cardRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CardDto> getCardsByOwnerId(Long ownerId) {
        return cardRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CardDto getCardById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with id " + id));
        return toDto(card);
    }

    @Transactional
    public CardDto createCard(CardRequestDto dto) {
        Card card = new Card();
        card.setOwnerId(dto.getOwnerId());
        card.setNumber(dto.getNumber());
        card.setExpiryDate(dto.getExpiryDate());
        card.setStatus(CardStatus.valueOf(dto.getStatus()));
        card.setBalance(dto.getBalance());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        card.setCreated(now);
        card.setUpdated(now);

        Card saved = cardRepository.save(card);
        return toDto(saved);
    }

    @Transactional
    public CardDto updateCard(Long id, CardRequestDto dto) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with id " + id));

        card.setOwnerId(dto.getOwnerId());
        card.setNumber(dto.getNumber());
        card.setExpiryDate(dto.getExpiryDate());
        card.setStatus(CardStatus.valueOf(dto.getStatus()));
        card.setBalance(dto.getBalance());
        card.setUpdated(new Timestamp(System.currentTimeMillis()));

        Card updated = cardRepository.save(card);
        return toDto(updated);
    }

    @Transactional
    public void deleteCard(Long id) {
        if (!cardRepository.existsById(id)) {
            throw new RuntimeException("Card not found with id " + id);
        }
        cardRepository.deleteById(id);
    }

    @Transactional
    public CardDto blockCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Карта не найдена"));
        card.setStatus(CardStatus.BLOCKED);
        card.setUpdated(new Timestamp(System.currentTimeMillis()));
        return toDto(cardRepository.save(card));
    }

    @Transactional
    public CardDto activateCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Карта не найдена"));
        card.setStatus(CardStatus.ACTIVE);
        card.setUpdated(new Timestamp(System.currentTimeMillis()));
        return toDto(cardRepository.save(card));
    }

    @Transactional
    public void requestBlockCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Карта не найдена"));

        if (card.getStatus() == CardStatus.BLOCKED) {
            throw new RuntimeException("Карта уже заблокирована");
        }

        if (card.getStatus() == CardStatus.PENDING_BLOCK) {
            throw new RuntimeException("Запрос на блокировку уже отправлен");
        }

        card.setStatus(CardStatus.PENDING_BLOCK);
        card.setUpdated(new Timestamp(System.currentTimeMillis()));
        cardRepository.save(card);

    }

    private CardDto toDto(Card card) {
        CardDto dto = new CardDto();
        dto.setId(card.getId());
        dto.setOwnerId(card.getOwnerId());
        dto.setNumber(maskCardNumber(card.getNumber()));
        dto.setExpirationDate((card.getExpiryDate()));
        dto.setStatus(String.valueOf(card.getStatus()));
        dto.setBalance(card.getBalance());
        dto.setCreated(card.getCreated());
        dto.setUpdated(card.getUpdated());
        return dto;
    }

}
