package maciejpaszylka.currencyexchange.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import maciejpaszylka.currencyexchange.model.Rate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRateDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Rate rates;
    private double rate;
    private Date date;
    @CreationTimestamp
    private Timestamp searchOperationTime;
}
