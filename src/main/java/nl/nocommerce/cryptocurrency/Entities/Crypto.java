package nl.nocommerce.cryptocurrency.Entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "cryptos")
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "name")
    private String name;

    @Column(name = "marketCap")
    private BigDecimal marketCap;

    @Column(name = "numberOfCoins")
    private Long numberOfCoins;

    protected Crypto(){}

    private Crypto(Builder builder) {
        this.ticker = builder.ticker;
        this.name = builder.name;
        this.marketCap =  builder.marketCap;
        this.numberOfCoins = builder.numberOfCoins;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public String getTicker() {
//        return ticker;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public BigDecimal getMarketCap() {
//        return marketCap;
//    }
//
//    public Long getNumberOfCoins() {
//        return numberOfCoins;
//    }


    public static class Builder {

        private String ticker;
        private String name;
        private BigDecimal marketCap;
        private Long numberOfCoins;

        public Builder(String ticker, String name) {
            this.ticker = ticker;
            this.name = name;
        }

        public Builder marketCap(BigDecimal marketCap) {
            this.marketCap = marketCap;
            return this;
        }

        public Builder numberOfCoins(Long numberOfCoins) {
            this.numberOfCoins = numberOfCoins;
            return this;
        }

        public Crypto build() {
            Crypto crypto = new Crypto(this);
//            validateCryptoCurrencyObject((crypto));
            return crypto;
        }

        private void validateCryptoCurrencyObject(Crypto crypto) {
            /* TODO: add validation */
        }
    }
}