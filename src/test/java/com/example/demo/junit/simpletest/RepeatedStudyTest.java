package com.example.demo.junit.simpletest;

import com.example.demo.Study;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;
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

    @DisplayName("CSV 테스트")
    @ParameterizedTest(name = "{index} {displayName} SourceParam = {0}")
    @CsvSource({"data, 1", "data, 2", "data, 3"})
    void repeatParameterTest_CSV(String name, Integer limit) {
        Study study = new Study(limit, name);
        System.out.println("study = " + study);
    }

    @DisplayName("CSV argumentsAccessor 테스트")
    @ParameterizedTest(name = "{index} {displayName} SourceParam = {0}")
    @CsvSource({"data, 1", "data, 2", "data, 3"})
    void repeatParameterTest_CSV_argumentsAccessor(@AggregateWith(StudyAggregator.class) Study study) {
        //ArgumentsAggregator argumentsAccessor
        //Study study = new Study(argumentsAccessor.getInteger(1), argumentsAccessor.getString(0));
        System.out.println("study = " + study);
    }

    // Custom Aggregator
    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(1), accessor.getString(0));
        }
    }

}
