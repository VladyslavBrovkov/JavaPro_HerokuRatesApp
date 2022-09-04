package ua.kiev.prog.retrievers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.kiev.prog.json.Rate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class RateRetriever {

    private static final String URL = "https://api.apilayer.com/fixer/latest?apikey=ft0WAoOyDooSJiX4DOnVKtUAwqa8LHIP";

    @Cacheable("rates")
    public Rate getRate() throws URISyntaxException {
        URI url = new URI(URL);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Rate> response = restTemplate.getForEntity(url, Rate.class);
        return response.getBody();
    }
}
