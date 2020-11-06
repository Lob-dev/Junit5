package com.example.demo.junit;

import org.junit.jupiter.api.*;

public class AppTest {

    // 기본 테스트
    @Test
    @DisplayName("생성 테스트")
    public void create() {
        System.out.println("AppTest.create");
    }

    // 깨진 테스트 Ignore
    @Test
    @Disabled
    @DisplayName("깨진 테스트")
    public void brokenTest() {
        System.out.println("AppTest.brokenTest");
    }

    // 기본 테스트 2
    @Test
    @DisplayName("조회 테스트")
    public void find() {
        System.out.println("AppTest.find");
    }

    // 모든 테스트가 실행되기 이전에 1번 호출된다.
    @BeforeAll
    public static void beforeAll() {
        System.out.println("AppTest.beforeAll");
    }

    // 모든 테스트가 실행된 이후에 1번 호출된다.
    @AfterAll
    public static void afterAll() {
        System.out.println("AppTest.afterAll");
    }

    // 각각의 테스트가 실행되기 이전에 호출된다.
    @BeforeEach
    public void beforeEach() {
        System.out.println("AppTest.beforeEach");
    }

    // 각각의 테스트가 실행된 이후에 호출된다.
    @AfterEach
    public void afterEach() {
        System.out.println  ("AppTest.afterEach");
    }

}