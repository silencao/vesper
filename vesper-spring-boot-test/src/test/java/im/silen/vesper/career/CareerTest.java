package im.silen.vesper.career;

import im.silen.vesper.TreeNode;
import im.silen.vesper.lib.json.JSONArray;
import im.silen.vesper.lib.resource.ResourceUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CareerTest {
    @Test
    void tree() {
        List<TreeNode> nodes = JSONArray.parse(ResourceUtil.lookup(Career.class, "./data/careers.json"), TreeNode.class);
        List<TreeNode> trees = nodes
                .stream()
                .collect(TreeNode.groupAndPartition(
                        node -> node.getCode().substring(0, 1),
                        key -> node -> node.getCode().startsWith(key + "00"),
                        children -> children
                                .stream()
                                .collect(TreeNode.groupAndPartition(
                                        node -> node.getCode().substring(0, 3),
                                        key -> node -> node.getCode().startsWith(key + "00")
                                ))
                ));


        System.out.println(JSONArray.stringify(trees));
    }
}
