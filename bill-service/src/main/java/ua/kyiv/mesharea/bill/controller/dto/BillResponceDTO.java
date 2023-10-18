package ua.kyiv.mesharea.bill.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ua.kyiv.mesharea.bill.entity.Bill;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class BillResponceDTO {

    private Long billId;

    private Long accountId;

    private BigDecimal amount;

    private Boolean isDefault;

    private OffsetDateTime creationDate;

    private Boolean overdraftEnabled;

    public BillResponceDTO(Bill bill) {
        billId = bill.getBillId();
        accountId = bill.getAccountId();
        amount = bill.getAmount();
        isDefault = bill.getIsDefault();
        creationDate = bill.getCreationDate();
        overdraftEnabled = bill.getOverdraftEnabled();
    }
}
