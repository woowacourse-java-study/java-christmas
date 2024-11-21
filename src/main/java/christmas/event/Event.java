package christmas.event;

import christmas.ParamsValidator;

public class Event {
	
	private final String name;
	private final EventType eventType;
	
	public Event(String name, EventType eventType) {
		ParamsValidator.validateParamsNotNull(name, eventType);
		this.name = name;
		this.eventType = eventType;
	}
	
	//TODO : 어떠한 주문에 대하여 할인을 적용해야함
}
