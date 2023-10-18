package ua.kyiv.mesharea.deposit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.kyiv.mesharea.deposit.controller.dto.DepositRequestDTO;
import ua.kyiv.mesharea.deposit.controller.dto.DepositResponseDTO;
import ua.kyiv.mesharea.deposit.entity.Deposit;
import ua.kyiv.mesharea.deposit.service.DepositService;

@RestController
public class DepositController {

    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/deposits")
    public DepositResponseDTO deposit(@RequestBody DepositRequestDTO depositRequestDTO) {
        return depositService.deposit(depositRequestDTO.getAccountId(),
                depositRequestDTO.getBillId(), depositRequestDTO.getAmount());
    }
}
