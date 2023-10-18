package ua.kyiv.mesharea.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.kyiv.mesharea.bill.controller.dto.BillRequestDTO;
import ua.kyiv.mesharea.bill.controller.dto.BillResponceDTO;
import ua.kyiv.mesharea.bill.service.BillService;

@Controller
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{billId}")
    public BillResponceDTO getBill(@PathVariable Long billId) {
        return new BillResponceDTO(billService.getBillById(billId));
    }

    @PostMapping("/")
    public Long createBill(@RequestBody BillRequestDTO billRequestDTO) {
        return billService.createBill(billRequestDTO.getAccountId(), billRequestDTO.getAmount(),
                billRequestDTO.getIsDefault(), billRequestDTO.getOverdraftEnabled());
    }

    @PutMapping("/{billId}")
    public BillResponceDTO updateBill(@PathVariable Long billId,
                                      @RequestBody BillRequestDTO billRequestDTO) {
        return new BillResponceDTO(billService.updateBill(billId,
                billRequestDTO.getAccountId(),
                billRequestDTO.getAmount(),
                billRequestDTO.getIsDefault(),
                billRequestDTO.getOverdraftEnabled()));
    }

    @DeleteMapping("/{billId}")
    public BillResponceDTO deleteBill(@PathVariable Long billId) {
        return new BillResponceDTO(billService.deleteBill(billId));
    }
}
