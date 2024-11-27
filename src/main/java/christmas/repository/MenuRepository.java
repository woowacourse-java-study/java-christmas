package christmas.repository;

import christmas.model.domain.Menu;
import java.util.HashMap;
import java.util.Map;

public class MenuRepository {
    private static final Map<String, Menu> menus = new HashMap<>();

    public static void addMenu(Menu menu) {
        menus.put(menu.getName(), menu);
    }

    public static Menu findByName(String name) {
        return menus.get(name);
    }
}