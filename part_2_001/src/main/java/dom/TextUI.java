package dom;

import java.util.Map;
import java.math.BigDecimal;

public class TextUI {
    private Input input;
    private Processor processor = new Processor();

    public static void main(String[] args) {
        new TextUI(new ConsoleInput()).start();
    }

    public TextUI(Input input) {
        this.input = input;
    }

    void start() {
        String command;
        System.out.println("Обработка торговых заявок");
        do {
            this.printMenu();
            command = this.input.prompt("Выберите пункт меню: ");
        } while (!this.executeCommand(command));
    }

    void printMenu() {
        System.out.println("--------- Меню ---------");
        System.out.println("1. Ввод заявки");
        System.out.println("2. Просмотр биржевого стакана");
        System.out.println("3. Выход");
    }

    boolean executeCommand(String command) {
        boolean exit = false;
        if ("1".equals(command)) {
            this.inputOrder();
        } else if ("2".equals(command)) {
            this.viewDOM();
        } else if ("3".equals(command)) {
            exit = true;
        }
        return exit;
    }

    void inputOrder() {
        System.out.println("------ Ввод заявки -----");
        Order order = new Order();
        order.setBook(this.input.prompt("Идентификатор ценной бумаги: "));
        inputType(order);
        inputAction(order);
        inputPrice(order);
        inputVolume(order);
        if (order.getType() == Order.Type.DELETE) {
            this.processor.remove(order);
            System.out.format("Снята заявка, Id: %d%n", order.getId());
        } else {
            this.processor.add(order);
            System.out.format("Создана заявка, Id: %d%n", order.getId());
        }
    }

    private void inputType(Order order) {
        boolean result = false;
        do {
            String answer = this.input.prompt("Выставить(a), снять(d): ");
            if ("a".equalsIgnoreCase(answer)) {
                order.setType(Order.Type.ADD);
                result = true;
            } else if ("d".equalsIgnoreCase(answer)) {
                order.setType(Order.Type.DELETE);
                result = true;
            }
        } while (!result);
    }

    private void inputAction(Order order) {
        boolean result = false;
        do {
            String answer = this.input.prompt("Bid(b), ask(a): ");
            if ("b".equalsIgnoreCase(answer)) {
                order.setAction(Action.BID);
                result = true;
            } else if ("a".equalsIgnoreCase(answer)) {
                order.setAction(Action.ASK);
                result = true;
            }
        } while (!result);
    }

    private void inputPrice(Order order) {
        boolean result;
        do {
            result = true;
            try {
                order.setPrice(new BigDecimal(this.input.prompt("Цена: ")));
            } catch (NumberFormatException exception) {
                result = false;
            }
        } while (!result);
    }

    private void inputVolume(Order order) {
        boolean result;
        do {
            result = true;
            try {
                order.setVolume(Integer.parseInt(this.input.prompt("Количество: ")));
            } catch (NumberFormatException exception) {
                result = false;
            }
        } while (!result);
    }

    void viewDOM() {
        System.out.println("--- Просмотр биржевого стакана ---");
        String book = this.input.prompt("Идентификатор ценной бумаги: ");
        System.out.format("Продажа Цена   Покупка%n");
        for (Map.Entry<BigDecimal, DOMRecord> entry
                             : this.processor.process(book).entrySet()) {
            System.out.format("%7s %6s %7s%n", entry.getValue().getBidVolume(),
                                                            entry.getKey(),
                                          entry.getValue().getAskVolume());
        }
    }
}
