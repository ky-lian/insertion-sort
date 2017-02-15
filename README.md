# Insertion-sort
Simple insertion sort example program.

This programm sorts data from the specified file in lexicographic order and writes the result in the specified file.
Every line of file may hold exactly one element of data. If output file is not existent, program will create it.
Program will not work if the input file is the same as the output file.

**Supported data types**: string and integer.

**Supported ordering**: ascending and descending.

##Usage: 
```
  java -jar FileInsertionSort.jar [OPTIONS]... [INPUT FILE] [OUTPUT FILE]
```
##Options:
```
-help  -  prints help message
-a     -  ascending sort
-d     -  descending sort
-i     -  integer data type
-s     -  string data type
```

Note, that *-a* and *-d*, *-i* and *-s* are mutually exclusive options.
