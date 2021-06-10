package com.epam.prejap.tetris.greeting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Michał Cwynar
 */
@Test(groups = {"greeting"})
public class GreetingTest {
    @Test(dataProvider = "time")
    public void welcomeThePlayer_invokingThisMethodWithoutPressingTheEscKey_stopsTheProgramRoughlyForGivenTime(long time) {
        // given
        Greeting greeting = new Greeting(time);
        // when
        long startTime = System.nanoTime();
        greeting.welcomeThePlayer();
        long endTime = System.nanoTime();
        // then
        assertThat((endTime-startTime)/1000000).isBetween(time-200, time+200);
    }

    @DataProvider
    public static Object[][] time() {
        return new Object[][]{
                {100},
                {500},
                {1000},
                {2500},
                {5000},
                {10000}
        };
    }
}
