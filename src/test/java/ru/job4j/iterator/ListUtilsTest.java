package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfDone() {
        ListUtils.removeIf(input, el -> el < 2);
        assertThat(input.size()).isEqualTo(1);
        assertThat(input.get(0)).isEqualTo(3);
    }

    @Test
    void whenRemoveIfIsEmptyList() {
        input = List.of();
        assertThatThrownBy(() -> ListUtils.removeIf(input, el -> el < 2))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("List is empty");
    }

    @Test
    void whenReplaceIfOneElementDone() {
        ListUtils.replaceIf(input, el -> el < 2, 5);
        assertThat(input.size()).isEqualTo(2);
        assertThat(input.get(0)).isEqualTo(5);
        assertThat(input.get(1)).isEqualTo(3);
    }

    @Test
    void whenReplaceIfAllElementsDone() {
        ListUtils.replaceIf(input, el -> el < 4, 5);
        assertThat(input.size()).isEqualTo(2);
        assertThat(input.get(0)).isEqualTo(5);
        assertThat(input.get(1)).isEqualTo(5);
    }

    @Test
    void whenReplaceIfIsEmptyList() {
        input = List.of();
        assertThatThrownBy(() -> ListUtils.replaceIf(input, el -> el < 4, 5))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("List is empty");
    }

    @Test
    void whenRemoveAllDone() {
        input.add(2);
        input.add(6);
        input.add(12);
        var someList = List.of(3, 6);
        var exp = List.of(1, 2, 12);
        ListUtils.removeAll(input, someList);
        assertThat(input.size()).isEqualTo(3);
        assertThat(input).isEqualTo(exp);
    }

    @Test
    void whenRemoveAllisEmptyList() {
        input = List.of();
        var someList = List.of(3, 6);
        assertThatThrownBy(() -> ListUtils.removeAll(input, someList))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("List is empty");
    }
}