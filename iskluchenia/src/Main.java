
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static <string> void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите одним сообщением через пробел:");
        System.out.println("Ваше Фамилию, Имя и Отчество в формате строк.");
        System.out.println("Дату вашего рождения строка формата dd.mm.yyyy");
        System.out.println("Номер вашего телефона - целое беззнаковое число без форматирования.");
        System.out.println("Ваш пол - символ латиницей f или m.");
        String userData = scan.nextLine();
        isQuantityCorrect(userData);
        parseStringArray(userData);
        String fileName = parseStringArray(userData);
        createFile(userData, fileName);
    }

    public static void isQuantityCorrect(String userData) {
        int countSpaces = 0;
        for (int i = 0; i < userData.length(); i++) {
            if (userData.charAt(i) == ' ') {
                countSpaces++;
            }
        }
        if (countSpaces != 5) {
            throw new IllegalArgumentException("Ошибка! Проверьте правильность ввода. Недостаток или исбыток данных");
        }
    }

    public static String parseStringArray(String userData) {
        String[] userDataArray = userData.split(" ");
        String fileName = userDataArray[0];
        for (int i = 0; i < 3; i++) {
            if (Pattern.matches(".*\\d+.*", userDataArray[i])) {
                throw new IllegalArgumentException("Ошибка! Проверьте правильность ввода следующих данных: Имя, Фамилия, Отчество");
            }
        }
        if (Pattern.matches(".*[a-zA-Z].*", userDataArray[3])) {
            throw new IllegalArgumentException("Ошибка! Проверьте правильность ввода. Наличие недопустимых символов");
        }
        if (Pattern.matches(".*[a-zA-Z].*", userDataArray[4]) && !userDataArray[4].equals("11")) {
            throw new IllegalArgumentException("Ошибка! Проверьте правильность ввода.");
        }
        if (!userDataArray[5].equals("m") && !userDataArray[5].equals("f")) {
            throw new IllegalArgumentException("Пол должен быть 'm' или 'f'");
        }
        return fileName;
    }

    public static void createFile(String userData, String fileName) {
        File file = new File(fileName);
        try{
            FileWriter writer;
            if(file.exists()){
                System.out.println("Файл " + fileName + " уже существует,");
                writer = new FileWriter(fileName, true);
            } else {
                writer = new FileWriter(fileName);
                System.out.println("Файл успешно создан: " + fileName);
            }
            writer.write(userData + "\n");
            writer.close();
        } catch (IOException e){
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
}