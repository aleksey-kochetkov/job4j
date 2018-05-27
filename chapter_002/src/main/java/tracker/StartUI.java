package tracker;

import java.util.Locale;

/**
 * @author Aleksey Kochetkov
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для вывода всех заявок.
     */
    private static final String SHOW = "1";
    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска по Id.
     */
    private static final String FIND_BY_ID = "4";
    /**
     * Константа меню для поиска по имени.
     */
    private static final String FIND_BY_NAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                //добавление заявки вынесено в отдельный метод.
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showAll();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавление новой заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки : ");
        String desc = this.input.ask("Введите текст заявки : ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с Id : " + item.getId() + "-----------");
    }

    /**
     * Строка для вывода на экран для заявки.
     * @param item заявка
     * @return строка
     */
    private String showItem(Item item) {
        return String.format("%s %s %s",
                    item.getName(), item.getDescription(), item.getId());
    }

    /**
     * Метод реализует вывод всех заявок на экран.
     */
    private void showAll() {
        System.out.println("------------ Вывод всех заявок --------------");
        for (Item item : this.tracker.getAll()) {
            System.out.format(this.showItem(item));
            System.out.println();
        }
//        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод реализует редактирование заявки.
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите Id заявки : ");
        Item item = this.tracker.findById(id);
        if (item == null) {
            System.out.println("------------ Заявка не существует -----------");
        } else {
            System.out.println(this.showItem(item));
            String name = this.input.ask("Введите новое имя : ");
            String desc = this.input.ask("Введите новый текст : ");
            item = new Item(name, desc);
            this.tracker.replace(id, item);
            System.out.println("------------ Заявка с Id : " + item.getId() + "-----------");
        }
    }

    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите Id заявки : ");
        Item item = this.tracker.findById(id);
        if (item == null) {
            System.out.println("------------ Заявка не существует -----------");
        } else {
            System.out.println(this.showItem(item));
            this.tracker.delete(id);
            System.out.println("------------ Удалена заявка с Id : " + id + "-----------");
        }
    }

    /**
     * Метод реализует поиск по Id.
     */
    private void findById() {
        System.out.println("------------ Поиск по Id --------------");
        String id = this.input.ask("Введите Id заявки : ");
        Item item = this.tracker.findById(id);
        if (item == null) {
            System.out.println("------------ Заявка не существует -----------");
        } else {
            System.out.println(this.showItem(item));
            System.out.println("------------ Поиск окончен -----------");
        }
    }

    /**
     * Метод реализует поиск по имени.
     */
    private void findByName() {
        System.out.println("------------ Поиск по имени --------------");
        String name = this.input.ask("Введите имя заявки : ");
        Item[] items = this.tracker.findByName(name);
        for (Item item : items) {
            System.out.println(this.showItem(item));
        }
        System.out.println("------------ Поиск окончен -----------");
    }

    private void showMenu() {
        System.out.println("Меню.");
        System.out.format("%s. %s", ADD, "Добавление новой заявки");
        System.out.println();
        System.out.format("%s. %s", SHOW, "Вывод всех заявок");
        System.out.println();
        System.out.format("%s. %s", EDIT, "Редактирование заявки");
        System.out.println();
        System.out.format("%s. %s", DELETE, "Удаление заявки");
        System.out.println();
        System.out.format("%s. %s", FIND_BY_ID, "Поиск по Id");
        System.out.println();
        System.out.format("%s. %s", FIND_BY_NAME, "Поиск по имени");
        System.out.println();
        System.out.format("%s. %s", EXIT, "Выход");
        System.out.println();
    }

    /**
     * Запуск программы.
     * @param args args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
