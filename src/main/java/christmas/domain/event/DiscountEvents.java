package christmas.domain.event;

import christmas.common.dto.EventResultDto;
import christmas.domain.order.Orders;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum DiscountEvents implements DiscountEvent {
	/*
	크리스마스 디데이 할인
	1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
	 */
	CHRISTMAS_D_DAY_DISCOUNT {
		@Override
		public Optional<EventResultDto> applyEvent(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(new EventResultDto(
					"크리스마스 디데이 할인",
					1000 + (orders.getDate().getDayOfMonth() - 1) * 100
			));
		}
		
		private static boolean canApply(Orders orders) {
			return EventPeriod.isInPeriod(2023, 12, 1, 25, orders.getDate());
		}
		
		@Override
		public boolean isEventYearMonth(int year, int month) {
			return year == 2023 && month == 12;
		}
	},
	
	/*
	평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
	 */
	WEEKDAY_DISCOUNT {
		@Override
		public Optional<EventResultDto> applyEvent(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(new EventResultDto(
					"평일 할인",
					orders.getDessertCount() * 2023
			));
		}
		
		private static boolean canApply(Orders orders) {
			DayOfWeek orderDay = orders.getDate().getDayOfWeek();
			return orderDay != DayOfWeek.SATURDAY
					&& orderDay != DayOfWeek.FRIDAY
					&& EventPeriod.isInPeriod(2023, 12, orders.getDate());
		}
		
		@Override
		public boolean isEventYearMonth(int year, int month) {
			return year == 2023 && month == 12;
		}
	},

	/*
	주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
	 */
	WEEKEND_DISCOUNT {
		@Override
		public Optional<EventResultDto> applyEvent(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(new EventResultDto(
					"주말 할인",
					orders.getMainCount() * 2023
			));
		}
		
		private static boolean canApply(Orders orders) {
			DayOfWeek orderDay = orders.getDate().getDayOfWeek();
			return (orderDay == DayOfWeek.SATURDAY || orderDay == DayOfWeek.FRIDAY)
					&& EventPeriod.isInPeriod(2023, 12, orders.getDate());
		}
		
		@Override
		public boolean isEventYearMonth(int year, int month) {
			return year == 2023 && month == 12;
		}
	},
	
	/*
	특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
	 */
	STAR_DISCOUNT {
		@Override
		public Optional<EventResultDto> applyEvent(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(new EventResultDto(
					"특별 할인",
					1000
			));
		}
		
		private static boolean canApply(Orders orders) {
			return StarCalendar.isStarDate(orders.getDate())
					&& EventPeriod.isInPeriod(2023, 12, orders.getDate());
		}
		
		@Override
		public boolean isEventYearMonth(int year, int month) {
			return year == 2023 && month == 12;
		}
	},
	;
	
	public static List<DiscountEvent> of(int year, int month) {
		return Arrays.stream(DiscountEvents.values())
				.filter(discountEvent -> discountEvent.isEventYearMonth(year, month))
				.map(discountEvent -> (DiscountEvent) discountEvent)
				.toList();
	}
}
