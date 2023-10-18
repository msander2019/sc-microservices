package ua.kyiv.mesharea.bill.repository;

import org.springframework.data.repository.CrudRepository;
import ua.kyiv.mesharea.bill.entity.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {
}
