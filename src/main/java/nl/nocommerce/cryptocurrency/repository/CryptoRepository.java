package nl.nocommerce.cryptocurrency.repository;

import nl.nocommerce.cryptocurrency.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
}