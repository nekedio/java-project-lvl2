package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class Parser {
    static Map<String, Object> getContents(String path, String extension) throws Exception {
        Map<String, Object> map;
        ObjectMapper mapper;

        File file = new File(path);
        byte[] fileContents = Files.readAllBytes(file.toPath());
        switch (extension) {
            case ("json") -> {
                mapper = new ObjectMapper();
                map = mapper.readValue(fileContents, new TypeReference<>() {
                });
            }
            case ("yml") -> {
                mapper = new ObjectMapper(new YAMLFactory());
                map = mapper.readValue(fileContents, new TypeReference<>() {
                });
            }
            default -> throw new RuntimeException("\"" + extension + "\" invalid file extension");
        }

        return map;
    }
}
