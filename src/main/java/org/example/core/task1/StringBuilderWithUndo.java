package org.example.core.task1;

import jdk.internal.vm.annotation.IntrinsicCandidate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class StringBuilderWithUndo {
    private StringBuilder stringBuilder;
    private StringBuilderHistory history;

    public StringBuilderWithUndo() {
        this.stringBuilder = new StringBuilder();
        this.history = new StringBuilderHistory();

    }

    public void save() {
        history.addMemento(new StringBuilderMemento(stringBuilder.toString()));
    }

    public void undo() {
        StringBuilderMemento previousMemento = history.getLastMemento();
        stringBuilder = new StringBuilder(previousMemento.getState());

    }

    // Реализация SpringBuilder

    public int compareTo(StringBuilder another) {
        return stringBuilder.compareTo(another);
    }

    public StringBuilderWithUndo append(Object obj) {
        save();
        stringBuilder.append(obj);
        return this;
    }

    @IntrinsicCandidate
    public StringBuilderWithUndo append(String str) {
        save();
        stringBuilder.append(str);
        return this;
    }

    public StringBuilderWithUndo append(StringBuffer sb) {
        save();
        stringBuilder.append(sb);
        return this;
    }

    public StringBuilderWithUndo append(CharSequence s) {
        save();
        stringBuilder.append(s);
        return this;
    }

    public StringBuilderWithUndo append(CharSequence s, int start, int end) {
        save();
        stringBuilder.append(s, start, end);
        return this;
    }

    public StringBuilderWithUndo append(char[] str) {
        save();
        stringBuilder.append(str);
        return this;
    }

    public StringBuilderWithUndo append(char[] str, int offset, int len) {
        save();
        stringBuilder.append(str, offset, len);
        return this;
    }

    public StringBuilderWithUndo append(boolean b) {
        save();
        stringBuilder.append(b);
        return this;
    }

    @IntrinsicCandidate
    public StringBuilderWithUndo append(char c) {
        save();
        stringBuilder.append(c);
        return this;
    }

    @IntrinsicCandidate
    public StringBuilderWithUndo append(int i) {
        save();
        stringBuilder.append(i);
        return this;
    }

    public StringBuilderWithUndo append(long lng) {
        save();
        stringBuilder.append(lng);
        return this;
    }

    public StringBuilderWithUndo append(float f) {
        save();
        stringBuilder.append(f);
        return this;
    }

    public StringBuilderWithUndo append(double d) {
        save();
        stringBuilder.append(d);
        return this;
    }

    public StringBuilderWithUndo appendCodePoint(int codePoint) {
        save();
        stringBuilder.appendCodePoint(codePoint);
        return this;
    }

    public StringBuilderWithUndo delete(int start, int end) {
        save();
        stringBuilder.delete(start, end);
        return this;
    }

    public StringBuilderWithUndo deleteCharAt(int index) {
        save();
        stringBuilder.deleteCharAt(index);
        return this;
    }

    public StringBuilderWithUndo replace(int start, int end, String str) {
        save();
        stringBuilder.replace(start, end, str);
        return this;
    }

    public StringBuilderWithUndo insert(int index, char[] str, int offset, int len) {
        save();
        stringBuilder.insert(index, str, offset, len);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, Object obj) {
        save();
        stringBuilder.insert(offset, obj);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, String str) {
        save();
        stringBuilder.insert(offset, str);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, char[] str) {
        save();
        stringBuilder.insert(offset, str);
        return this;
    }

    public StringBuilderWithUndo insert(int dstOffset, CharSequence s) {
        save();
        stringBuilder.insert(dstOffset, s);
        return this;
    }

    public StringBuilderWithUndo insert(int dstOffset, CharSequence s, int start, int end) {
        save();
        stringBuilder.insert(dstOffset, s, start, end);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, boolean b) {
        save();
        stringBuilder.insert(offset, b);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, char c) {
        save();
        stringBuilder.insert(offset, c);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, int i) {
        save();
        stringBuilder.insert(offset, i);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, long l) {
        save();
        stringBuilder.insert(offset, l);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, float f) {
        save();
        stringBuilder.insert(offset, f);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, double d) {
        save();
        stringBuilder.insert(offset, d);
        return this;
    }

    public int indexOf(String str) {
        return stringBuilder.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return stringBuilder.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return stringBuilder.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return stringBuilder.lastIndexOf(str, fromIndex);
    }

    public StringBuilderWithUndo reverse() {
        save();
        stringBuilder.reverse();
        return this;
    }

    @Override
    @IntrinsicCandidate
    public String toString() {
        return stringBuilder.toString();
    }

    public int length() {
        return stringBuilder.length();
    }

    public int capacity() {
        return stringBuilder.capacity();
    }

    public void ensureCapacity(int minimumCapacity) {
        save();
        stringBuilder.ensureCapacity(minimumCapacity);
    }

    public void trimToSize() {
        save();
        stringBuilder.trimToSize();
    }

    public void setLength(int newLength) {
        save();
        stringBuilder.setLength(newLength);
    }

    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    public int codePointAt(int index) {
        return stringBuilder.codePointAt(index);
    }

    public int codePointBefore(int index) {
        return stringBuilder.codePointBefore(index);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return stringBuilder.codePointCount(beginIndex, endIndex);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return stringBuilder.offsetByCodePoints(index, codePointOffset);
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        stringBuilder.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    public void setCharAt(int index, char ch) {
        save();
        stringBuilder.setCharAt(index, ch);
    }

    public String substring(int start) {
        return stringBuilder.substring(start);
    }

    public CharSequence subSequence(int start, int end) {
        return stringBuilder.subSequence(start, end);
    }

    public String substring(int start, int end) {
        return stringBuilder.substring(start, end);
    }

    public IntStream chars() {
        return stringBuilder.chars();
    }

    public IntStream codePoints() {
        return stringBuilder.codePoints();
    }

    public boolean isEmpty() {
        return stringBuilder.isEmpty();
    }

    private class StringBuilderHistory {

        private List<StringBuilderMemento> mementoList = new ArrayList<>();

        public void addMemento(StringBuilderMemento memento) {
            mementoList.add(memento);
        }

        public StringBuilderMemento getLastMemento() {
            if (!mementoList.isEmpty()) {
                return mementoList.remove(mementoList.size() - 1);
            }
            throw new NoSuchElementException("There is no memento saved!");
        }
    }

    private class StringBuilderMemento {
        private final String state;

        public StringBuilderMemento(String str) {
            this.state = str;
        }
        public String restoreState() {
            return this.state;
        }

        public String getState() {
            return state;
        }
    }
}
