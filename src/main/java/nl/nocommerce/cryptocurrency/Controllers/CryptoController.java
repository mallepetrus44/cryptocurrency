package nl.nocommerce.cryptocurrency.Controllers;

import nl.nocommerce.cryptocurrency.CustomException.CryptoCurrencyNotFoundException;
import nl.nocommerce.cryptocurrency.Entities.Crypto;
import nl.nocommerce.cryptocurrency.Repositories.CryptoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CryptoController {

    private static final Logger logger = LoggerFactory.getLogger(CryptoController.class);

    /* TODO controller communicates directly with repo, add service-layer for logic */

    private final CryptoRepository repository;

    public CryptoController(CryptoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/currencies")
    List<Crypto> all() {
        logger.info("Getting all cryptos");
            return repository.findAll();
    }

    @GetMapping("/currencies/{id}")
    Crypto get(@PathVariable Long id) {
        logger.info("Getting crypto with id: "+ id);
        return repository.findById(id)
                .orElseThrow(() -> new CryptoCurrencyNotFoundException(id));
    }

    @PostMapping("/currencies")
    Crypto add(@RequestBody Crypto newCrypto ) {
        logger.info("saving crypto:"+ newCrypto.getName());
        return repository.save(newCrypto);
    }

    @PutMapping("/currencies/{id}")
    Crypto edit(@RequestBody Crypto newCrypto, @PathVariable Long id) {
        return repository.findById(id)
                .map(cryptoCurrency -> {
                    cryptoCurrency.setName(newCrypto.getName());
                    cryptoCurrency.setTicker(newCrypto.getTicker());
                    cryptoCurrency.setNumberOfCoins(newCrypto.getNumberOfCoins());
                    cryptoCurrency.setMarketCap(newCrypto.getMarketCap());
                    logger.info("Updating crypto with id: "+ id);
                    return repository.save(cryptoCurrency);
                })
                .orElseGet(()-> {
                    logger.info("Adding crypto with id: "+ id);
                    return repository.save(newCrypto);
                });
    }

    @DeleteMapping("/currencies/{id}")
    void delete(@PathVariable Long id) {
        logger.info("Deleting crypto with id: "+id );
        repository.deleteById(id);
    }
}


