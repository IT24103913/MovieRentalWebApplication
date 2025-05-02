package com.example.movierental.repository;

import com.example.movierental.models.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    private static final String FILE_PATH = "utils/admins.txt";

    public static void saveAdmin(Admin admin) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(admin.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static List<Admin> getAllAdmins() {
//        List<Admin> admins = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                Admin admin = Admin.fromString(line);
//                if (admin != null) admins.add(admin);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return admins;
//    }
}
