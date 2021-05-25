# 使用 Java 语言
namespace java com.westboy.demo08_thrift.generated
# 使用 Python 语言
namespace py py.thrift.generated


// 自定义数据类型
typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person {
    1: optional String username,
    2: optional int age,
    3: optional boolean married
}

exception DataException {
    1: optional String message,
    2: optional String callback,
    3: optional String date
}

service PersonService {
    Person getPersonByUserName(1: required String username) throws (1: DataException dataException),
    void savePerson(1:required Person person) throws (1: DataException dataException)
}

// 当前目录 java-learning
// thrift --gen java ./shengsiyuan-netty/src/main/thrift/PersonService.thrift
// 生成后在 java-learning/gen-java 将其放置到 shengsiyuan-netty 的 demo08_thrift 包中

// 使用 Python 时
// thrift --gen py ./shengsiyuan-netty/src/main/thrift/PersonService.thrift
// 生成后在 java-learning/gen-py