---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by westboy.
--- DateTime: 2021/3/31 3:47 下午
---

print("-----------------if-------------------")

a = 10;
if (a < 20) then
    print("a 小于 20");
end

print("a 的值为:" .. a);

print("-----------------if...else-------------------")

a = 100;
if (a < 20) then
    print("a 小于 20")
else
    print("a 大于 20")
end

print("a 的值为 :", a)

print("-----------------if...elseif...else-------------------")

a = 100
if (a == 10) then
    print("a 的值为 10")
elseif (a == 20) then
    print("a 的值为 20")
elseif (a == 30) then
    print("a 的值为 30")
else
    print("没有匹配 a 的值")
end
print("a 的真实值为: ", a)

print("-----------------if 嵌套-------------------")

a = 100;
b = 200;
if (a == 100) then
    if (b == 200) then
        print("a 的值为 100 b 的值为 200");
    end
end
print("a 的值为 :", a);
print("b 的值为 :", b);