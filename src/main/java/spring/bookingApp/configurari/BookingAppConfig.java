package spring.bookingApp.configurari;

import com.theokanning.openai.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class BookingAppConfig {
    @Value("${api.openAI.key}")
    private String OPEN_AI_API_KEY;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(OPEN_AI_API_KEY);
    }
}

