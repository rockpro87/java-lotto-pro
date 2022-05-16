package lotto.controller;

import lotto.*;
import lotto.model.Lottos;
import lotto.model.Organizer;
import lotto.model.Store;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {
    private static final Store store = new Store(Config.LOTTO_ONE_GAME_PRICE);

    public static void buyLotto() {
        int amount = InputView.inputBuyAmount(store.inputAmountText());
        store.receiveAmount(amount);
        Lottos lottos = store.giveLotto();
        ResultView.printLottoNumbers(lottos);

        LottoController.drawNumber(lottos);
    }

    private static void drawNumber(Lottos lottos) {
        Organizer organizer = new Organizer(InputView.inputWinningNumbers());
        ResultView.printWinningResult(organizer.winningResults(lottos));
    }
}
