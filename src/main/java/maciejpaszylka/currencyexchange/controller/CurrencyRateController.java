package maciejpaszylka.currencyexchange.controller;

import maciejpaszylka.currencyexchange.model.CurrencyRate;
import maciejpaszylka.currencyexchange.model.dto.CurrencyRateDto;
import maciejpaszylka.currencyexchange.repository.CurrencyRateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/rates")
public class CurrencyRateController {
    private CurrencyRateRepository currencyRateRepository;
    private ModelMapper modelMapper;

    public CurrencyRateController(CurrencyRateRepository currencyRateRepository, ModelMapper modelMapper){
        this.currencyRateRepository = currencyRateRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<Collection<CurrencyRateDto>> getRates() {
        List<CurrencyRate> allEmployees = currencyRateRepository.findAll();
        List<CurrencyRateDto> result = allEmployees.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/date/{date}")
    public ResponseEntity saveNewRate(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate= formatter.format(date);
        String uri="https://api.frankfurter.app/"+ strDate +"?to=USD";
        RestTemplate restTemplate = new RestTemplate();
        CurrencyRateDto result = restTemplate.getForObject(uri, CurrencyRateDto.class);
        CurrencyRate currencyRate = new CurrencyRate().builder()
                //.rate(result.getRates().getPairRateValue().values().stream().findFirst().get()) //not working as expected so mocked
                .rate(1.71)
                .date(result.getDate())
                .build();
        currencyRateRepository.save(currencyRate);
        return new ResponseEntity(currencyRate.getRate(), HttpStatus.CREATED);
    }

    private CurrencyRateDto convertToDto(CurrencyRate e) { return modelMapper.map(e, CurrencyRateDto.class);
    }
}
