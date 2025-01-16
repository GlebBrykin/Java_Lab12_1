import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("********************");
        System.out.println("Задача 1");
        System.out.println("********************");
        Path inputFilePath = Paths.get("C:\\Users\\User\\Lab12_1\\src\\input.txt");
        Path outputFilePath = Paths.get("C:\\Users\\User\\Lab12_1\\src\\output.txt");
        try
        {
            List<String> lines = Files.readAllLines(inputFilePath);
            lines.replaceAll(String::toUpperCase);
            Files.write(outputFilePath, lines);
        }
        catch(IOException e)
        {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        }

        System.out.println("********************");
        System.out.println("Задача 2");
        System.out.println("********************");
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Введите команды для управления файлами и папками (введите 'exit' для выхода):");
        while(true)
        {
            System.out.print(">>> ");
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit"))
                break;
            String[] parts = command.split(" ");
            try
            {
                switch(parts[0].toLowerCase())
                {
                    case "create":
                        if((parts.length == 3) && parts[1].equalsIgnoreCase("folder"))
                        {
                            Path folderPath = Paths.get(parts[2]);
                            Files.createDirectories(folderPath);
                            System.out.println("Папка создана: " + folderPath);
                        }
                        else
                            System.out.println("Неверная команда. Используйте: create folder <path>");
                        break;
                    case "check":
                        if((parts.length == 3) && parts[1].equalsIgnoreCase("existence"))
                        {
                            Path checkPath = Paths.get(parts[2]);
                            if(Files.exists(checkPath))
                                System.out.println("Файл или папка существует: " + checkPath);
                            else
                                System.out.println("Файл или папка не существует: " + checkPath);
                        }
                        else
                            System.out.println("Неверная команда. Используйте: check existence <path>");
                        break;
                    case "write":
                        if((parts.length >= 4) && parts[1].equalsIgnoreCase("file"))
                        {
                            Path filePath = Paths.get(parts[2]);
                            String content = command.substring(command.indexOf(parts[3]));
                            Files.writeString(filePath, content);
                            System.out.println("Содержимое записано в файл: " + filePath);
                        }
                        else
                            System.out.println("Неверная команда. Используйте: write file <path> <content>");
                        break;
                    case "read":
                        if((parts.length == 3) && parts[1].equalsIgnoreCase("file"))
                        {
                            Path readPath = Paths.get(parts[2]);
                            String content = Files.readString(readPath);
                            System.out.println("Содержимое файла: " + content);
                        }
                        else
                            System.out.println("Неверная команда. Используйте: read file <path>");
                        break;
                    case "delete":
                        if(parts.length == 2)
                        {
                            Path deletePath = Paths.get(parts[1]);
                            Files.delete(deletePath);
                            System.out.println("Файл удален: " + deletePath);
                        }
                        else
                            System.out.println("Неверная команда. Используйте: delete <path>");
                        break;
                    default:
                        System.out.println("Неверная команда.");
                        break;
                }
            }
            catch(IOException e)
            {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }
}