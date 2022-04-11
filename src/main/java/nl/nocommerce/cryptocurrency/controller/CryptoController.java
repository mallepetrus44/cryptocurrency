package nl.nocommerce.cryptocurrency.controller;

import nl.nocommerce.cryptocurrency.CustomException.CryptoCurrencyNotFoundException;
import nl.nocommerce.cryptocurrency.entity.Crypto;
import nl.nocommerce.cryptocurrency.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CryptoController {

    @Autowired
    private CryptoRepository repository;

    public void CryptoCurrencyController(CryptoRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/currencies")
    List<Crypto> all() {
        return repository.findAll();
    }

    @GetMapping("/currencies/{id}")
    Crypto get(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CryptoCurrencyNotFoundException(id));
    }

    @PostMapping("/currencies")
    Crypto add(@RequestBody Crypto newCrypto ) {
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
                    return repository.save(cryptoCurrency);
                })
                .orElseGet(()-> {
                    return repository.save(newCrypto);
                });
    }

    @DeleteMapping("/currencies/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}


