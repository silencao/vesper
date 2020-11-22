package im.silen.vueboot.autotest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AutoTestService {
    private static final Log logger = LogFactory.getLog(AutoTestService.class);

    public static AutoTestService create() {
        return new AutoTestService();
    }
}
