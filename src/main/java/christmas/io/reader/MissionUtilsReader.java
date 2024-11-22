package christmas.io.reader;

import camp.nextstep.edu.missionutils.Console;

public class MissionUtilsReader implements Reader {
	
	@Override
	public String readLine() {
		return Console.readLine();
	}
}
