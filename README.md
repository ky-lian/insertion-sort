# Insertion-sort
Simple insertion sort example program.

This programm sorts data from the specified file in lexicographic order and writes the result in the specified file.
Every line of file may hold exactly one element of data. If output file is not existent, it will be created automatically.
Input file must not match output file. Warning: Data contained in the specified result file will be overwritten.

**Supported data types**: string and integer.

**Supported ordering**: ascending and descending.

## Usage: 
```
  java -jar FileInsertionSort.jar [OPTIONS]... [INPUT FILE] [OUTPUT FILE]
```
To run program, you need JRE of version 1.8 or higher.
## Options:
```
-help  -  prints help message
-a     -  ascending sort
-d     -  descending sort
-i     -  integer data type
-s     -  string data type
```

Note, that *-a* and *-d*, *-i* and *-s* are mutually exclusive options.


# Сортировка вставками
Простая программа для демонстрации сортировки вставками.

Эта программа сортирует данные из указанного файла в лексикографическом порядке и записывает результат в указанный файл.
Данные из входного файла должны быть записаны построчно в столбик (каждая строка файла - новый элемент).
Если выходной файл не существует, он будет создан автоматически. Входной файл не должен совпадать с выходным.
Внимание: данные, содержащиеся в выходном файле будут перезаписаны.

**Поддерживаемые типы данных**: Строки и числа.

**Поддерживаемые порядки сортировки**: по возрастанию и убыванию.

## Использование: 
```
  java -jar FileInsertionSort.jar [ОПЦИИ]... [ВХОДНОЙ ФАЙЛ] [ВЫХОДНОЙ ФАЙЛ]
```
Для запуска необходимо JRE версии 1.8 и выше.
## Options:
```
-help  -  выводит справочное сообщение
-a     -  сортировка по возрастанию
-d     -  сортировка по убыванию
-i     -  целочисленный тип данных
-s     -  строчный тип данных
```

Обратите внимание, что *-a* и *-d*, *-i* и *-s* взаимно исключающие опции.
