package vasyurin.work.bank_rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vasyurin.work.bank_rest.services.TransferService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public String transfer(
            @RequestParam Long fromCardId,
            @RequestParam Long toCardId,
            @RequestParam BigDecimal amount
    ) {
        transferService.transfer(fromCardId, toCardId, amount);
        return "Перевод выполнен успешно";
    }
}
