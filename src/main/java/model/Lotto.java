package model;

import java.util.Collection;
import java.util.stream.Collectors;

public final class Lotto {

	private final Collection<LottoNumber> numberCollection;

	public Lotto(Collection<LottoNumber> numberCollection) {
		validate(numberCollection);
		this.numberCollection = numberCollection;
	}

	static Lotto from(Collection<LottoNumber> numberList) {
		return new Lotto(numberList);
	}

	public LottoRank rank(Lotto lotto) {
		int matchCount = 0;
		for (LottoNumber number : numberCollection) {
			matchCount += lotto.includedCount(number);
		}
		return LottoRank.valueOfMatchCount(matchCount);
	}

	public int size() {
		return numberCollection.size();
	}

	@Override
	public String toString() {
		return numberCollection.stream()
			.map(LottoNumber::toString)
			.collect(Collectors.joining(", "));
	}

	private int includedCount(LottoNumber number) {
		if (numberCollection.contains(number)) {
			return 1;
		}
		return 0;
	}

	private void validate(Collection<LottoNumber> numberCollection) {
		if (isEmpty(numberCollection)) {
			throw new IllegalArgumentException("'numberCollection' must not be empty");
		}
	}

	private boolean isEmpty(Collection<LottoNumber> numberCollection) {
		return numberCollection == null || numberCollection.isEmpty();
	}

}
