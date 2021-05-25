---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by westboy.
--- DateTime: 2021/3/31 3:56 下午
---

--[[ 函数返回两个值的最大值 --]]
function max(num1, num2)
    if (num1 > num2) then
        result = num1;
    else
        result = num2;
    end
    return result;
end
-- 调用函数
print("两值比较最大值为 ", max(10, 4))
print("两值比较最大值为 ", max(5, 6))


-- myPrint 函数作为参数传递
print("--------------函数作为参数传递----------------")

myPrint = function(param)
    print("这是打印函数", "##", param, "##")
end

function add(num1, num2, functionPrint)
    result = num1 + num2
    functionPrint(result)
end

myPrint(10)

add(2, 5, myPrint)

print("--------------多返回值----------------")

function maximum (a)
    local maxIndex = 1             -- 最大值索引
    local max = a[maxIndex]        -- 最大值
    for i, val in ipairs(a) do
        if val > max then
            maxIndex = i
            max = val
        end
    end
    return max, maxIndex
end

print(maximum({ 8, 10, 23, 12, 5 }))

print("--------------可变参数----------------")

function average(...)
    result = 0
    local arg = { ... }
    for i, v in ipairs(arg) do
        result = result + v
    end
    print("总共传入 " .. #arg .. " 个数")
    return result / #arg
end

print("平均值为", average(10, 5, 3, 4, 5, 6))

