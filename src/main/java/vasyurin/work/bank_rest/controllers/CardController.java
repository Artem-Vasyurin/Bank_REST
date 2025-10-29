package vasyurin.work.bank_rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vasyurin.work.bank_rest.dto.CardDto;
import vasyurin.work.bank_rest.dto.CardRequestDto;
import vasyurin.work.bank_rest.services.CardService;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @GetMapping("/user/{ownerId}")
    public ResponseEntity<List<CardDto>> getCardsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(cardService.getCardsByOwnerId(ownerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody CardRequestDto dto) {
        return ResponseEntity.ok(cardService.createCard(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardDto> updateCard(@PathVariable Long id, @RequestBody CardRequestDto dto) {
        return ResponseEntity.ok(cardService.updateCard(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/block")
    public ResponseEntity<CardDto> blockCard(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.blockCard(id));
    }

    @PostMapping("/{id}/request-block")
    public ResponseEntity<String> requestBlockCard(@PathVariable Long id) {
        cardService.requestBlockCard(id);
        return ResponseEntity.ok("Запрос на блокировку карты отправлен");
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<CardDto> activateCard(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.activateCard(id));
    }
}
