package ua.kiev.prog.retrievers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.kiev.prog.json.Rate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

@Component
public class RateRetriever {

    private static final String URL = "https://api.apilayer.com/fixer/latest?apikey=ft0WAoOyDooSJiX4DOnVKtUAwqa8LHIP";

    @Cacheable("rates")
    public Rate getRate() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Rate> responseJson = restTemplate.exchange(URL, HttpMethod.GET, entity, Rate.class);
        return responseJson.getBody();
    }
}
