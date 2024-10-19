import commands.*

/**
 * @author youngmyn
 */
fun main() {
    /*
    Метка начала цикла в основном блоке программы – переменная cycleInMain , строка (вайтспесовая, разумеется, из пробелов и табуляций), представляющая число 0 в бинарном представлении
Метка подпрограммы проверки валидности ввода validInputSubroutine
– строка с числом 1
метка завершения программы с ошибкой failWithErr– строка с числом 2
метка подсчета результата calcResultSubroutine – строка с числом 3
метка хорошего исхода trueResult – строка с числом 4
метка плохого исхода falseResult – строка с числом 5

Шесть метод для циклов print - cycleFor1...cycleFor6
Адрес переменной-счетчика для всех этих циклов cycleForCounter лежит по адресу 4
     */
    val countInput = 0
    val sumOfInput = 1
    val lastInputNumber = 2
    val resultOfDivision = 3

    val cycleInMain = " "
    val validInputSubroutine = "\t"
    val failWithErr = "\t "
    val calcResultSubroutine = "\t\t"
    val trueResult = "\t  "
    val falseResult = "\t \t"

    val cycleFor1 = "\t\t "
    val cycleFor2 = "\t\t\t"
    val cycleFor3 = "\t   "
    val cycleFor4 = "\t  \t"
    val cycleFor5 = "\t \t "
    val cycleFor6 = "\t \t\t"
    val cycleForCounter = 4
    val whitespaceProgram = listOf(

        PushNumber(countInput),//Инициализируем 3 переменные нулями
        PushNumber(0),
        Store(),
        PushNumber(sumOfInput),
        PushNumber(0),
        Store(),
        PushNumber(lastInputNumber),
        PushNumber(0),
        Store(),

        Label(cycleInMain),

        PushNumber(777),//Будем класть каждое введенное число в heap по адресу 777
        ReadNumber(),

        PushNumber(countInput),// 7 команд ниже делают инкремент переменной countInput
        Retrieve(),// 7 команд, карл! Ох уж эти стековые языки...
        PushNumber(1),
        Addition(),
        PushNumber(countInput),
        Swap(),
        Store(),

        PushNumber(777), //Проверяем, ввел ли пользователь -1
        Retrieve(),
        PushNumber(1),
        Addition(),
        JumpToLabelIfTopOfStackIsZero(validInputSubroutine),//Если ввел, то прыгаем на validInputSubroutine

        PushNumber(777),//засовываем в переменную lastInputNumber введенное число (с адреса 777, помним)
        Retrieve(),
        PushNumber(lastInputNumber),
        Swap(),
        Store(),

        PushNumber(sumOfInput),//прибавляем введенное число к sumOfInput
        Retrieve(),
        PushNumber(777),
        Retrieve(),
        Addition(),
        PushNumber(sumOfInput),
        Swap(),
        Store(),

        JumpToLabel(cycleInMain),// И идем к началу цикла


// ---------------------------------------------------------------
        //ПОДПРОГРАММА ВАЛИДНОСТИ ВВОДА
        Label(validInputSubroutine),
        /*
            Тут помним, что в сумму всех введенных чисел мы считаем и делитель (последнее введенное число).
            В дальнейшем нам, для правильной калькуляции, надо иметь отдельно сумму всех введенных чисел,
              кроме делителя, и сам делитель. Поэтому из переменной суммы всего инпута нам надо вычесть делитель.
            sumOfInput = sumOfInput – lastInputNumber
         */
        PushNumber(sumOfInput),//Вычли из sumOfInput lastInputNumber
        Retrieve(),
        PushNumber(lastInputNumber),
        Retrieve(),
        Subtraction(),

        PushNumber(sumOfInput),//И сохранили в sumOfInput
        Swap(),
        Store(),

        PushNumber(countInput),//Далее валидация общего количества введенных чисел:
        Retrieve(),
        PushNumber(3),
        Subtraction(),
        JumpToLabelIfTopOfStackIsNegative(failWithErr),//Если счетчик введенных чисел меньше 3х, то идем к метке failWithErr.
        JumpToLabel(calcResultSubroutine),//Иначе – идем к метке подсчета результата

// ---------------------------------------------------------------
        //ПОДПРОГРАММА НА КЕЙС С НЕКОРРЕКТНЫМ ВВОДОМ
        Label(failWithErr),

        PushNumber(116),//Кладем ascii коды строки "error, invalid input" в инвертированном порядке
        PushNumber(117),
        PushNumber(112),
        PushNumber(110),
        PushNumber(105),
        PushNumber(32),
        PushNumber(100),
        PushNumber(105),
        PushNumber(108),
        PushNumber(97),
        PushNumber(118),
        PushNumber(110),
        PushNumber(105),
        PushNumber(32),
        PushNumber(44),
        PushNumber(114),
        PushNumber(111),
        PushNumber(114),
        PushNumber(114),
        PushNumber(101),

        PushNumber(cycleForCounter),//В цикле For 20 раз подряд выводим символ на консоль
        PushNumber(-20),
        Store(),
        Label(cycleFor1),
        PushNumber(cycleForCounter),
        Retrieve(),
        PushNumber(1),
        Addition(),
        Swap(),
        PrintChar(),
        PushNumber(cycleForCounter),
        Swap(),
        Store(),
        PushNumber(cycleForCounter),
        Retrieve(),
        JumpToLabelIfTopOfStackIsNegative(cycleFor1),
        End(),

// ---------------------------------------------------------------
        //ПОДПРОГРАММА ПОДСЧЕТА РЕЗУЛЬТАТА
        /*
        Подпрограмма подсчет результата:
		Инициализируем переменную результата целочисленного деления
		Кладем в эту переменную значение целочисленного деления с первым операндом:
		        счетчик суммы введенных чисел, вторым: последнее введенное число.
Далее результат умножаем на последнее введенное число (наш делитель) и кладем наверх стека.
Из этого числа вычитаем сумму всех введенных чисел.
Если после этого наверху стека лежит 0, то значит,
                    сумма без остатка делится на введенный делитель, и мы идем к метке хорошего исхода
Если нет, то идем к метке плохого исхода

         */
        Label(calcResultSubroutine),

        PushNumber(resultOfDivision),//Кладем в стек адрес переменной resultOfDivision
        // Далее кладем значение. Но чтобы значение получить, сначала вычисляем его:
        PushNumber(sumOfInput),//Кладем первый операнд для деления
        Retrieve(),

        PushNumber(lastInputNumber),//Кладем второй операнд для деления
        Retrieve(),

        IntegerDivision(),//Заменяем эти 2 операнда в стеке результатом целочисленного деления

        Store(),//Сохраняем результат по адресу resultOfDivision, который положили в стек вначале подпрограммы

        PushNumber(resultOfDivision),//Опять кладем наверх стека значение переменной resultOfDivision,
        Retrieve(),                   //ибо при сохранении в heap она из стека удалилась

        PushNumber(lastInputNumber),/*Добавляем второй операнд (сейчас будем перемножать
        resultOfDivision с lastInputNumber). Логика следующая: как понять что остатка при делении не было?
        Например, как проверить, 21 делится на 5 с остатком или без него?
        Записываем результат целочисленного деления 21 на 5 (это 4), а затем 4 умножаем на 5.
        Если получили 21 - то поделилось без остатка, если нет - то с остатком. В данном случае 4*5 не равно 21,
        значит нацело не делится
        */
        Retrieve(),

        Multiplication(),//Перемножаем resultOfDivision с lastInputNumber

        PushNumber(sumOfInput), //Кладем наверх стека sumOfInput
        Retrieve(),

        Subtraction(),//Считаем выражение resultOfDivision*lastInputNumber - sumOfInput
        JumpToLabelIfTopOfStackIsZero(trueResult),//Если наверху стека 0, значит делится без остатка. Идем в соответсвующую подпрограмму
        JumpToLabel(falseResult),//Иначе идем к ветвлению, что сумма на делитель без остатка не делится.


// ---------------------------------------------------------------
        //ПОДПРОГРАММА НА СЛУЧАЙ, КОГДА СУММА ДЕЛИТСЯ БЕЗ ОСТАТКА
        Label(trueResult),
/*
Мы хотим получить вывод вида: "entered amount($sumOfInput) is divided without remainder by $lastInputNumber"
Я не нашел элегантного способа вывести число и символы в перемешку в цикле For.
Была идея прибавлять к числам 48, и печатать их ascii коды, но это будет работать только тогда, когда сумма
введенных чисел это число от 0 до 10, т.е. занимает 1 символ.
Поэтому сделаем так. В этой строке 47 символов и 2 числа, которые неоходимо вывести.
Поэтому у нас будет 2 цикла For для печати символов и 2 одиночных вывода чисел.
То есть сначла печатаем "entered amount(", потом число sumOfInput, потом " is divided without remainder by ",
потом число lastInputNumber

 */
//Кладем ascii коды строки "entered amount($sumOfInput) is divided without remainder by $lastInputNumber" в инвертированном порядке
        PushNumber(lastInputNumber),
        Retrieve(),

        PushNumber(32),
        PushNumber(121),
        PushNumber(98),
        PushNumber(32),
        PushNumber(114),
        PushNumber(101),
        PushNumber(100),
        PushNumber(110),
        PushNumber(105),
        PushNumber(97),
        PushNumber(109),
        PushNumber(101),
        PushNumber(114),
        PushNumber(32),
        PushNumber(116),
        PushNumber(117),
        PushNumber(111),
        PushNumber(104),
        PushNumber(116),
        PushNumber(105),
        PushNumber(119),
        PushNumber(32),
        PushNumber(100),
        PushNumber(101),
        PushNumber(100),
        PushNumber(105),
        PushNumber(118),
        PushNumber(105),
        PushNumber(100),
        PushNumber(32),
        PushNumber(115),
        PushNumber(105),
        PushNumber(32),
        PushNumber(41),

        PushNumber(sumOfInput),
        Retrieve(),
        PushNumber(40),

        PushNumber(116),
        PushNumber(110),
        PushNumber(117),
        PushNumber(111),
        PushNumber(109),
        PushNumber(97),
        PushNumber(32),
        PushNumber(100),
        PushNumber(101),
        PushNumber(114),
        PushNumber(101),
        PushNumber(116),
        PushNumber(110),
        PushNumber(101),

        PushNumber(cycleForCounter),// Первый цикл For - вывод "entered amount("
        PushNumber(-15),
        Store(),
        Label(cycleFor2),
        PushNumber(cycleForCounter),
        Retrieve(),
        PushNumber(1),
        Addition(),
        Swap(),
        PrintChar(),
        PushNumber(cycleForCounter),
        Swap(),
        Store(),
        PushNumber(cycleForCounter),
        Retrieve(),
        JumpToLabelIfTopOfStackIsNegative(cycleFor2),

        PrintNumber(),//Печатаем число - наш sumOfInput

        PushNumber(cycleForCounter),// Второй цикл For - вывод " is divided without remainder by "
        PushNumber(-34),
        Store(),
        Label(cycleFor3),
        PushNumber(cycleForCounter),
        Retrieve(),
        PushNumber(1),
        Addition(),
        Swap(),
        PrintChar(),
        PushNumber(cycleForCounter),
        Swap(),
        Store(),
        PushNumber(cycleForCounter),
        Retrieve(),
        JumpToLabelIfTopOfStackIsNegative(cycleFor3),

        PrintNumber(),//Печатаем число - наш lastInputNumber

        End(),

// ---------------------------------------------------------------
        //ПОДПРОГРАММА НА СЛУЧАЙ, КОГДА СУММА НЕ ДЕЛИТСЯ БЕЗ ОСТАТКА
        Label(falseResult),

        PushNumber(114),//Кладем ascii коды строки "entered amount($sumOfInput) is not divisible by $lastInputNumber without remainder" в инвертированном порядке
        PushNumber(101),
        PushNumber(100),
        PushNumber(110),
        PushNumber(105),
        PushNumber(97),
        PushNumber(109),
        PushNumber(101),
        PushNumber(114),
        PushNumber(32),
        PushNumber(116),
        PushNumber(117),
        PushNumber(111),
        PushNumber(104),
        PushNumber(116),
        PushNumber(105),
        PushNumber(119),
        PushNumber(32),

        PushNumber(lastInputNumber),
        Retrieve(),

        PushNumber(32),
        PushNumber(121),
        PushNumber(98),
        PushNumber(32),
        PushNumber(101),
        PushNumber(108),
        PushNumber(98),
        PushNumber(105),
        PushNumber(115),
        PushNumber(105),
        PushNumber(118),
        PushNumber(105),
        PushNumber(100),
        PushNumber(32),
        PushNumber(116),
        PushNumber(111),
        PushNumber(110),
        PushNumber(32),
        PushNumber(115),
        PushNumber(105),
        PushNumber(32),
        PushNumber(41),

        PushNumber(sumOfInput),
        Retrieve(),

        PushNumber(40),
        PushNumber(116),
        PushNumber(110),
        PushNumber(117),
        PushNumber(111),
        PushNumber(109),
        PushNumber(97),
        PushNumber(32),
        PushNumber(100),
        PushNumber(101),
        PushNumber(114),
        PushNumber(101),
        PushNumber(116),
        PushNumber(110),
        PushNumber(101),



        PushNumber(cycleForCounter),// Первый цикл For - вывод "entered amount("
        PushNumber(-15),
        Store(),
        Label(cycleFor4),
        PushNumber(cycleForCounter),
        Retrieve(),
        PushNumber(1),
        Addition(),
        Swap(),
        PrintChar(),
        PushNumber(cycleForCounter),
        Swap(),
        Store(),
        PushNumber(cycleForCounter),
        Retrieve(),
        JumpToLabelIfTopOfStackIsNegative(cycleFor4),

        PrintNumber(),//Печатаем число - наш sumOfInput

        PushNumber(cycleForCounter),//Второй цикл For - вывод ") is not divisible by "
        PushNumber(-22),
        Store(),
        Label(cycleFor5),
        PushNumber(cycleForCounter),
        Retrieve(),
        PushNumber(1),
        Addition(),
        Swap(),
        PrintChar(),
        PushNumber(cycleForCounter),
        Swap(),
        Store(),
        PushNumber(cycleForCounter),
        Retrieve(),
        JumpToLabelIfTopOfStackIsNegative(cycleFor5),

        PrintNumber(),//Печатаем число - наш lastInputNumber

        PushNumber(cycleForCounter),//Третий цикл For - вывод " without remainder"
        PushNumber(-18),
        Store(),
        Label(cycleFor6),
        PushNumber(cycleForCounter),
        Retrieve(),
        PushNumber(1),
        Addition(),
        Swap(),
        PrintChar(),
        PushNumber(cycleForCounter),
        Swap(),
        Store(),
        PushNumber(cycleForCounter),
        Retrieve(),
        JumpToLabelIfTopOfStackIsNegative(cycleFor6),

        End()
    )

    whitespaceProgram.forEach {print(it.toString())}
}

