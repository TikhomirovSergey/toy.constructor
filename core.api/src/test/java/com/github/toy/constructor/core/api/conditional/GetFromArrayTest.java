package com.github.toy.constructor.core.api.conditional;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.github.toy.constructor.core.api.StoryWriter.condition;
import static com.github.toy.constructor.core.api.conditions.ToGetObjectFromArray.getFromArray;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.fail;

public class GetFromArrayTest extends BaseConditionalTest {

    private static final String ARRAY_CONVERTED_FROM_LIST = "Array converted from list";

    @Test
    public void testOfDescriptionForFunctionWithArrayInputAndSingleOutput() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", VALUE_A),
                FIVE_SECONDS, FIVE_HUNDRED_MILLIS,
                true, true, () -> NOTHING_WAS_FOUND).toString(),
                is(format("%s. Criteria: %s", ARRAY_CONVERTED_FROM_LIST, "equals " + A_UPPER + " ignore case")));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", VALUE_A),
                FIVE_SECONDS, FIVE_HUNDRED_MILLIS,
                true, true, () -> NOTHING_WAS_FOUND).apply(LITERAL_LIST),
                Matchers.is(A_LOWER));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput2() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", VALUE_A),
                FIVE_SECONDS, true, true, () -> NOTHING_WAS_FOUND)
                        .apply(LITERAL_LIST),
                Matchers.is(A_LOWER));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput3() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", VALUE_A),
                true, true, () -> NOTHING_WAS_FOUND)
                        .apply(LITERAL_LIST),
                Matchers.is(A_LOWER));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput4() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", VALUE_A),
                FIVE_SECONDS, FIVE_HUNDRED_MILLIS, true, true)
                        .apply(LITERAL_LIST),
                Matchers.is(A_LOWER));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput5() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", VALUE_A),
                FIVE_SECONDS, true, true)
                        .apply(LITERAL_LIST),
                Matchers.is(A_LOWER));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput6() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", VALUE_A),
                true, true)
                        .apply(LITERAL_LIST),
                Matchers.is(A_LOWER));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput7() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                FIVE_SECONDS, FIVE_HUNDRED_MILLIS, () -> NOTHING_WAS_FOUND).apply(LITERAL_LIST),
                Matchers.is(ONE_NUM));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput8() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                FIVE_SECONDS, () -> NOTHING_WAS_FOUND).apply(LITERAL_LIST),
                Matchers.is(ONE_NUM));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput9() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY, () -> NOTHING_WAS_FOUND)
                        .apply(LITERAL_LIST),
                Matchers.is(ONE_NUM));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput10() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                FIVE_SECONDS, FIVE_HUNDRED_MILLIS).apply(LITERAL_LIST),
                Matchers.is(ONE_NUM));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput11() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                FIVE_SECONDS).apply(LITERAL_LIST),
                Matchers.is(ONE_NUM));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutput12() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY)
                        .apply(LITERAL_LIST),
                Matchers.is(ONE_NUM));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputIgnoringException() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", MALFORMED_PREDICATE),
                FIVE_SECONDS, FIVE_HUNDRED_MILLIS,
                true, true).apply(LITERAL_LIST),
                nullValue());
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputIgnoringException2() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", MALFORMED_PREDICATE),
                FIVE_SECONDS,
                true, true).apply(LITERAL_LIST),
                nullValue());
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputIgnoringException3() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", MALFORMED_PREDICATE),
                true, true).apply(LITERAL_LIST),
                nullValue());
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "java.lang.RuntimeException was caught. Message: Exception for the unit testing!")
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputWithoutIgnoringException() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", MALFORMED_PREDICATE),
                FIVE_SECONDS, FIVE_HUNDRED_MILLIS,
                true,
                false).apply(LITERAL_LIST),
                nullValue());
        fail("The exception throwing was expected");
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "java.lang.RuntimeException was caught. Message: Exception for the unit testing!")
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputWithoutIgnoringException2() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", MALFORMED_PREDICATE),
                FIVE_SECONDS,
                true,
                false).apply(LITERAL_LIST),
                nullValue());
        fail("The exception throwing was expected");
    }

    @Test(expectedExceptions = RuntimeException.class,
            expectedExceptionsMessageRegExp = "java.lang.RuntimeException was caught. Message: Exception for the unit testing!")
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputWithoutIgnoringException3() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals " + A_UPPER + " ignore case", MALFORMED_PREDICATE),
                true,
                false).apply(LITERAL_LIST),
                nullValue());
        fail("The exception throwing was expected");
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputWithNullResult() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals W ignore case", VALUE_A),
                FIVE_SECONDS, FIVE_HUNDRED_MILLIS,
                true,
                true).apply(EMPTY_LIST),
                nullValue());
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputWithNullResult2() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals W ignore case", VALUE_A),
                FIVE_SECONDS,
                true,
                true).apply(EMPTY_LIST),
                nullValue());
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputWithNullResult3() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals W ignore case", VALUE_A),
                true,
                true).apply(EMPTY_LIST),
                nullValue());
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputWithNullResult4() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                FIVE_SECONDS, FIVE_HUNDRED_MILLIS).apply(EMPTY_LIST),
                nullValue());
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputWithNullResult5() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                FIVE_SECONDS).apply(EMPTY_LIST),
                nullValue());
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputWithNullResult6() {
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY).apply(EMPTY_LIST),
                nullValue());
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputToCheckTimeOut() {
        long start = System.currentTimeMillis();
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals W ignore case", VALUE_W),
                FIVE_SECONDS,
                true,
                true).apply(LITERAL_LIST),
                nullValue());
        long end = System.currentTimeMillis();
        assertThat("Spent time in millis", end - start,
                greaterThanOrEqualTo(FIVE_SECONDS.toMillis()));
        assertThat("Difference between expected and actual duration", end - start - FIVE_SECONDS.toMillis(),
                lessThanOrEqualTo(200L));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputToCheckSleeping() {
        long start = System.currentTimeMillis();
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                condition("equals W ignore case", VALUE_W),
                ONE_MILLISECOND,
                FIVE_HUNDRED_MILLIS,
                true,
                true).apply(LITERAL_LIST),
                nullValue());
        long end = System.currentTimeMillis();
        assertThat("Spent time in millis", end - start,
                greaterThanOrEqualTo(FIVE_HUNDRED_MILLIS.toMillis()));
        assertThat("Difference between expected and actual duration", end - start - FIVE_HUNDRED_MILLIS.toMillis(),
                lessThanOrEqualTo(150L));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputToCheckTimeOut2() {
        long start = System.currentTimeMillis();
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                FIVE_SECONDS).apply(EMPTY_LIST),
                nullValue());
        long end = System.currentTimeMillis();
        assertThat("Spent time in millis", end - start,
                greaterThanOrEqualTo(FIVE_SECONDS.toMillis()));
        assertThat("Difference between expected and actual duration", end - start - FIVE_SECONDS.toMillis(),
                lessThanOrEqualTo(200L));
    }

    @Test
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputToCheckSleeping2() {
        long start = System.currentTimeMillis();
        assertThat(getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                ONE_MILLISECOND,
                FIVE_HUNDRED_MILLIS).apply(EMPTY_LIST),
                nullValue());
        long end = System.currentTimeMillis();
        assertThat("Spent time in millis", end - start,
                greaterThanOrEqualTo(FIVE_HUNDRED_MILLIS.toMillis()));
        assertThat("Difference between expected and actual duration", end - start - FIVE_HUNDRED_MILLIS.toMillis(),
                lessThanOrEqualTo(150L));
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "nothing was found")
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputToCheckTimeOutAndException() {
        long start = System.currentTimeMillis();
        try {
            getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                    condition("equals W ignore case", VALUE_W),
                    FIVE_SECONDS,
                    true,
                    true,
                    () -> NOTHING_WAS_FOUND).apply(LITERAL_LIST);
        }
        catch (Exception e) {
            long end = System.currentTimeMillis();
            assertThat("Spent time in millis", end - start,
                    greaterThanOrEqualTo(FIVE_SECONDS.toMillis()));
            assertThat("Difference between expected and actual duration", end - start - FIVE_SECONDS.toMillis(),
                    lessThanOrEqualTo(200L));
            throw e;
        }
        fail("The exception throwing was expected");
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "nothing was found")
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputToCheckTimeOutAndException2() {
        long start = System.currentTimeMillis();
        try {
            getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                    FIVE_SECONDS,
                    () -> NOTHING_WAS_FOUND).apply(EMPTY_LIST);
        }
        catch (Exception e) {
            long end = System.currentTimeMillis();
            assertThat("Spent time in millis", end - start,
                    greaterThanOrEqualTo(FIVE_SECONDS.toMillis()));
            assertThat("Difference between expected and actual duration", end - start - FIVE_SECONDS.toMillis(),
                    lessThanOrEqualTo(200L));
            throw e;
        }
        fail("The exception throwing was expected");
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "nothing was found")
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputToCheckTimeOutWithSleepAndException() {
        long start = System.currentTimeMillis();
        try {
            getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                    condition("equals W ignore case", VALUE_W),
                    FIVE_SECONDS,
                    FIVE_HUNDRED_MILLIS,
                    true,
                    true,
                    () -> NOTHING_WAS_FOUND).apply(LITERAL_LIST);
        }
        catch (Exception e) {
            long end = System.currentTimeMillis();
            assertThat("Spent time in millis", end - start,
                    greaterThan(FIVE_SECONDS.toMillis()));
            assertThat("Difference between expected and actual duration", end - start - FIVE_SECONDS.toMillis(),
                    lessThanOrEqualTo(600L));
            throw e;
        }
        fail("The exception throwing was expected");
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "nothing was found")
    public void testOfApplyingOfFunctionWithArrayInputAndSingleOutputToCheckTimeOutWithSleepAndException2() {
        long start = System.currentTimeMillis();
        try {
            getFromArray(ARRAY_CONVERTED_FROM_LIST, CONVERT_LIST_TO_ARRAY,
                    FIVE_SECONDS,
                    FIVE_HUNDRED_MILLIS,
                    () -> NOTHING_WAS_FOUND).apply(EMPTY_LIST);
        }
        catch (Exception e) {
            long end = System.currentTimeMillis();
            assertThat("Spent time in millis", end - start,
                    greaterThan(FIVE_SECONDS.toMillis()));
            assertThat("Difference between expected and actual duration", end - start - FIVE_SECONDS.toMillis(),
                    lessThanOrEqualTo(600L));
            throw e;
        }
        fail("The exception throwing was expected");
    }
}
