import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PhoneBookTest {
    PhoneBook phoneBook;
    @BeforeEach
    void setUp() {
        System.out.println("Начало теста");
         phoneBook = new PhoneBook();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Окончание теста");
        phoneBook = null;
    }

    public static Stream<Arguments> sourceAdd() {
        return Stream.of(
                Arguments.of(2, "Валера", "+7(905) 999-10-12", "Игорь", "+7(905) 999-10-12"),
                Arguments.of(1, "Валера", "+7(905) 999-20-20", "Валера", "+7(905) 999-20-20")
        );
    }
    @ParameterizedTest
    @MethodSource("sourceAdd")
    void add_test(int expected, String name1, String number1, String name2, String number2) {
        phoneBook.add(name1, name2);
        Assertions.assertEquals(expected, phoneBook.add(name2, number2));
    }
    public static Stream<Arguments> sourceFindByNumber() {
        return Stream.of(
                Arguments.of("Валера","Валера", "+7(905) 999-10-12", "Игорь", "+7(905) 999-10-12"),
                Arguments.of("Валера","Валера", "+7(905) 999-20-20", "Валера", "+7(905) 999-20-20")
        );
    }
    @ParameterizedTest
    @MethodSource("sourceFindByNumber")
    void findByNumber_test(String expected, String name1, String number1, String name2, String number2) {
        phoneBook.add(name1, number1);
        phoneBook.add(name2, number2);
        Assertions.assertEquals(expected, phoneBook.findByNumber(number1));
    }
    public static Stream<Arguments> sourceFindByName() {
        return Stream.of(
                Arguments.of("+7(905) 999-10-12", "Валера", "+7(905) 999-10-12", "Игорь", "+7(905) 999-10-12"),
                Arguments.of("+7(905) 999-20-20", "Валера", "+7(905) 999-20-20", "Валера", "+7(905) 999-20-20")
        );
    }
    @ParameterizedTest
    @MethodSource("sourceFindByName")
    void findByName_test(String expected, String name1, String number1, String name2, String number2) {
        phoneBook.add(name1, number1);
        phoneBook.add(name2, number2);
        Assertions.assertEquals(expected, phoneBook.findByName(name1));
    }
}
