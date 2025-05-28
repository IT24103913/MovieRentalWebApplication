package com.example.movierental.utils;

import com.example.movierental.models.Movie;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MovieStack {
    private final int capacity = 5;
    private final Movie[] stack;
    private int top;

    public MovieStack() {
        stack = new Movie[capacity];
        top = -1;
    }

    public void push(Movie movie) {
        if (movie == null || movie.getId() == null) {
            return;
        }

        // Check if movie already exists in stack
        for (int i = 0; i <= top; i++) {
            if (stack[i] != null && movie.getId().equals(stack[i].getId())) {
                // Remove the existing movie
                removeAt(i);
                break;
            }
        }

        // If stack is full, remove bottom item
        if (top == capacity - 1) {
            removeAt(0);
        }

        // Add new movie to top
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
        return Arrays.copyOfRange(stack, 0, top + 1);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}