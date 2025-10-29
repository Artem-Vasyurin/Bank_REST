package vasyurin.work.bank_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;

    private String number;

    private Long ownerId;

    private String expirationDate;

    private String status;

    private BigDecimal balance;

    private Timestamp created;

    private Timestamp updated;
}
