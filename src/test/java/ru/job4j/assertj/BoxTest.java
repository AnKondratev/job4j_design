package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisNumberOfVerticesTetrahedron() {
        Box tetrahedron = new Box(4, 6);
        int numberOfVertex = tetrahedron.getNumberOfVertices();
        assertThat(numberOfVertex).isNotZero()
                .isPositive()
                .isEqualTo(4);
    }

    @Test
    void isThisNumberOfVerticesCube() {
        Box cube = new Box(8, 12);
        int numberOfVertex = cube.getNumberOfVertices();
        assertThat(numberOfVertex).isNotZero()
                .isPositive()
                .isEven()
                .isEqualTo(8);
    }

    @Test
    void isExistFigure() {
        Box box = new Box(4, 6);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isUnExistFigure() {
        Box box = new Box(-1, 6);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void isAreaTetrahedron() {
        Box tetrahedron = new Box(4, 6);
        double tetrahedronArea = tetrahedron.getArea();
        double expect = Math.sqrt(3) * (6 * 6);
        assertThat(tetrahedronArea).isNotZero()
                .isPositive()
                .isGreaterThan(-1)
                .isEqualTo(expect);
    }

    @Test
    void isAreaCube() {
        Box cube = new Box(8, 12);
        double cubeArea = cube.getArea();
        double expect = 6 * (12 * 12);
        assertThat(cubeArea).isNotZero()
                .isPositive()
                .isGreaterThan(-1)
                .isEqualTo(expect);
    }
}