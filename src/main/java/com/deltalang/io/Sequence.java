package com.deltalang.io;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.NonFinal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor(staticName = "of")
public class Sequence<E> {
    List<E> elements;
    @NonFinal
    int index = -1;

    public static <E> Sequence<E> of(Sequence<E> sequence) {
        return Sequence.of(sequence.elements);
    }

    @SafeVarargs
    public static <E> Sequence<E> of(E... elements) {
        return Sequence.of(List.of(elements));
    }

    @SneakyThrows
    public static Sequence<Character> of(Path path) {
        return of(Files.readString(path).chars().mapToObj(it -> (char) it).toList());
    }

    public Sequence<E> copy() {
        return Sequence.of(this);
    }

    public E current() {
        return elements.get(index);
    }

    public boolean hasCurrent() {
        return index >= 0 && index < size();
    }

    public E next() {
        return next(1);
    }

    public E next(int offset) {
        return elements.get(index + offset);
    }

    public boolean hasNext() {
        return index + 1 < size();
    }

    public E previous() {
        return previous(1);
    }

    public E previous(int offset) {
        return elements.get(index - offset);
    }

    public boolean hasPrevious() {
        return index > 0;
    }

    public E consume() {
        return elements.get(++index);
    }

    public E unconsume() {
        return --index < 0 ? null : elements.get(index);
    }

    public int size() {
        return elements.size();
    }

    public int remaining() {
        return size() - index - 1;
    }
}
