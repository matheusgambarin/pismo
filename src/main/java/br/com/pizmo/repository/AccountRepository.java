package br.com.pizmo.repository;

import br.com.pizmo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByDocumentNumber(Long documentNumber);
}
