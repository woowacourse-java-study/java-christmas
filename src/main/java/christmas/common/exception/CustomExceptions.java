package christmas.common.exception;

public enum CustomExceptions {
	
	ILLEGAL_ARGUMENT(
			"잘못된 입력입니다.",
			IllegalArgumentException.class
	),
	MENU_NAME_NOT_FOUND(
			"존재하지 않는 메뉴입니다.",
			IllegalArgumentException.class
	),
	ILLEGAL_DATE(
			"잘못된 날짜입니다.",
			IllegalArgumentException.class
	),
	ILLEGAL_ORDER_FORMAT(
			"잘못된 주문 형식입니다.",
			IllegalArgumentException.class
	),
	ILLEGAL_DATE_FORMAT(
			"잘못된 날짜 형식입니다.",
			IllegalArgumentException.class
	),
	OVER_MAX_RETRY_ATTEPMT(
			"최대 재시도 횟수를 초과했습니다.",
			IllegalStateException.class
	)
	;
	
	private final String message;
	private final Class<? extends RuntimeException> exceptionType;
	
	CustomExceptions(String message, Class<? extends RuntimeException> exceptionType) {
		this.message = message;
		this.exceptionType = exceptionType;
	}
	
	public RuntimeException get() {
		try {
			return exceptionType.getDeclaredConstructor(String.class).newInstance(message);
		} catch (Exception e) {
			return new RuntimeException(message);
		}
	}
}
