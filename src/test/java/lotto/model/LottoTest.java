package lotto.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void howManyLottosCanIBuyWith_3000원() {
        final Money price = new Money(3_000);
        final int count = Lotto.getNumberOfLottosPurchasableWith(price);
        assertThat(count).isEqualTo(3);
    }

    @Test
    void howManyLottosCanIBuyWith_0원() {
        final Money price = new Money(0);
        final int count = Lotto.getNumberOfLottosPurchasableWith(price);
        assertThat(count).isEqualTo(0);
    }

    @Test
    void generate_성공() {
        final Lotto lotto = Lotto.generate(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto).isNotNull();
        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    void generate_실패() {
        assertThatThrownBy(() -> Lotto.generate(Arrays.asList(1, 2, 3, 4, 5)))
                .isInstanceOf(RuntimeException.class);

        assertThatThrownBy(() -> Lotto.generate(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(RuntimeException.class);

    }

    @ParameterizedTest
    @MethodSource("provideWinNumbers")
    void calculateWinning(LottoNumbers winNumbers, Winning expectedWinning) {
        final Lotto lotto = Lotto.generate(Arrays.asList(1, 2, 3, 4, 5, 6));
        final Winning winning = lotto.calculateWinning(winNumbers);
        assertThat(winning).isEqualTo(expectedWinning);
    }

    private static Stream<Arguments> provideWinNumbers() {
        return Stream.of(
                Arguments.of(LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)), Winning.FIRST_PRIZE),
                Arguments.of(LottoNumbers.of(Arrays.asList(11, 12, 13, 14, 15, 16)), Winning.NONE),
                Arguments.of(LottoNumbers.of(Arrays.asList(1, 2, 3, 14, 15, 16)), Winning.FOURTH_PRIZE)
        );
    }
}