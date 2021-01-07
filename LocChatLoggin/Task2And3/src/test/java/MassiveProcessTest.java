import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

public class MassiveProcessTest {

    private static MassiveProcess massiveProcess;

    @BeforeAll
    static void mainStart(){
        System.out.println("Главный тест метода начался");
    }

    @AfterAll
    static void mainEnd(){
        System.out.println("Главный тест метода закончился");
    }

    @BeforeEach
    void startUp() {
        massiveProcess = new MassiveProcess();
        System.out.println("промежуточный тест начался");
    }

    @AfterEach
    void end() {
        System.out.println("промежуточный тест закончился");
    }

    @Test
    public void testDiv1() {
        Integer raw1 []  = {1,2,3,4,5,6,7,8,9,11};
        System.out.println("Тест 1 идёт");
        Assertions.assertEquals(true ,massiveProcess.checkIfContainsOneAndFour(raw1));
    }

    @Test
    public void testDiv2() {
        Integer raw2 []  = {2,3,4,5,6,7,8,9,11};
        System.out.println("Тест 2 идёт");
        Assertions.assertEquals(false ,massiveProcess.checkIfContainsOneAndFour(raw2));
    }

    @Test
    public void testDiv3() {
        Integer raw3 []  = {1,2,3,5,6,7,8,9,11};
        System.out.println("Тест 3 идёт");
        Assertions.assertEquals(false ,massiveProcess.checkIfContainsOneAndFour(raw3));
    }

    @Test
    public void testDiv4() {
        Integer raw3 []  = {2,3,5,6,7,8,9,11};
        System.out.println("Тест 4 идёт");
        Assertions.assertEquals(false ,massiveProcess.checkIfContainsOneAndFour(raw3));
    }
}
