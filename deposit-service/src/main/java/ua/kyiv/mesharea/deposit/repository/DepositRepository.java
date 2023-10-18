package ua.kyiv.mesharea.deposit.repository;

import org.springframework.data.repository.CrudRepository;
import ua.kyiv.mesharea.deposit.entity.Deposit;

public interface DepositRepository extends CrudRepository<Deposit, Long> {
}
