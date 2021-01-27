package im.silen.vesper.career;

import im.silen.vesper.lib.json.JSONArray;
import im.silen.vesper.lib.resource.ResourceUtil;
import im.silen.vesper.lib.tree.TreeBuilder;
import im.silen.vesper.lib.tree.TreeNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CareerTest {
    @Test
    void tree() {
        List<TreeNode> nodes = JSONArray.parse(ResourceUtil.lookup(Career.class, "./data/careers.json"), TreeNode.class);
        List<TreeNode> trees = Optional.of(nodes)
                .map(TreeBuilder
                        .groupBy(node -> node.getCode().substring(0, 1))
                        .children(key -> node -> node.getCode().startsWith(key + "00"))
                        .forEach(TreeBuilder
                                .groupBy(node -> node.getCode().substring(0, 3))
                                .children(key -> node -> node.getCode().startsWith(key + "00"))
                        )
                ).orElseThrow();

        System.out.println(JSONArray.stringify(trees));
    }
}
