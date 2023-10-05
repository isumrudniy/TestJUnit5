package org.javarush;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ExtendWith(MockitoExtension.class)
public class AppTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertEquals(1, 1);
    }

    @Test
    @Disabled("Временно")
    @Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
    public void checkSum() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals((4 + 2), App.plusSum(4, 2));
    }

    @ParameterizedTest
    @CsvSource({"1,2", "3,4", "5,6"
    })
    public void checkSum2(int a, int b) {
        assertEquals((a + b), App.plusSum(a, b));
    }

    @ParameterizedTest
    @NullSource
    public void testParametrize(Integer argument) {
        assertEquals(null, argument);
    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    class MockTest {

        @Mock
        List listMock;

        @Test
        public void init() {
            List listMockito = Mockito.mock(ArrayList.class);

//            Mockito.doReturn(1).when(listMock).get(0); // Используется чаще
            Mockito.when(listMock.size()).thenReturn(10); // Используется реже

            listMockito.add(1);
            listMockito.add(3);

            listMock.add(1);
            listMock.add(2);

            assertEquals(10, listMock.size());

        }
    }

}
