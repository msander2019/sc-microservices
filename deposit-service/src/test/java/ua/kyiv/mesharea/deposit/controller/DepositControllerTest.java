package ua.kyiv.mesharea.deposit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.kyiv.mesharea.deposit.DepositApplication;
import ua.kyiv.mesharea.deposit.config.SpringH2Config;
import ua.kyiv.mesharea.deposit.controller.dto.DepositResponseDTO;
import ua.kyiv.mesharea.deposit.entity.Deposit;
import ua.kyiv.mesharea.deposit.repository.DepositRepository;
import ua.kyiv.mesharea.deposit.rest.AccountResponseDTO;
import ua.kyiv.mesharea.deposit.rest.AccountServiceClient;
import ua.kyiv.mesharea.deposit.rest.BillResponceDTO;
import ua.kyiv.mesharea.deposit.rest.BillServiceClient;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DepositApplication.class, SpringH2Config.class})
public class DepositControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DepositRepository depositRepository;

    @MockBean
    private BillServiceClient billServiceClient;

    @MockBean
    private AccountServiceClient accountServiceClient;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private static final String REQUEST = "{\n" +
            "    \"billId\": 1,\n" +
            "    \"amount\": 3000\n" +
            "}";

    @Test
    public void createDeposit() throws Exception {
        BillResponceDTO billResponseDTO = createBillResponseDTO();
        Mockito.when(billServiceClient.getBillById(ArgumentMatchers.anyLong())).thenReturn(billResponseDTO);
        Mockito.when(accountServiceClient.getAccountById(ArgumentMatchers.anyLong())).thenReturn(createAccountResponseDTO());
        MvcResult mvcResult = mockMvc.perform(post("/deposits")
                .content(REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        final List<Deposit> deposits = depositRepository.findDepositByEmail("alex@gmail.com");
        ObjectMapper objectMapper = new ObjectMapper();
        DepositResponseDTO depositResponseDTO = objectMapper.readValue(body, DepositResponseDTO.class);

        Assertions.assertThat(depositResponseDTO.getMail()).isEqualTo(deposits.get(0).getEmail());
        Assertions.assertThat(depositResponseDTO.getAmount()).isEqualTo(BigDecimal.valueOf(3000));
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
