# springboot-demo

用来测试能否访问redis

sudo docker run --rm -v $(pwd)/springboot2.5.14.jar:/app/app.jar openjdk:8u322-jdk-bullseye java -jar /app/app.jar

结论：需要配置ssl: true + spring-boot-starter-data-redis即可正常访问。

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

但 ssl: true + spring-boot-starter-data-redis (lettuce)在 springboot2.2.10下异常(即只能使用jedis)

.   ____          _            __ _ _
/\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
\\/  ___)| |_)| | | | | || (_| |  ) ) ) )
'  |____| .__|_| |_|_| |_\__, | / / / /
=========|_|==============|___/=/_/_/_/
:: Spring Boot ::       (v2.2.10.RELEASE)

2026-01-27 03:57:37.765 INFO 1 --- [           main] com.example.demo.Application             : Starting Application
v0.0.1-SNAPSHOT on 956137b80482 with PID 1 (/app/app.jar started by root in /)
2026-01-27 03:57:37.770 INFO 1 --- [           main] com.example.demo.Application             : The following profiles
are active: test
2026-01-27 03:57:38.933 INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data
modules found, entering strict repository configuration mode!
2026-01-27 03:57:38.938 INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring
Data Redis repositories in DEFAULT mode.
2026-01-27 03:57:39.004 INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data
repository scanning in 33ms. Found 0 Redis repository interfaces.
2026-01-27 03:57:39.708 INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with
port(s): 58080 (http)
2026-01-27 03:57:40.045 INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting
service [Tomcat]
2026-01-27 03:57:40.045 INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet
engine: [Apache Tomcat/9.0.38]
2026-01-27 03:57:40.155 INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring
embedded WebApplicationContext
2026-01-27 03:57:40.155 INFO 1 --- [           main] w.s.c.ServletWebServerApplicationContext : Root
WebApplicationContext: initialization completed in 2288 ms
2026-01-27 03:57:40.836 INFO 1 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing
ExecutorService 'applicationTaskExecutor'
2026-01-27 03:57:41.324 INFO 1 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing
ExecutorService
2026-01-27 03:57:41.660 INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(
s): 58080 (http) with context path ''
2026-01-27 03:57:41.664 INFO 1 --- [           main] com.example.demo.Application             : Started Application in
4.646 seconds (JVM running for 5.225)
2026-01-27 03:57:41.667 INFO 1 --- [           main] com.example.demo.Application             : starts
2026-01-27 03:57:41.855 INFO 1 --- [           main] io.lettuce.core.EpollProvider            : Starting without
optional epoll library
2026-01-27 03:57:41.858 INFO 1 --- [           main] io.lettuce.core.KqueueProvider           : Starting without
optional kqueue library
Exception in thread "main" java.lang.reflect.InvocationTargetException
at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
at java.lang.reflect.Method.invoke(Method.java:498)
at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:48)
at org.springframework.boot.loader.Launcher.launch(Launcher.java:87)
at org.springframework.boot.loader.Launcher.launch(Launcher.java:51)
at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:52)
Caused by: org.springframework.data.redis.RedisConnectionFailureException: Redis connection failed; nested exception is
io.lettuce.core.RedisConnectionException: Unable to connect to [
RedisURI [host='clustercfg.redis-cluster-prod-remt.y437u8.euw2.cache.amazonaws.com', port=6379]]
at org.springframework.data.redis.connection.lettuce.LettuceExceptionConverter.convert(LettuceExceptionConverter.java:
66)
at org.springframework.data.redis.connection.lettuce.LettuceFutureUtils.join(LettuceFutureUtils.java:74)
at org.springframework.data.redis.connection.lettuce.LettuceConnectionProvider.getConnection(
LettuceConnectionProvider.java:53)
at
org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$ExceptionTranslatingConnectionProvider.getConnection(LettuceConnectionFactory.java:1417)
at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$SharedConnection.getNativeConnection(
LettuceConnectionFactory.java:1205)
at
org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$SharedConnection.getConnection(LettuceConnectionFactory.java:1188)
at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory.getClusterConnection(LettuceConnectionFactory.java:378)
at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory.getConnection(LettuceConnectionFactory.java:355)
at org.springframework.data.redis.core.RedisConnectionUtils.doGetConnection(RedisConnectionUtils.java:134)
at org.springframework.data.redis.core.RedisConnectionUtils.getConnection(RedisConnectionUtils.java:97)
at org.springframework.data.redis.core.RedisConnectionUtils.getConnection(RedisConnectionUtils.java:84)
at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:215)
at org.springframework.data.redis.core.RedisTemplate.execute(RedisTemplate.java:188)
at org.springframework.data.redis.core.AbstractOperations.execute(AbstractOperations.java:96)
at org.springframework.data.redis.core.DefaultValueOperations.set(DefaultValueOperations.java:256)
at org.springframework.data.redis.core.ValueOperations.set(ValueOperations.java:75)
at com.example.demo.Application.main(Application.java:20)
... 8 more
Caused by: io.lettuce.core.RedisConnectionException: Unable to connect to [RedisURI [host='clustercfg.redis-cluster-prod-remt.y437u8.euw2.cache.amazonaws.com', port=6379]]
at io.lettuce.core.RedisConnectionException.create(RedisConnectionException.java:78)
at io.lettuce.core.cluster.RedisClusterClient.lambda$transformAsyncConnectionException$32(RedisClusterClient.java:1136)
at io.lettuce.core.DefaultConnectionFuture.lambda$thenCompose$1(DefaultConnectionFuture.java:252)
at java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:774)
at java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:750)
at java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:488)
at java.util.concurrent.CompletableFuture.completeExceptionally(CompletableFuture.java:1990)
at reactor.core.publisher.MonoToCompletableFuture.onError(MonoToCompletableFuture.java:76)
at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onError(FluxMapFuseable.java:134)
at reactor.core.publisher.FluxPeekFuseable$PeekFuseableSubscriber.onError(FluxPeekFuseable.java:227)
at reactor.core.publisher.MonoFlatMap$FlatMapMain.secondError(MonoFlatMap.java:185)
at reactor.core.publisher.MonoFlatMap$FlatMapInner.onError(MonoFlatMap.java:251)
at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:100)
at reactor.core.publisher.Operators.error(Operators.java:196)
at reactor.core.publisher.MonoError.subscribe(MonoError.java:52)
at reactor.core.publisher.Mono.subscribe(Mono.java:4213)
at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:97)
at reactor.core.publisher.Operators$MonoSubscriber.onError(Operators.java:1829)
at reactor.core.publisher.MonoIgnoreThen$ThenIgnoreInner.onError(MonoIgnoreThen.java:235)
at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onError(MonoPeekTerminal.java:251)
at reactor.core.publisher.FluxPeekFuseable$PeekFuseableConditionalSubscriber.onError(FluxPeekFuseable.java:545)
at reactor.core.publisher.FluxMapFuseable$MapFuseableConditionalSubscriber.onError(FluxMapFuseable.java:326)
at reactor.core.publisher.MonoCollectList$MonoCollectListSubscriber.onError(MonoCollectList.java:106)
at io.lettuce.core.RedisPublisher$ImmediateSubscriber.onError(RedisPublisher.java:890)
at io.lettuce.core.RedisPublisher$State.onError(RedisPublisher.java:687)
at io.lettuce.core.RedisPublisher$RedisSubscription.onError(RedisPublisher.java:344)
at io.lettuce.core.RedisPublisher$SubscriptionCommand.onError(RedisPublisher.java:800)
at io.lettuce.core.RedisPublisher$SubscriptionCommand.completeExceptionally(RedisPublisher.java:794)
at io.lettuce.core.cluster.ClusterCommand.completeExceptionally(ClusterCommand.java:97)
at io.lettuce.core.protocol.RedisStateMachine.safeSet(RedisStateMachine.java:360)
at io.lettuce.core.protocol.RedisStateMachine.decode(RedisStateMachine.java:139)
at io.lettuce.core.protocol.CommandHandler.decode(CommandHandler.java:716)
at io.lettuce.core.protocol.CommandHandler.decode0(CommandHandler.java:680)
at io.lettuce.core.protocol.CommandHandler.decode(CommandHandler.java:675)
at io.lettuce.core.protocol.CommandHandler.decode(CommandHandler.java:596)
at io.lettuce.core.protocol.CommandHandler.channelRead(CommandHandler.java:565)
at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
at io.netty.handler.ssl.SslHandler.unwrap(SslHandler.java:1526)
at io.netty.handler.ssl.SslHandler.decodeJdkCompatible(SslHandler.java:1275)
at io.netty.handler.ssl.SslHandler.decode(SslHandler.java:1322)
at io.netty.handler.codec.ByteToMessageDecoder.decodeRemovalReentryProtection(ByteToMessageDecoder.java:501)
at io.netty.handler.codec.ByteToMessageDecoder.callDecode(ByteToMessageDecoder.java:440)
at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:276)
at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1410)
at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:919)
at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:166)
at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:714)
at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:650)
at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:576)
at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:493)
at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:989)
at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
at java.lang.Thread.run(Thread.java:750)
Caused by: java.lang.UnsupportedOperationException
at java.util.AbstractList.add(AbstractList.java:148)
at java.util.AbstractList.add(AbstractList.java:108)
at io.lettuce.core.output.ArrayOutput.set(ArrayOutput.java:54)
at io.lettuce.core.protocol.RedisStateMachine.safeSet(RedisStateMachine.java:358)
... 31 more
