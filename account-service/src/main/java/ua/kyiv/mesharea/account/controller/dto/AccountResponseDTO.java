package ua.kyiv.mesharea.account.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ua.kyiv.mesharea.account.entity.Account;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class AccountResponseDTO {

    private Long accountId;

    private String name;

    private String email;

    private List<Long> bills;

    private String phone;

    private OffsetDateTime creationDate;

    public AccountResponseDTO(Account account) {
        accountId = account.getAccountId();
        name = account.getName();
        email = account.getEmail();
        bills = account.getBills();
        phone = account.getPhone();
        creationDate = account.getCreationDate();
    }
}
