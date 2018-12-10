# spring boot
a demon spring boot project

# 一小时 250MB
* 75 char , 1000 ms
* 100 char , 1000 ms
* 125 char , 1000 ms
* 200 char , 1000 ms






# 设置打印笔数 (默认100)
http://localhost:9991/setMaxCountNum/{maxCountNum}

# 获取打印笔数 (默认100)
http://localhost:9991/getMaxCountNum

# 设置并发打印数 (默认25)
http://localhost:9991/setMaxThreadNum/{maxThreadNum}

# 获取并发打印数 (默认25)
http://localhost:9991/getMaxThreadNum


# 添加 （）
http://localhost:9991/initSchedule/{maxCharLen}/{delay}

# 获取
http://localhost:9991/getSchedule


# 取消 (停止打印)
http://localhost:9991/cancelSchedule

# 增大打印 (新增)
http://localhost:9991/increase/{times}

http://localhost:9991/setMaxThreadNum/1
http://localhost:9991/initSchedule/10/5000
http://localhost:9991/increase/2