package ua.kyiv.mesharea.deposit.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// клиент который посылает запросы
@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
    AccountResponseDTO getAccountById(@PathVariable("accountId") Long accountId);
}
