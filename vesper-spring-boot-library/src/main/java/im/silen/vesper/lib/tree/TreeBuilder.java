package im.silen.vesper.lib.tree;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class TreeBuilder<T> implements UnaryOperator<List<TreeNode>> {
    Function<TreeNode, ? extends T> classifier;
    Function<T, Predicate<TreeNode>> asParentNode;
    TreeBuilder<?> eachChildNode;

    public static <T> TreeBuilder<T> groupBy(Function<TreeNode, ? extends T> classifier) {
        return new TreeBuilder<>(classifier);
    }

    private TreeBuilder(Function<TreeNode, ? extends T> classifier) {
        this.classifier = classifier;
    }

    public TreeBuilder<T> children(Function<T, Predicate<TreeNode>> asParentNode){
        this.asParentNode = asParentNode;
        return this;
    }

    public <K> TreeBuilder<T> forEach(TreeBuilder<K> eachChildNode){
        this.eachChildNode = eachChildNode;
        return this;
    }

    @Override
    public List<TreeNode> apply(List<TreeNode> treeNodes) {
        return treeNodes.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(classifier), listMap -> listMap
                .entrySet()
                .stream()
                .flatMap(entry -> {
                    Map<Boolean, List<TreeNode>> parts = entry.getValue().stream()
                            .collect(Collectors.partitioningBy(asParentNode.apply(entry.getKey())));

                    List<TreeNode> children;
                    if (eachChildNode == null) {
                        children = parts.get(Boolean.FALSE);
                    } else {
                        children = eachChildNode.apply(parts.get(Boolean.FALSE));
                    }
                    return parts.get(Boolean.TRUE).stream()
                            .limit(1)
                            .peek(career -> career.setChildren(children));
                }).collect(Collectors.toList())));
    }
}