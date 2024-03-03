package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleIsOne() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "one"));
        Role result = roleStore.findById("1");
        assertThat(result.getRole()).isEqualTo("one");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "one"));
        Role result = roleStore.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindroleIsOne() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "One"));
        roleStore.add(new Role("1", "Second"));
        Role result = roleStore.findById("1");
        assertThat(result.getRole()).isEqualTo("One");
    }

    @Test
    void whenReplaceThenRoleIsSecond() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "One"));
        roleStore.replace("1", new Role("1", "Second"));
        Role result = roleStore.findById("1");
        assertThat(result.getRole()).isEqualTo("Second");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "One"));
        roleStore.replace("10", new Role("10", "Second"));
        Role result = roleStore.findById("1");
        assertThat(result.getRole()).isEqualTo("One");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "One"));
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleIsOne() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "One"));
        roleStore.delete("10");
        Role result = roleStore.findById("1");
        assertThat(result.getRole()).isEqualTo("One");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "One"));
        boolean result = roleStore.replace("1", new Role("1", "Second"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "One"));
        boolean result = roleStore.replace("10", new Role("10", "Second"));
        assertThat(result).isFalse();
    }
}