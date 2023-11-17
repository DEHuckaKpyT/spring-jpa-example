package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

/**
 * Created on 16.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@UtilityClass
public class ResourceUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static <T> T parseJson(String resourcePath, Class<T> clazz) {
        ClassPathResource resource = new ClassPathResource(resourcePath);

        return mapper.readValue(resource.getInputStream(), clazz);
    }

    @SneakyThrows
    public static <T> T parseJson(String resourcePath, TypeReference<T> typeReference) {
        ClassPathResource resource = new ClassPathResource(resourcePath);

        return mapper.readValue(resource.getInputStream(), typeReference);
    }
}
