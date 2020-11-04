package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class StudyTest {

    @Test
    @DisplayName("AAA 테스트 확인")
    void Arrange_Act_Assert() {
        // Arrange : 생성
        int a = 1;

        // Act : 조작
        int result = add(a);

        // Assert : 결과 비교
        assertEquals(2,result,"add 후 값은 2여야 합니다.");
        System.out.println("StudyTest.Arrange_Act_Assert");

    }
    public int add(int a){
        return a+1;
    }

    @Test
    @DisplayName("스터디 만들기")
    //@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "local")
    //@EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9})
    //@EnabledOnOs({OS.WINDOWS, OS.LINUX})
    void create_new_study() {

        // Arrange
        Study study = new Study(10);
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);

        // Assert
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "스터디를 만들었을 때는 "+StudyStatus.DRAFT+" 상태여야 한다."),
                    // 단순히 문자열을 넘기는 방식으로 작성할 경우 해당 코드는 성공 실패 여부와 상관없이 문자열 연산을 하지만
                    // 람다식으로 만들었을 경우에는 해당 함수가 필요할 때만 호출되어 생성됨으로 연산 자원을 아낄 수 있다.
                () -> assertTrue(study.getLimit() > 0, "스터디 참석 가능 인원은 0보다 커야합니다."),
                () -> assertThat(study.getLimit()).isGreaterThan(0),
                () -> assumeFalse("LOCAL".equalsIgnoreCase(test_env))
        );
        System.out.println("StudyTest.create_new_study");
    }

    @Test
    @Tag("fast") // tag 에 따른 테스트 제공
    @DisplayName("스터디 생성 시 ThrowException ")
    void create_new_study_ThrowException() {
        // assertThrows(IllegalArgumentException.class, () -> new Study(-10));

        // Arrange
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();

        // Assert
        assertEquals(message, "Limit 는 0보다 커야한다.");
    }

    @Test
    @DisplayName("스터디 생성 시 Time Out ")
    @Tag("slow")
    void create_new_study_And_Timeout() {
        assertTimeout(Duration.ofSeconds(1), () -> new Study(10));

        assertTimeout(Duration.ofMillis(315), () -> {
            new Study(10); // 9~14 Millis
            Thread.sleep(300);
        });

        // 해당 기능의 경우 이 코드 블럭을 별도의 쓰레드로 동작한다.
        // Thread Local 을 사용하는 코드가 내부에 존재한다면 예상치 못한 문제가 발생할 수 있다.
        // Transaction 을 사용하는 코드가 내부에 있다면 DB에 해당 테스트 내용이 반영될 수 있다.
        // Transaction 이 설정된 쓰레드가 아닌 별개의 쓰레드로 동작시키기 때문이다.
        assertTimeoutPreemptively(Duration.ofMillis(315), () -> {
            new Study(10); // 9~14 Millis
            Thread.sleep(300);
        });
    }
}