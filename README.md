# Sudoku
201812257 장현석 MobilePrograming sudoku project ReadMe

# 스도쿠 기능을 모두 구현

[1. 개요](#1-개요)

[2. 기능](#2-기능)

[3. 실행](#3-실행)

## 1. 개요

9×9 칸에서 진행되는 숫자 퍼즐 게임이다.
스도쿠를 구성하는 칸(cell)은 총 (3×3)×9 = 81칸, 3×3칸 9개로 세분화되며, 지켜야 할 룰은 다음과 같다.
각각의 가로줄(row)과 세로줄(column)에 1~9가 중복 없이 하나씩 들어가야한다.
3×3칸(box) 안에는 1~9가 중복 없이 하나씩 들어가야한다.

## 2. 기능

1. 숫자 입력 - 스도쿠 비어있는 칸에 짧게 클릭시 숫자 입력 패드 생성 / 숫자 입력, 삭제 가능  / ※초기 생성된 숫자는 삭제는 입력등 변경 불가능

2. 메모 입력 - 스도쿠 칸에 길게 클릭시 메모 입력 패드 생성 / 메모 입력, 삭제 가능 및 메모 입력시 기존 입력된 숫자 삭제 /※초기 생성된 숫자는 메모 불가능

3. conflict - 각각의 가로줄(row)과 세로줄(column)과 3×3칸(box)에 1~9가 중복 될시 중복되는 칸을 빨간색으로 표시

4. unconflict - 각각의 가로줄(row)과 세로줄(column)과 3×3칸(box)에 1~9가 중복 될시 중복이 사라질 시 다시 원상태로 복귀

5. Reset - 아래 리셋 버튼을 입력시 기존에 입력한 숫자와 메모가 모두 사라지고 새로운 스도쿠 보드 생성

## 3. 실행

스도쿠 보드 생성
![image](https://user-images.githubusercontent.com/74901548/208420665-89f504d5-92f6-41f0-a604-436bf9737fa9.png)

스도쿠 숫자 입력
![image](https://user-images.githubusercontent.com/74901548/208420783-3839fae5-1ee5-43d8-8c5f-f626c09e37ff.png)

스도쿠 메모 입력
![image](https://user-images.githubusercontent.com/74901548/208420886-bc481804-071d-4507-8a1b-47000522ffcd.png)

스도쿠 conflict / unconflict
![image](https://user-images.githubusercontent.com/74901548/208421003-cd65caa1-ad69-44b5-b30e-76fd2ca26117.png)

스도쿠 Reset
![image](https://user-images.githubusercontent.com/74901548/208421133-c0210f48-9925-414f-8301-c0ded17b600f.png)
