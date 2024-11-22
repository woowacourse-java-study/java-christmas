package christmas.event;

import christmas.order.Orders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

public enum DiscountEventList implements DiscountEvent {
	/*
	크리스마스 디데이 할인
	1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
	 */
	CRISTMAS_D_DAY_DISCOUNT {
		@Override
		public Optional<EventResult> applyEvent(Orders orders) {
			if (!canApply(orders)) {
				Optional.empty();
			}
			return Optional.of(new EventResult(
					"크리스마스 디데이 할인",
					1000 + (orders.getDate().getDayOfMonth() - 1) * 100
			));
		}
		
		private static boolean canApply(Orders orders) {
			return LocalDate.of(2023, 12, 1).isBefore(orders.getDate())
					&& orders.getDate().isBefore(LocalDate.of(2023, 12, 25));
		}
	},
	
	/*
	평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
	 */
	WEEKDAY_DISCOUNT {
		@Override
		public Optional<EventResult> applyEvent(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(new EventResult(
					"평일 할인",
					orders.getDessertCount() * 2023
			));
		}
		
		private static boolean canApply(Orders orders) {
			DayOfWeek orderDay = orders.getDate().getDayOfWeek();
			return orderDay != DayOfWeek.SATURDAY
					&& orderDay != DayOfWeek.FRIDAY
					&& LocalDate.of(2023, 12, 1).isBefore(orders.getDate())
					&& orders.getDate().isBefore(LocalDate.of(2023, 12, 31));
		}
	},

	/*
	주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
	 */
	WEEKEND_DISCOUNT {
		@Override
		public Optional<EventResult> applyEvent(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(new EventResult(
					"주말 할인",
					orders.getMainCount() * 2023
			));
		}
		
		private static boolean canApply(Orders orders) {
			DayOfWeek orderDay = orders.getDate().getDayOfWeek();
			return (orderDay == DayOfWeek.SATURDAY
					|| orderDay == DayOfWeek.FRIDAY)
					&& LocalDate.of(2023, 12, 1).isBefore(orders.getDate())
					&& orders.getDate().isBefore(LocalDate.of(2023, 12, 31));
		}
	},
	
	/*
	특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
	 */
	STAR_DISCOUNT {
		@Override
		public Optional<EventResult> applyEvent(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(new EventResult(
					"특별 할인",
					1000
			));
		}
		
		private static boolean canApply(Orders orders) {
			return StarCalendar.isStarDate(orders.getDate())
					&& LocalDate.of(2023, 12, 1).isBefore(orders.getDate())
					&& orders.getDate().isBefore(LocalDate.of(2023, 12, 31));
		}
	},
	;
}
