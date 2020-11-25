package im.silen.vueboot.java.util.stream;

import im.silen.vesper.lib.json.JSONArray;
import im.silen.vueboot.growth.Growth;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorTest {
    @Test
    void name() {
        List<Growth> growths = JSONArray.parse("[{\"level\":746,\"sum\":\"  5.3t\"},{\"level\":748,\"sum\":\"  6.8t\"},{\"level\":750,\"sum\":\" 11.4t\"},{\"level\":754,\"sum\":\" 15.2t\"},{\"level\":754,\"sum\":\" 15.5t\"},{\"level\":756,\"sum\":\" 20.5t\"},{\"level\":758,\"sum\":\" 27.9t\"},{\"level\":758,\"sum\":\" 32.0t\"},{\"level\":758,\"sum\":\" 42.1t\"},{\"level\":767,\"sum\":\" 85.2t\"},{\"level\":767,\"sum\":\"106.5t\"},{\"level\":767,\"sum\":\"108.6t\"},{\"level\":769,\"sum\":\"172.9t\"},{\"level\":772,\"sum\":\"276.9t\"},{\"level\":777,\"sum\":\"535.0t\"},{\"level\":778,\"sum\":\"706.3t\"},{\"level\":779,\"sum\":\"981.4t\"},{\"level\":779,\"sum\":\"  1.0u\"},{\"level\":782,\"sum\":\"  1.1u\"},{\"level\":782,\"sum\":\"  1.8u\"},{\"level\":782,\"sum\":\"  1.9u\"},{\"level\":787,\"sum\":\"  2.9u\"},{\"level\":790,\"sum\":\"  3.7u\"},{\"level\":790,\"sum\":\"  4.0u\"},{\"level\":790,\"sum\":\"  5.2u\"},{\"level\":793,\"sum\":\"  7.4u\"},{\"level\":795,\"sum\":\" 10.2u\"},{\"level\":797,\"sum\":\" 18.0u\"},{\"level\":800,\"sum\":\" 20.4u\"},{\"level\":800,\"sum\":\" 30.9u\"},{\"level\":803,\"sum\":\" 36.8u\"},{\"level\":806,\"sum\":\" 46.4u\"},{\"level\":806,\"sum\":\" 55.6u\"},{\"level\":806,\"sum\":\" 68.1u\"},{\"level\":811,\"sum\":\" 78.6u\"},{\"level\":819,\"sum\":\"251.7u\"},{\"level\":820,\"sum\":\"368.7u\"},{\"level\":828,\"sum\":\"649.5u\"},{\"level\":831,\"sum\":\"864.5u\"},{\"level\":836,\"sum\":\"  3.6v\"},{\"level\":839,\"sum\":\"  5.5v\"},{\"level\":841,\"sum\":\"  7.9v\"},{\"level\":844,\"sum\":\" 12.9v\"},{\"level\":847,\"sum\":\" 20.7v\"},{\"level\":847,\"sum\":\" 24.6v\"},{\"level\":851,\"sum\":\" 42.3v\"},{\"level\":851,\"sum\":\" 47.6v\"},{\"level\":857,\"sum\":\"110.6v\"},{\"level\":870,\"sum\":\"478.7v\"},{\"level\":870,\"sum\":\"604.7v\"},{\"level\":895,\"sum\":\" 45.3w\"},{\"level\":907,\"sum\":\"270.4w\"},{\"level\":909,\"sum\":\"439.2w\"},{\"level\":913,\"sum\":\"769.7w\"},{\"level\":915,\"sum\":\"  1.3x\"},{\"level\":936,\"sum\":\" 19.8x\"},{\"level\":952,\"sum\":\"189.3x\"}]"
        , Growth.class);

        Map<Object, List<Growth>> group1 = growths.stream().collect(Collectors.groupingBy(Growth::getLevel));

        Long group2 = growths.stream().collect(Collectors.filtering(o -> {

            return true;
        }, Collectors.counting()));

        Byte collect = growths.stream().collect(Collectors.collectingAndThen(Collectors.counting(), Long::byteValue));
        System.out.println("========");

    }
}
