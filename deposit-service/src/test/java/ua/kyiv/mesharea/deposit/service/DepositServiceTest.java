package ua.kyiv.mesharea.deposit.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import ua.kyiv.mesharea.deposit.controller.dto.DepositResponseDTO;
import ua.kyiv.mesharea.deposit.exception.DepositServiceException;
import ua.kyiv.mesharea.deposit.repository.DepositRepository;
import ua.kyiv.mesharea.deposit.rest.AccountResponseDTO;
import ua.kyiv.mesharea.deposit.rest.AccountServiceClient;
import ua.kyiv.mesharea.deposit.rest.BillResponceDTO;
import ua.kyiv.mesharea.deposit.rest.BillServiceClient;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class DepositServiceTest {

    @Mock
    private DepositRepository depositRepository;

    @Mock
    private AccountServiceClient accountServiceClient;

    @Mock
    private BillServiceClient billServiceClient;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private DepositService depositService;

    @Test
    public void  depositServiceTest_withBillId() {
        BillResponceDTO billResponseDTO = createBillResponseDTO();
        Mockito.when(billServiceClient.getBillById(ArgumentMatchers.anyLong())).thenReturn(billResponseDTO);
        Mockito.when(accountServiceClient.getAccountById(ArgumentMatchers.anyLong())).thenReturn(createAccountResponseDTO());
        DepositResponseDTO deposit = depositService.deposit(null, 1L, BigDecimal.valueOf(1000));
        Assertions.assertThat(deposit.getMail()).isEqualTo("alex@gmail.com");
    }

    @Test(expected = DepositServiceException.class)
    public void depositServiceTest_exception() {
        depositService.deposit(null, null, BigDecimal.valueOf(1000));
    }

    private AccountResponseDTO createAccountResponseDTO() {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setAccountId(1L);
        accountResponseDTO.setBills(Arrays.asList(1L, 2L, 3L));
        accountResponseDTO.setCreationDate(OffsetDateTime.now());
        accountResponseDTO.setEmail("alex@gmail.com");
        accountResponseDTO.setName("Alex");
        accountResponseDTO.setPhone("+380965236585");
        return accountResponseDTO;
    }

    private BillResponceDTO createBillResponseDTO() {
        BillResponceDTO billResponceDTO = new BillResponceDTO();
        billResponceDTO.setAccountId(1L);
        billResponceDTO.setAmount(BigDecimal.valueOf(1000));
        billResponceDTO.setBillId(1L);
        billResponceDTO.setCreationDate(OffsetDateTime.now());
        billResponceDTO.setIsDefault(true);
        billResponceDTO.setOverdraftEnabled(true);
        return billResponceDTO;
    }
}
