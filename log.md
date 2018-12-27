# 分布式存储io测试操作手册
- 测试程序部署至西片区生产46机器。（55.144.16网段36台，其他网段10台）
- 测试程序使用spring boot编写，采用新增定时任务形式，定时启动线程写日志。并配置系统io写入参数。

# 测试目标
- 模拟生产在线交易写io操作。监控西片区系统io性能。

# 测试对照
- 统计四方坪生产机器闲时、忙时均写io量，调整测试程序系统参数，对比系统io性能
- 压力测试，调整程序io写入量，监控io写入性能。

## 启动
**进入 admlog@172.29.86.11;cd fxy;**
- `./docmd.sh 'sh start.sh'`

## 停止
- `./docmd.sh 'sh stop.sh'`

## 运行参数
- 每次打印笔数 （maxCountNum）,默认 100
- 并发数 (maxThreadNum),默认 25
- 打印长度 (maxCharLen)
- 重复打印间隔 (delay),单为毫秒
- 压力测试参数 (times),一小时内成times倍数增加打印

### 参数设置
   - 设置打印笔数
     http://localhost:9991/setMaxCountNum/{maxCountNum}
   - 获取打印笔数
     http://localhost:9991/getMaxCountNum
   - 设置并发打印数
     http://localhost:9991/setMaxThreadNum/{maxThreadNum}
   - 获取并发打印数
     http://localhost:9991/getMaxThreadNum
   - 添加打印任务
     http://localhost:9991/initSchedule/{maxCharLen}/{delay}
   - 获取打印任务
     http://localhost:9991/getSchedule
   - 停止打印任务
     
   - 增加打印压力
     http://localhost:9991/increase/{times}

## 测试默认参照参数 
   **生产单台虚拟机平均每天打印日志大小2.5G**
   **237M/Hour,5.57G/Day 3.95mb/min** 
   - 打印100char，每一秒钟打印100笔
   http://localhost:9991/initSchedule/100/1000
   - 打印125char，每一秒钟打印100笔
   http://localhost:9991/initSchedule/125/1000
   - 打印150char，每一秒钟打印100笔
   http://localhost:9991/initSchedule/150/1000
   - 打印200char，每一秒钟打印100笔
   http://localhost:9991/initSchedule/200/1000


## 测试数据
- 打印100char，每一秒钟打印100笔
    0.72 Mb/min
- 打印125char，每一秒钟打印100笔
    0.9 Mb/min
- 打印150char，每一秒钟打印100笔
    0.99 Mb/min
- 打印200char，每一秒钟打印100笔
    1.3  Mb/min

分析生产接入日志：S.LSNSVR
统计每小时日志量(文件个数:646,当日写文件总大小3.1G)：
16，12，12，24，18，12，13，20，28，31，57，40，47，39，33，32，32，30，30，30，30，30，26，19，15
上午8点日志量开始上升，10-12点达到峰值。下午1点至21点日志量相对均衡。后逐步回落。
测试程序日志可根据以上数据进行测试。
均小时写日志量：74M
忙时写日志量(9-12h)：220M
一般时间写日志量（13-21h）:150M

4.2M * 60 = 252M

-rw-r----- 1 root root   0 Dec 12 12:37 CountLogger.log
-rw-r----- 1 root root 20M Dec 17 17:32 LoggerControl.log

20M * 12 = 240M
-rw-r----- 1 root root 19M Dec 17 17:37 LoggerControl.log
-rw-r----- 1 root root 21M Dec 17 17:32 LoggerControl.log.1

21M * 10 = 210M
-rw-r----- 1 root root 19M Dec 17 17:42 LoggerControl.log
-rw-r----- 1 root root 21M Dec 17 17:37 LoggerControl.log.1
-rw-r----- 1 root root 21M Dec 17 17:32 LoggerControl.log.2