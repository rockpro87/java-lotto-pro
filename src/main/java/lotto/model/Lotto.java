package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private static final Money SELLING_PRICE = new Money(1000);

    private final LottoNumbers numbers;

    private Lotto(LottoNumbers numbers) {
        this.numbers = numbers;
    }

    public static int getNumberOfLottosPurchasableWith(Money money) {
        if (money.isZero()) {
            return 0;
        }
        return (int) money.divideBy(SELLING_PRICE);
    }

    public static Lotto generate(List<Integer> numbers) {
        final List<LottoNumber> list = new ArrayList<>();
        for (int number : numbers) {
            list.add(new LottoNumber(number));
        }
        return new Lotto(new LottoNumbers(list));
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public Winning calculateWinning(LottoNumbers winNumbers) {
        final int count = numbers.calculateNumberOfMatch(winNumbers);
        return Winning.ofMatchCount(count);
    }

    public Money getSellingPrice() {
        return SELLING_PRICE;
    }
}