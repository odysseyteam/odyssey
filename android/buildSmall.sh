# gradle 打包脚本

THIS_DIR=$(dirname "$0")
pushd "$THIS_DIR"

# 1. 清除公共库：
./gradlew cleanLib -q
echo "清除公共库完成"

# 2. 清除业务单元：
./gradlew cleanBundle -q
echo "清除业务单元完成"

# 读取第二个参数打包 例：./buildPlugin x86 | armeabi
echo "将要编译的类型：$1"

# 3. 编译公共库：
# 4. 编译业务单元：
if [ $1 = "x86" -o $1 = "armeabi" ]
then
  ./gradlew buildLib -q -Dbundle.arch=$1
  echo "编译公共库完成"
  ./gradlew buildBundle -q -Dbundle.arch=$1
  echo "编译业务单元完成"
else
  ./gradlew buildLib -q -Dbundle.arch=armeabi
  ./gradlew buildLib -q -Dbundle.arch=x86
  echo "编译公共库完成"
  ./gradlew buildBundle -q -Dbundle.arch=x86
  ./gradlew buildBundle -q -Dbundle.arch=armeabi
  echo "编译业务单元完成"
fi

popd