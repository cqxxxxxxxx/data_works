#!/bin/sh
set -e

ls

#堆大小设置
JVM_HEAP=' -Xms1g -Xmx1g -Xss512k -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/ '

#设置方法区大小 prometheus查看使用一般在90-150之间
JVM_METASPACE=' -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m '

#gc日志设置
JVM_LOG=' -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/tmp/gc.log '

#DEBUG设置
JVM_DEBUG=' -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=20021 '

#加速随机数生成， 打印启动参数（包括隐式启用的）， 禁止异常栈的忽略(默认在同一个地方抛出了N次相同异常后就不会打印完整的栈信息)
JVM_COMMON=' -Djava.security.egd=file:/dev/./urandom -XX:+PrintCommandLineFlags -XX:-OmitStackTraceInFastThrow '

#CMS垃圾收集器 ParNew/CMS/serialOld组合 (之前没设置一直默认用的Parallel Scavenge/Parrallel Old的组合)
#老年代中内存使用了75%时触发CMS进行GC,避免使用SerialOld； UseCMSInitiatingOccupancyOnly不让JVM进行自动调节。
JVM_GC=' -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly '

if [ "$JVM_DEBUG_ENABLE" == 'true' ]; then
  JAVA_OPTS="$JVM_COMMON $JVM_HEAP $JVM_METASPACE $JVM_LOG $JVM_GC $JVM_DEBUG"
else
  JAVA_OPTS="$JVM_COMMON $JVM_HEAP $JVM_METASPACE $JVM_LOG $JVM_GC"
fi
### 启动服务
java -Dspring.profiles.active=$SPRING_PROFILE_ACTIVE -jar ./app.jar
