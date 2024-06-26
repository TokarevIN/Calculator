package arabian;


import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        //2+3
        //X+V=XV
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String expression = scn.nextLine();
        //Определяем арифметическое дейсвие:
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (expression.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        // Если не нашли арифметического действия, выбрасываем исключение
        if (actionIndex == -1) {
            throw new IllegalArgumentException("Строка не является математической операцией");
        }

        // Разделяем выражение на операнды
        String[] data = expression.split(regexActions[actionIndex]);

        // Проверка на количество операндов
        if (data.length != 2) {
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        //Определяем, находится ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            //Определяем римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                //Если римские, то конвертируем их в арабские
                //X+V
                //X-10
                //V-5
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            } else {
                //конвертируем арабские числа из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            if  (a <= 0 || a > 10||b <= 0 || b > 10) {

                throw new IllegalArgumentException("Число должно быть от 0 до 10");
            }
            // Выполняем с числами арифметическое действие
            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };
            // 15->XV
            if (isRoman) {
                if (result <= 0) {
                    throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
                }
                //Если числа были римские, возвращаем результат в римские числа
                System.out.println(converter.intToRoman(result));
            } else {
                //Если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        } else {
            throw new IllegalArgumentException("Используются одновременно разные системы счисления");
        }
    }
}