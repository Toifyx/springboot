# spring boot
a demon spring boot project

# 一小时 250MB
* 75 char , 1000 ms
* 100 char , 1000 ms
* 125 char , 1000 ms
* 200 char , 1000 ms






# 设置打印笔数 (默认100)
http://localhost:9991/setMaxCountNum/{maxCountNum}

# 设置并发打印数 (默认25)
http://localhost:9991/setMaxThreadNum/{maxThreadNum}

# 添加 （）
http://localhost:9991/initSchedule/{maxCharLen}/{delay}

# 取消 (停止打印)
http://localhost:9991/cancelSchedule

# increase ( )
http://localhost:9991/increase/{times}