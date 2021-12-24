package ru.filit.mdma.dm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Service;
import ru.filit.mdma.dm.exception.WrongDataException;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class YamlService {

    private final ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory()).findAndRegisterModules();

     public <T> List<T> readYaml(File file, Class<T> clazz) throws WrongDataException {
         CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
         try {
             return objectMapper.readValue(file, listType);
         } catch (IOException e) {
             e.printStackTrace();
             throw new WrongDataException("Wrong data");
         }
     }

    public<T> void writeYaml(File file, List<T> list) throws WrongDataException {
        try {
            objectMapper.writeValue(file, list);
        } catch (IOException e) {
            throw new WrongDataException("Wrong data");
        }
    }
}
