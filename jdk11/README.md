# JDK11 (主要新特性)

* 集合/stream增强(ListTest.java)

* String 新增 API(StringTest.java)

* HttpClient客户端(HttpClientTest.java)

* 垃圾收集器

    * 新的 Epsilon 垃圾收集器
    
    A NoOp Garbage Collector
    
    开发处理内存分配但是不实现任何实际内存回收机制的GC，一旦可用堆内存用完，JVM就会退出
    
    如果有 `System.gc()`/`-XX:+DisableExplicitGC` 调用，实际上什么也不会发送,因为没有内存回收

    用法：`-XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC`

    ```text
    主要用途：
      性能测试（过滤GC引起的性能假象）
      内存压力测试（分配指定内存大小进行测试，如果崩溃说明可能存在内存泄露问题）
    ```
    
    * ZGC
    
    A Scalable Low-Latency Garbage Collector(Experimental)
    
    JDK11最大特性之一，后面带有Experimental还不建议生产环境使用
    
    ```text
    GC 暂停时间不会超过 10ms
    既能处理几百兆的小堆，也可以处理 TB 大堆(OMG)
    和 G1 相比，应用吞吐能力不会下降超过 15 %
    为未来的 GC 功能和利用 colord 指针以及 Load barriers 优化奠定基础
    初始只支持64位操作系统
    ```
    
    ZGC 的设计目的是：支持 TB 级内存容量，暂停时间小于(<10ms)，对整个程序吞吐影响小于 15%，
    将来还可以扩展实现机制，以支持不是令人兴奋的功能，列如：多层堆(冷热对象分离)，压缩堆

    GC 是 java 主要优势之一，而 GC 停顿太长就会影响应用的响应时间，程序员希望 JVM 能够以高效的方式
    充分利用这些内存，并且无需长时间的 GC 暂停
    
    ZGC 是一个并发，基于 region 压缩型的垃圾收集器，只有 root 扫描阶段会 STW(Stop the world) ，因此 GC 停顿时间
    不会随着堆的增长和存活对象的增长而增长
     
    用法：`-XX:+UnlockExperimentalVMOptions -XX:+UseZGC` 还处于试验阶段，所以需要通过 JVM 参数来解锁这个特性
    
    windows版本貌似不支持
    
    
* 完全支持 Linux 容器（包括Docker）

    在Docker容器中运行 java 应用程序一直存在一个问题，那就是容器中运行 jvm 程序在设置内存大小和 CPU 使用率后，
    会导致应用性能下降；这是因为 java 程序没有意识到正在容器中运行。随着 JDK10 的发布这个问题得以解决；
    
* 支持 G1 上的并行完全垃圾收集

    G1 相比于 JDK8，升级到 JDK11 即可免费享用并行 Full GC，快速的 CardTable 扫描，自适应的堆比例调整（IHOP），
    在并发标记阶段的类型卸载等等；这些都是对 G1 的增强
      
* Low-Overhead Heap Profiling 免费的低耗能飞行记录仪和堆分析

    通过 JVMTI 的 SampledObjectAlloc 回调提供了一个开销低的 heap 分析方式
    
    为了排错 java 应用问题以及 JVM 问题的数据收集框架希望达到的目标如下：
    
    * 提供用户生产和消费数据作为事件API
    * 提供缓存机制和二进制数据格式
    * 运行事件配置和事件过滤
    * 提供 OS,JVM 和 JDK 库的事件
    
* JEP 329: 实现 RFC7539 中指定的 ChaCha20 和 Poly1305 两种加密算法 代替 RC4

* 新的默认跟权限证书集

* JEP 332 最新的 HTTPS 安全协议 TLS1.3

* Java Flight Recorder

   之前是商业版的特性，在 java11 当中开源，它可以导出事件到文件然后用 `Java Mission Control` 来分析
   
   可以在启动应用时配置 `java -XX:StartFlightRecording` 或者启动后 `jcmd` 来录制
   
   ```text
   $ jcmd <pid> JFR.START
   $ jcmd <pid> JFR.dump filename=recording.jfr
   $ jcmd <pid> JFR.stop
   ```