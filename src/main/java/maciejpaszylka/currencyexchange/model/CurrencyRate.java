package maciejpaszylka.currencyexchange.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double rate;
    private Date date;
    @CreationTimestamp
    private Timestamp searchOperationTime;
}
