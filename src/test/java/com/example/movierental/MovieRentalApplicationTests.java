package com.example.movierental;

import java.io.*;

class MovieRentalApplicationTest {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\kavee\\Desktop\\reviews.txt";  // Replace with your file path
        try {
            // Try to write a test line to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write("Test review to check file write access.");
            writer.newLine();
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}
