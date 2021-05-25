--- 数据类型

-- string
s = "Hello world"
print(type(s))
-- 计算字符串长度
print("字符串长度计算=" .. #s)
-- number
print(type(10.4 * 3))
-- function
print(type(print))
-- function
print(type(type))
-- boolean
print(type(true))
-- nil
print(type(nil))
-- string
print(type(type(X)))


-- 创建一个空的 table
local tbl1 = {}

-- 直接初始表
local tbl2 = { "apple", "pear", "orange", "grape" }

print("------------table------------")
-- Lua 中的表（table）其实是一个"关联数组"（associative arrays），数组的索引可以是数字或者是字符串
a = {}
a["key"] = "value"
key = 10
a[key] = 22
a[key] = a[key] + 11
for k, v in pairs(a) do
    print(k .. " : " .. v)
end

print("------------table------------")
-- 不同于其他语言的数组把 0 作为数组的初始索引，在 Lua 里表的默认初始索引一般以 1 开始
local tbl = { "apple", "pear", "orange", "grape" }
for key, val in pairs(tbl) do
    print(key .. " : " .. val)
end

print("------------table------------")
-- table 不会固定长度大小，有新数据添加时 table 长度会自动增长，没初始的 table 都是 nil

a3 = {}
for i = 1, 10 do
    a3[i] = i
end
a3["key"] = "val"

for key, val in pairs(a3) do
    print(key .. " : " .. val)
end

print("------------函数------------")
-- 在 Lua 中，函数是被看作是"第一类值（First-Class Value）"，函数可以存在变量里

function factorial1(n)
    if n == 0 then
        return 1
    else
        return n * factorial1(n - 1)
    end
end
print(factorial1(5))
factorial2 = factorial1
print(factorial2(5))


print("------------匿名函数------------")

-- function 可以以匿名函数（anonymous function）的方式通过参数传递
function anonymous(tab, fun)
    for k, v in pairs(tab) do
        print(fun(k, v))
    end
end
tab = { key1 = "val1", key2 = "val2" }
anonymous(tab, function(key, val)
    return key .. " = " .. val
end)
