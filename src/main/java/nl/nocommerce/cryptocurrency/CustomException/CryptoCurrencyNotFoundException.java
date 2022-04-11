package nl.nocommerce.cryptocurrency.CustomException;

public class CryptoCurrencyNotFoundException extends RuntimeException {

    public CryptoCurrencyNotFoundException(Long id) {
        super("Could not find cryptocurrency " + id);
    }
}