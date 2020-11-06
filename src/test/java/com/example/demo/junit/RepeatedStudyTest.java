package com.example.demo.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class RepeatedStudyTest {

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition} = {totalRepetitions}") // loopCount : 10
    void repeatTest(RepetitionInfo Info) {
        // @DisplayName("반복 테스트") == {displayName}
        // {currentRepetition} == getCurrentRepetition
        // {totalRepetitions} == getTotalRepetitions
        // getCurrentRepetition : 현재 테스트 횟수
        // getTotalRepetitions  : 총 테스트해야 할 수
        System.out.println(Info.getCurrentRepetition() +" = "+ Info.getTotalRepetitions());
    }

    // Junit 4에선 Junit Parameter Library 를 사용해야함
    @DisplayName("인자 테스트")
    @ParameterizedTest(name = "{index} {displayName} SourceParam = {0}")
    @ValueSource(strings = {"바보", "lob", "으윽"})
    @NullAndEmptySource // NullSource + EmptySource
    void repeatParameterTest(String SourceParam) {
        System.out.println("SourceParam = " + SourceParam);
    }

}
