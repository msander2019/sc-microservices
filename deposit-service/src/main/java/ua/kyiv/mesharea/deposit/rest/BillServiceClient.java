package ua.kyiv.mesharea.deposit.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "bill-service")
public interface BillServiceClient {

    @RequestMapping(value = "bills/{billId}", method = RequestMethod.GET)
    BillResponceDTO getBillById(@PathVariable("billId") Long billId);

    @RequestMapping(value = "bills/{billId}", method = RequestMethod.PUT)
    void update(@PathVariable("billId") Long billId, BillRequestDTO billRequestDTO);

    @RequestMapping(value = "bills/account/{accountId}", method = RequestMethod.GET)
    List<BillResponceDTO> getBillsByAccountId(@PathVariable("accountId") Long accountId);
}
