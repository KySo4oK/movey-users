package micro.user.service.userservice;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Map;
import java.util.Properties;

public class DatabasePropertiesListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    ConfigClient configClient = new ConfigClient();

    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        Properties props = new Properties();
        Map<String, String> sources = configClient.getSources("user", "sccs");
        props.put("spring.datasource.url", "jdbc:postgresql://postgres-service.default.svc.cluster.local/POSTGRES_DB");
        props.put("spring.datasource.driverClassName", "org.postgresql.Driver");
        props.put("spring.datasource.username", sources.get("dbusername"));
        props.put("spring.datasource.password", sources.get("dbpassword"));
        props.put("spring.rabbitmq.password", sources.get("spring.rabbitmq.password"));
        props.put("spring.rabbitmq.username", sources.get("spring.rabbitmq.username"));
        environment.getPropertySources().addFirst(new PropertiesPropertySource("myProps", props));
    }
}
