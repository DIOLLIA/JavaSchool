package integration.schedule.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({
        "schedule.dao",
        "schedule.service",
        "schedule.entity"})
@Configuration
public class Component {
}
