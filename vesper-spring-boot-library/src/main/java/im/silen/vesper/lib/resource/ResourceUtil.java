package im.silen.vesper.lib.resource;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class ResourceUtil {
    public static File lookup(Class<?> clazz, String path) {
        try {
            return ResourceUtils.getFile(clazz.getResource(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
