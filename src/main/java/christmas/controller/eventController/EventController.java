package christmas.controller.eventController;

import christmas.domain.event.Events;
import christmas.service.dateProvider.DateProvider;

public class EventController {
	
	private final DateProvider dateProvider;
	
	public EventController(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}
	
	public Events getEvents() {
		return Events.of(dateProvider.getYear(), dateProvider.getMonth());
	}
}
