package maciejpaszylka.currencyexchange.repository;

import maciejpaszylka.currencyexchange.model.CurrencyRate;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, Long> {
    List<CurrencyRate> findAll();
    CurrencyRate findCurrencyRateByDate(LocalDate date);
}
