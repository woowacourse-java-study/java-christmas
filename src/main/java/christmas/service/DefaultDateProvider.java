package christmas.service;

public class DefaultDateProvider implements DateProvider {
	
	@Override
	public int getYear() {
		return 2023;
	}
	
	@Override
	public int getMonth() {
		return 12;
	}
}
