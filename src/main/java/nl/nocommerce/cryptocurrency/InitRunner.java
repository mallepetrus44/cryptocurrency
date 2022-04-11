package nl.nocommerce.cryptocurrency;

import nl.nocommerce.cryptocurrency.Entities.Crypto;
import nl.nocommerce.cryptocurrency.Repositories.CryptoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitRunner.class);

    @Autowired
    private CryptoRepository repository;

    @Override
    public void run(String... args) {

            repository.save(new Crypto.Builder("BTC", "Bitcoin").marketCap(BigDecimal.valueOf(189580000000L)).numberOfCoins(16770000L).build());
            repository.save(new Crypto.Builder("ETH", "Ethereum").marketCap(BigDecimal.valueOf(69280000000L)).numberOfCoins(96710000L).build());
            repository.save(new Crypto.Builder("XRP", "Ripple").marketCap(BigDecimal.valueOf(64750000000L)).numberOfCoins(38590000000L).build());
            repository.save(new Crypto.Builder("BCH", "BitcoinCash").marketCap(BigDecimal.valueOf(69020000000L)).numberOfCoins(16670000L).build());

            logger.info("# of cryptos: {}", repository.count());

    }
}
