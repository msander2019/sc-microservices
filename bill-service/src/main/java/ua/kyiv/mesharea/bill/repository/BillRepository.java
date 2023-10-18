package ua.kyiv.mesharea.bill.repository;

import org.springframework.data.repository.CrudRepository;
import ua.kyiv.mesharea.bill.entity.Bill;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill, Long> {

    List<Bill> getBillsByAccountId(Long accountId);
}
