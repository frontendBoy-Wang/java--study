@echo off
color 1f
cls
echo.
echo 1��ȡ�˺�
echo.
echo 2�˳�
echo.
SET t=
SET /P t=��ѡ��1/2:
IF /I '%t:~0,1%'=='1' GOTO start
IF /I '%t:~0,1%'=='2' GOTO stop
exit

:start
echo ���ڻ�ȡ,���Ժ�
java -jar E://jar/mrdear-1.0.jar
start D:\tools\��ǽ\ShadowsocksR-dotnet4.0.exe
goto finish

:stop
echo �����˳�,���Ժ�


goto end
:end
exet