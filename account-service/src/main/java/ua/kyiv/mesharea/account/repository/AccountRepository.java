package ua.kyiv.mesharea.account.repository;

import org.springframework.data.repository.CrudRepository;
import ua.kyiv.mesharea.account.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
