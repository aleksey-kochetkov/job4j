package ru.job4j.condition;

/**
 * Глупый бот.
 * @author Aleksey Kochetkov
 */
public class DummyBot {

    /**
     * Отвечает на вопрос.
     * @param question текст вопроса
     * @return текст ответа
     */
    public String answer(String question) {
        String answer = "Это ставит меня в тупик. Задайте другой вопрос.";
        if (question.equals("Привет, Бот!")) {
            answer = "Привет, умник!";
        } else if (question.equals("Пока!")) {
            answer = "До скорой встречи!";
        }

        return answer;
    }
}
