package ua.kiev.prog.retrievers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.kiev.prog.json.Rate;

import java.util.HashMap;

@Component
public class RateRetriever {

    private static final String URL = "https://api.apilayer.com/fixer/latest?apikey=ft0WAoOyDooSJiX4DOnVKtUAwqa8LHIP";

    @Cacheable("rates")
    public Rate getRate() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        Rate rate = restTemplate
                .exchange("/rate",HttpMethod.GET, entity, Rate.class).getBody();
        return rate;
    }
}
