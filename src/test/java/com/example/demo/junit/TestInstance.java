package com.example.demo.junit;

import com.example.demo.Study;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.TestInstance.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@org.junit.jupiter.api.TestInstance(Lifecycle.PER_CLASS)
public class TestInstance {

    int value = 1;

    // 기본 테스트와 테스트 again 이 참조하는 인스턴스 필드 변수가 다르다.
    // Junit 은 기본 전략으로 테스트 메서드마다 새로운 인스턴스를 생성한다. -> 독립적인 실행을 통해 예상치 못한 부작용을 방지한다.
    // Junit 에서는 기본 전략을 변경할 수 있으며 해당 속성 값은 @TestInstance(Lifecycle.PER_CLASS) 이다. (테스트 Class : 1 Instance)
    // 기본적으로는 테스트 순서가 선언된 순서대로 종료되는 것이 맞지만, 모든 상황에서 그런 것은 아니다.
    // 테스트 간의 속성 값을 공유하여서는 어떤 값이 출력될 지 모른다. (물론 Final 이면 상관 없다.)

    @FastTest
    void create_new_study() {
        // Arrange
        Study actual = new Study(1);

        // Assert
        Assertions.assertThat(actual.getLimit()).isGreaterThan(0);
        System.out.println(this);
        System.out.println("create Study = " + value++);
    }

    @Test
    void create_new_study_again() {
        // Assert
        System.out.println(this);
        System.out.println("create Study = " + value++);
    }
}
