package ua.kyiv.mesharea.deposit.repository;

import org.springframework.data.repository.CrudRepository;
import ua.kyiv.mesharea.deposit.entity.Deposit;

import java.util.List;

public interface DepositRepository extends CrudRepository<Deposit, Long> {
    List<Deposit> findDepositByEmail(String email);
}
