package tank.config;

import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 * Created by Mayz
 * Date 2024/10/6 16:39
 * Description
 */
public class ConfigManager {

    Yaml yml = new Yaml();

    public Object get(String key) {
        Map<String, Object> params = yml.load(ConfigManager.class.getClassLoader().getResourceAsStream("application.yml"));
        if (CollectionUtils.isEmpty(params)) return null;
        return params.get(key);
    }
}