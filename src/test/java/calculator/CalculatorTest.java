package calculator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    public void 숫자_덧셈() {
        //given
        Calculator calculator = new Calculator();
        int testNum = 2;
        //when
        calculator.addNum(testNum);

        //then
        assertThat(calculator.getTotalSum()).isEqualTo(2);
    }

    @Test
    public void 리스트의_모든_숫자_덧셈() {
        //given
        Calculator calculator = new Calculator();
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4));

        //when
        calculator.addNums(nums);

        //then
        assertThat(calculator.getTotalSum()).isEqualTo(10);
    }

}