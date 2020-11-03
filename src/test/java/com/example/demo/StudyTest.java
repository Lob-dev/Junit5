package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

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
    void create_new_study() {

        // Arrange
        Study study = new Study(10);

        System.out.println("StudyTest.create_new_study");

        // Assert
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 만들었을 때는 "+StudyStatus.DRAFT+" 상태여야 한다."),
                    // 단순히 문자열을 넘기는 방식으로 작성할 경우 성공 실패 여부와 상관없이 문자열 연산을 하지만
                    // 람다식으로 만들었을 경우에는 해당 함수가 필요할 때만 호출되어 생성됨으로 자원을 아낄 수 있다.
                () -> assertTrue(study.getLimit() > 0, "스터디 참석 가능 인원은 0보다 커야합니다.")
        );

    }

}