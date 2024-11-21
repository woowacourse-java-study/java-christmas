package christmas.event;

import christmas.ParamsValidator;

import java.util.List;

public class Events {
	
	private final List<Event> events;
	
	public Events(List<Event> events) {
		ParamsValidator.validateParamsNotNull(events);
		this.events = events;
	}
	
	//TODO : 증정메뉴 반환
	//TODO : 혜택내역 반환
	//TODO : 총 혜택금액 반환
}
