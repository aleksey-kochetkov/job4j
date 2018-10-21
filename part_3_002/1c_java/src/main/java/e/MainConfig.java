package e;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class MainConfig {

    @Bean
    public Storage storage() {
        return new Storage();
    }

    @Bean
    public UserStorage userStorage() {
        return new UserStorage(storage());
    }
}
