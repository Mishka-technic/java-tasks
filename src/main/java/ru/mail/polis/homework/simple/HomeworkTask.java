package ru.mail.polis.homework.simple;

import java.util.function.ToDoubleFunction;

public class HomeworkTask {

    /**
     * Нужно численно посчитать интеграл от a до b с шагом delta от функции function
     * Для того, что бы получить значение по Y для точки X, надо просто написать function.applyAsDouble(t)
     * Считаем, что функция определена на всем пространстве от a до b
     */
    public static double calcIntegral(double a, double b, ToDoubleFunction<Double> function, double delta) {
        double integral = 0.;

        for (double x = a; x < b; x += delta) {
            integral += delta * function.applyAsDouble(x);
        }

        return integral;
    }

    /**
     * Вывести номер максимальной цифры. Счет начинается слева направо,
     * выводим номер первой максимальной цифры (если их несколько)
     */
    public static byte maxNumber(long a) {
        long tempA = a;
        int counterNumbers = 0;

        while (tempA != 0) {
            counterNumbers++;
            tempA /= 10;
        }

        long maxNumber = -1;
        byte index = 1;

        for (byte i = 1; i <= counterNumbers; i++) {
            long currentNumber = (a / (long) Math.pow(10, (counterNumbers - i))) % 10;
            if (currentNumber > maxNumber) {
                maxNumber = currentNumber;
                index = i;
            }
        }

        return index;
    }


    /**
     * Даны две точки в пространстве (x1, y1) и (x2, y2). Вам нужно найти Y координату третьей точки (x3, y3),
     * которая находится на той же прямой что и первые две.
     */
    public static double lineFunction(int x1, int y1, int x2, int y2, int x3) {
        double k = (y1 - y2) / (double) (x1 - x2);
        double b = y1 - k * x1;

        return k * x3 + b;
    }

    /**
     * Даны 4 точки в пространстве A(x1, y1), B(x2, y2), C(x3, y3), D(x4, y4). Найдите площадь выпуклого
     * четырехуголька ABCD.
     * Это дополнительное задание, необязательное для выполнения
     */
    public static double square(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        double d1 = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));
        double d2 = Math.sqrt(Math.pow((x4 - x2), 2) + Math.pow((y4 - y2), 2));

        if (d1 == 0 || d2 == 0) {
            return 0.;
        }

        double cosY = ((x3 - x1) * (x4 - x2) + (y3 - y1) * (y4 - y2)) / (d1 * d2);

        return (0.5) * d1 * d2 * Math.sqrt(1 - Math.pow(cosY, 2));
    }

}
