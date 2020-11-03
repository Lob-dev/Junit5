package com.example.demo;

import org.junit.jupiter.api.*;

public class AppTest {

    @Test
    @DisplayName("생성 테스트")
    public void create() {
        System.out.println("AppTest.create");
    }

    @Test
    @Disabled
    @DisplayName("깨진 테스트")
    public void brokenTest() {
        System.out.println("AppTest.brokenTest");
    }

    @Test
    @DisplayName("조회 테스트")
    public void find() {
        System.out.println("AppTest.find");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("AppTest.beforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("AppTest.beforeEach");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AppTest.afterAll");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AppTest.afterEach");
    }

}