---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by westboy.
--- DateTime: 2021/3/31 4:17 下午
---


-- 初始化表
mytable = {}

-- 指定值
mytable[1] = "Lua"

-- 移除引用
mytable = nil
-- lua 垃圾回收会释放内存


-- 简单的 table
mytable = {}
print("mytable 的类型是 ", type(mytable))

mytable[1] = "Lua"
mytable["wow"] = "修改前"
print("mytable 索引为 1 的元素是 ", mytable[1])
print("mytable 索引为 wow 的元素是 ", mytable["wow"])

-- alternatetable和mytable的是指同一个 table
alternatetable = mytable

print("alternatetable 索引为 1 的元素是 ", alternatetable[1])
print("mytable 索引为 wow 的元素是 ", alternatetable["wow"])

alternatetable["wow"] = "修改后"

print("mytable 索引为 wow 的元素是 ", mytable["wow"])

-- 释放变量
alternatetable = nil
print("alternatetable 是 ", alternatetable)

-- mytable 仍然可以访问
print("mytable 索引为 wow 的元素是 ", mytable["wow"])

mytable = nil
print("mytable 是 ", mytable)

print("----------table 连接-------------")

fruits = { "banana", "orange", "apple" }
-- 返回 table 连接后的字符串
print("连接后的字符串 ", table.concat(fruits))

-- 指定连接字符
print("连接后的字符串 ", table.concat(fruits, ", "))

-- 指定索引来连接 table
print("连接后的字符串 ", table.concat(fruits, ", ", 2, 3))

print("----------table 插入和移除-------------")

fruits = { "banana", "orange", "apple" }

-- 在末尾插入
table.insert(fruits, "mango")
print("索引为 4 的元素为 ", fruits[4], "当前数组长度", #fruits)

-- 在索引为 2 的键处插入
table.insert(fruits, 2, "grapes")
print("索引为 2 的元素为 ", fruits[2], "当前数组长度", #fruits)

print("最后一个元素为 ", fruits[5], "当前数组长度", #fruits)
table.remove(fruits)
print("移除后最后一个元素为 ", fruits[5], "当前数组长度", #fruits)

print("----------table 排序-------------")

fruits = { "banana", "orange", "apple", "grapes" }
print("排序前")
for k, v in ipairs(fruits) do
    print(k, v)
end

table.sort(fruits)
print("排序后")
for k, v in ipairs(fruits) do
    print(k, v)
end

print("----------table 最大值-------------")

function table_maxn(t)
    local mn = 0
    for k, v in pairs(t) do
        if k > mn then
            mn = k
        end
    end
    return mn
end
tbl = { [1] = "a", [2] = "b", [3] = "c", [26] = "z" }
print("tbl 长度 ", #tbl)
print("tbl 最大值 ", table_maxn(tbl))