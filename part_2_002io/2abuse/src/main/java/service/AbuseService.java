package service;

import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.nio.CharBuffer;

/**
 * Задан входной символьный поток и выходной символьный поток. Надо
 * удалить все слова, входящие в массив abuse. Важно, все преобразования
 * нужно делать в потоке. Нельзя зачитать весь поток в память, удалить
 * слова и потом записать. Нужно все делать в потоке.
 * (Изменил сигнатуру метода на символьные потоки в соответствии с
 * текстом задания.)
 */
public class AbuseService {
    private Set<String> set;
    private int minLength;
    private int maxLength;
    private StringBuilder buffer;

    public void dropAbuses(Reader in, Writer out, String[] abuse)
                                                     throws IOException {
        this.init(abuse);
        this.readBuffer(in);
        while (this.buffer.length() > 0) {
            this.dropBuffer();
            this.writeBuffer(out);
            this.readBuffer(in);
        }
    }

    private void init(String[] abuses) {
        this.set = new HashSet<>();
        this.minLength = Integer.MAX_VALUE;
        for (String s : abuses) {
            this.set.add(s.toUpperCase());
            if (s.length() < this.minLength) {
                this.minLength = s.length();
            }
            if (s.length() > this.maxLength) {
                this.maxLength = s.length();
            }
        }
        this.buffer = new StringBuilder();
    }

    private void readBuffer(Reader in) throws IOException {
        char[] chars = new char[this.maxLength];
        int length = in.read(chars);
        if (length > 0) {
            this.buffer.insert(this.buffer.length(), chars, 0, length);
        }
    }

    private void dropBuffer() {
        for (int i = this.minLength; i <= this.buffer.length(); i++) {
            if (this.set.contains(
                            this.buffer.substring(0, i).toUpperCase())) {
                this.buffer.delete(0, i);
            }
        }
    }

    private void writeBuffer(Writer out) throws IOException {
        if (this.buffer.length() > 0) {
            out.write(this.buffer.charAt(0));
            this.buffer.deleteCharAt(0);
        }
    }
}
