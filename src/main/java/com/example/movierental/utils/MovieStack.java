package com.example.movierental.utils;

import com.example.movierental.models.Movie;

public class MovieStack {
    private final int capacity = 5;
    private final Movie[] stack;
    private int top;

    public MovieStack() {
        stack = new Movie[capacity];
        top = -1;
    }

    public void push(Movie movie) {
        // Remove existing occurrence
        for (int i = 0; i <= top; i++) {
            if (stack[i].getId().equals(movie.getId())) {
                removeAt(i);
                break;
            }
        }

        // If stack is full, remove bottom item
        if (top == capacity - 1) {
            removeAt(0);
        }

        // Shift elements up and push new movie on top
        stack[++top] = movie;
    }

    private void removeAt(int index) {
        for (int i = index; i < top; i++) {
            stack[i] = stack[i + 1];
        }
        stack[top] = null;
        top--;
    }

    public Movie[] getAll() {
        Movie[] result = new Movie[top + 1];
        for (int i = 0; i <= top; i++) {
            result[i] = stack[i];
        }
        return result;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}