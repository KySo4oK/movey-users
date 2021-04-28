package micro.user.service.userservice;

import lombok.Data;

import java.util.Map;

@Data
public class ConfigEntity {
    private String name;
    private String[] profiles;
    private String label;
    private String version;
    private String state;
    private PropertySource[] propertySources;
}
