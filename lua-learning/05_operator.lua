---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by westboy.
--- DateTime: 2021/3/31 4:02 下午
---

a = 21
b = 10
c = a + b
print("Line 1 - c 的值为(a + b)", c)
c = a - b
print("Line 2 - c 的值为(a - b)", c)
c = a * b
print("Line 3 - c 的值为(a * b)", c)
c = a / b
print("Line 4 - c 的值为(a / b)", c)
c = a % b
print("Line 5 - c 的值为(a % b)", c)
c = a ^ 2
print("Line 6 - c 的值为(a ^ 2)", c)
c = -a
print("Line 7 - c 的值为(-a)", c)

print("--------------------关系运算符--------------------")

a = 21
b = 10

if (a == b)
then
    print("Line 1 - a 等于 b")
else
    print("Line 1 - a 不等于 b")
end

if (a ~= b)
then
    print("Line 2 - a 不等于 b")
else
    print("Line 2 - a 等于 b")
end

if (a < b)
then
    print("Line 3 - a 小于 b")
else
    print("Line 3 - a 大于等于 b")
end

if (a > b)
then
    print("Line 4 - a 大于 b")
else
    print("Line 5 - a 小于等于 b")
end

-- 修改 a 和 b 的值
a = 5
b = 20
if (a <= b)
then
    print("Line 5 - a 小于等于  b")
end

if (b >= a)
then
    print("Line 6 - b 大于等于 a")
end

print("--------------------逻辑运算符--------------------")

a = 5
b = 20

if (a and b)
then
    print("Line 1 - 条件为 true")
end

if (a or b)
then
    print("Line 2 - 条件为 true")
end

-- 修改 a 和 b 的值
a = 0
b = 10

if (a and b)
then
    print("Line 3 - 条件为 true")
else
    print("Line 3 - 条件为 false")
end

if (not (a and b))
then
    print("Line 4 - 条件为 true")
else
    print("Line 4 - 条件为 false")
end

print("--------------------其他运算符--------------------")
a = "Hello "
b = "World"

print("连接字符串 a 和 b ", a .. b)

print("b 字符串长度 ", #b)

print("字符串 Test 长度 ", #"Test")

print("w3cschool菜鸟教程网址长度 ", #"www.w3cschool.cc")
