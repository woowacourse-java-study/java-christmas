package christmas.domain;

import java.util.Objects;

public class ParamsValidator {
	
	public static void validateParamsNotNull(final Object... params) {
		for (Object obj : params) {
			if (Objects.isNull(obj)) {
				throw new IllegalArgumentException();
			}
		}
	}
}
