package tracker;

/**
 * @author Aleksey Kochetkov
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;
    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных
     * @param tracker хранилище заявок
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        int key;
        do {
            menu.show();
            key = this.input.ask("Введите пункт меню : ", menu.getRange());
            menu.select(key);
        } while (key != MenuTracker.EXIT);
    }

    /**
     * Запуск программы.
     * @param args args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
