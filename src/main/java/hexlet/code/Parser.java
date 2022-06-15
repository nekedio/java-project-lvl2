package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {
    static Map<String, Object> getContents(String data, String extension) throws Exception {
        Map<String, Object> map;
        ObjectMapper mapper;

        switch (extension) {
            case ("json") -> {
                mapper = new ObjectMapper();
                map = mapper.readValue(data, new TypeReference<>() {
                });
            }
            case ("yml") -> {
                mapper = new ObjectMapper(new YAMLFactory());
                map = mapper.readValue(data, new TypeReference<>() {
                });
            }
            default -> throw new Exception("Unknown format: \"" + extension + "\"");
        }

        return map;
    }
}
