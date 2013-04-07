package com.github.cruiser.tradeoutside.input;

import java.util.List;
import com.github.cruiser.tradeoutside.trade.Trade;

public abstract class InputFile {

	public abstract List<Trade> getTradeList(String fileName) throws Exception;
}
