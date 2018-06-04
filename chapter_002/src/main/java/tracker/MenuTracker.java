package tracker;

/**
 * @author Aleksey Kochetkov
 */
public class MenuTracker {
    public static final int EXIT = 6;

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];
    private int[] range;

    /**
     * Строка для вывода на экран для заявки.
     * @param item заявка
     * @return строка
     */
    static String showItem(Item item) {
        return String.format("%s %s %s",
                item.getName(), item.getDescription(), item.getId());
    }

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        fillActions();
    }

    private void fillActions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindById();
        this.actions[5] = new FindByName();
        this.actions[EXIT] = new Exit();
        this.range = new int[] {0, 1, 2, 3, 4, 5, EXIT};
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : actions) {
            if (action == null) {
                break;
            }
            System.out.println(action.info());
        }
    }

    public int[] getRange() {
        return this.range;
    }

    private class AddItem extends BaseAction {

        public AddItem() {
            super(0, "Добавление новой заявки");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки : ");
            String desc = input.ask("Введите текст заявки : ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с Id : " + item.getId() + "-----------");
        }
    }

    private static class ShowItems extends BaseAction {

        public ShowItems() {
            super(1, "Вывод всех заявок");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Вывод всех заявок --------------");
            for (Item item : tracker.getAll()) {
                System.out.format(MenuTracker.showItem(item));
                System.out.println();
            }
        }
    }

    private class DeleteItem extends BaseAction {

        public DeleteItem() {
            super(3, "Удаление заявки");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите Id заявки : ");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("------------ Заявка не существует -----------");
            } else {
                System.out.println(MenuTracker.showItem(item));
                tracker.delete(id);
                System.out.println("------------ Удалена заявка с Id : " + id + "-----------");
            }
        }
    }

    private class FindById extends BaseAction {

        public FindById() {
            super(4, "Поиск по Id");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск по Id --------------");
            String id = input.ask("Введите Id заявки : ");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("------------ Заявка не существует -----------");
            } else {
                System.out.println(MenuTracker.showItem(item));
                System.out.println("------------ Поиск окончен -----------");
            }
        }
    }

    private class FindByName extends BaseAction {

        public FindByName() {
            super(5, "Поиск по имени");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск по имени --------------");
            String name = input.ask("Введите имя заявки : ");
            Item[] items = tracker.findByName(name);
            for (Item item : items) {
                System.out.println(MenuTracker.showItem(item));
            }
            System.out.println("------------ Поиск окончен -----------");
        }
    }

    private class Exit extends BaseAction {

        public Exit() {
            super(EXIT, "Выход");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }
    }
}

class EditItem extends BaseAction {

    public EditItem() {
        super(2, "Редактирование заявки");
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Редактирование заявки --------------");
        String id = input.ask("Введите Id заявки : ");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("------------ Заявка не существует -----------");
        } else {
            System.out.println(MenuTracker.showItem(item));
            String name = input.ask("Введите новое имя : ");
            String desc = input.ask("Введите новый текст : ");
            item = new Item(name, desc);
            tracker.replace(id, item);
            System.out.println("------------ Заявка с Id : " + item.getId() + "-----------");
        }
    }
}