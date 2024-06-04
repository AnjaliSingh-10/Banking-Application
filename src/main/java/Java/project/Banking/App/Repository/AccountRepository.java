package Java.project.Banking.App.Repository;

import Java.project.Banking.App.Entity.Account;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository< Account,Long> {
}
