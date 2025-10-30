package vasyurin.work.bank_rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
