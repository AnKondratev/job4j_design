package ru.job4j.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenAddReturnsTrueOrFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2)).isTrue();
        assertThat(tree.add(2, 3)).isTrue();
        assertThat(tree.add(2, 3)).isFalse();
    }

    @Test
    void whenNotBinaryTree() {
        var simpleTree = new SimpleTree<>(1);
        simpleTree.add(1, 2);
        simpleTree.add(1, 3);
        simpleTree.add(1, 4);
        simpleTree.add(4, 5);
        simpleTree.add(5, 6);
        assertThat(simpleTree.isBinary()).isFalse();
    }

    @Test
    void whenBinaryTree() {
        var simpleTree = new SimpleTree<>(1);
        simpleTree.add(1, 2);
        simpleTree.add(1, 3);
        simpleTree.add(3, 5);
        simpleTree.add(5, 6);
        assertThat(simpleTree.isBinary()).isTrue();
    }
}