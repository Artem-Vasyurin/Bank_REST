package vasyurin.work.bank_rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardRequestDto {

    @NotNull(message = "ID владельца обязателен")
    private Long ownerId;

    @NotNull(message = "Номер карты обязателен")
    private String number;

    @NotNull(message = "Срок действия обязателен")
    private String expiryDate;

    @NotNull(message = "Статус обязателен")
    private String status;

    @NotNull(message = "Баланс обязателен")
    private BigDecimal balance;
}
