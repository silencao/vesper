package im.silen.vesper;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TreeNode {
    private String code;
    private String text;
    private List<TreeNode> children;

    public static <T> Collector<TreeNode, Object, List<TreeNode>> groupAndPartition(
            Function<TreeNode, ? extends T> classifier,
            Function<T, Predicate<TreeNode>> predicateFunction
    ) {
        return groupAndPartition(classifier, predicateFunction, UnaryOperator.identity());
    }

    public static <T> Collector<TreeNode, Object, List<TreeNode>> groupAndPartition(
            Function<TreeNode, ? extends T> classifier,
            Function<T, Predicate<TreeNode>> predicateFunction,
            UnaryOperator<List<TreeNode>> childNodesOperator
    ) {
        return Collectors.collectingAndThen(Collectors.groupingBy(classifier), listMap -> listMap
                .entrySet()
                .stream()
                .flatMap(entry -> {
                    Map<Boolean, List<TreeNode>> parts = entry.getValue().stream()
                            .collect(Collectors.partitioningBy(predicateFunction.apply(entry.getKey())));

                    List<TreeNode> children = childNodesOperator.apply(parts.get(Boolean.FALSE));
                    return parts.get(Boolean.TRUE).stream()
                            .limit(1)
                            .peek(career -> {
                                career.setChildren(children);
                            });
                }).collect(Collectors.toList()));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
