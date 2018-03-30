@ECHO OFF
dir /s /b *.java >src_files.txt
if not exist bin (mkdir bin)
javac @src_files.txt -d bin
IF [%1] == [] (ECHO no output file provided) ELSE (IF [%2] == [] (echo No main class file provided )  ELSE (jar cvfe myapp.jar com.preprocess -C bin/ . ) )