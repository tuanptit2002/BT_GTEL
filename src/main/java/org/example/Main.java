package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String filePath = "users.csv";
        boolean check = true;

        if (new File(filePath).length() == 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("ID,Ma,FullName,Age,Number");
                writer.newLine();
            }
        }

        while (check) {
            System.out.println("-------- MENU----------");
            System.out.println("1. Thêm user");
            System.out.println("2. Xem danh sách user");
            System.out.println("3. Cập nhật user");
            System.out.println("4. Xóa user");
            System.out.println("Nhập lựa chọn khác để thoát chương trình");
            System.out.print("Nhập lựa chọn của bạn: ");
            int select = scanner.nextInt();

            switch (select) {
                case 1:
                    addUser(scanner, filePath);
                    break;
                case 2:
                    viewUsers(filePath);
                    break;
                case 3:
                    updateUser(scanner, filePath);
                    break;
                case 4:
                    deleteUser(scanner, filePath);
                    break;
                default:
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    check = false;
                    break;
            }
        }
    }

    private static void addUser(Scanner scanner, String filePath) throws IOException {
        System.out.print("Nhập số lượng user: ");
        int sl = scanner.nextInt();
        scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (int i = 1; i <= sl; i++) {
                System.out.println("Nhập id người thứ " + i + ": ");
                String id = scanner.nextLine();
                System.out.println("Nhập mã người thứ " + i + ": ");
                String ma = scanner.nextLine();
                System.out.println("Nhập họ tên người thứ " + i + ": ");
                String fullName = scanner.nextLine();
                System.out.println("Nhập tuổi người thứ " + i + ": ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("Nhập số điện thoại người thứ " + i + ": ");
                String number = scanner.nextLine();
                User user = new User(id, ma, fullName, age, number);
                writer.write(user.toString());
                writer.newLine();
            }
        }
        System.out.println("Thêm người dùng thành công!");
    }

    private static void viewUsers(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateUser(Scanner scanner, String filePath) {
        List<String> updatedLines = new ArrayList<>();
        scanner.nextLine();
        System.out.print("Nhập mã user muốn cập nhật: ");
        String ma = scanner.nextLine();
        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[1].equals(ma)) {
                    System.out.println("Nhập lại tên user: ");
                    userData[2] = scanner.nextLine();
                    System.out.println("Nhập lại tuổi user: ");
                    userData[3] = scanner.nextLine();
                    System.out.println("Nhập lại số điện thoại user: ");
                    userData[4] = scanner.nextLine();
                    line = String.join(",", userData);
                    userFound = true;
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (userFound) {
            try (BufferedWriter writerNew = new BufferedWriter(new FileWriter(filePath))) {
                for (String updatedLine : updatedLines) {
                    writerNew.write(updatedLine);
                    writerNew.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Cập nhật thông tin người dùng thành công!");
        } else {
            System.out.println("Không tìm thấy user với mã: " + ma);
        }
    }

    private static void deleteUser(Scanner scanner, String filePath) {
        List<String> dataUserNew = new ArrayList<>();
        scanner.nextLine();
        System.out.print("Nhập mã user muốn xóa: ");
        String maNew = scanner.nextLine();
        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (!userData[1].equals(maNew)) {
                    dataUserNew.add(line);
                } else {
                    userFound = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (userFound) {
            try (BufferedWriter writerNew = new BufferedWriter(new FileWriter(filePath))) {
                for (String updatedLine : dataUserNew) {
                    writerNew.write(updatedLine);
                    writerNew.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Xóa người dùng thành công!");
        } else {
            System.out.println("Không tìm thấy user với mã: " + maNew);
        }
    }
}
