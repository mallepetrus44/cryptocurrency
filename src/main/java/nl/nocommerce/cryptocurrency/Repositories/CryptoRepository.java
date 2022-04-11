package nl.nocommerce.cryptocurrency.Repositories;

import nl.nocommerce.cryptocurrency.Entities.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
}