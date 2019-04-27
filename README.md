# Vertx OpenSSL Testing

To compile: `./gradlew shadowJar`

To run: `java -jar build/libs/VertxOpenSslTest-all.jar`

Comment and un-comment the tcnative library to test this.

When running on java 1.8, with only the `vertx-core` dependency I get the following error:

```
SEVERE: ALPN not available for JDK SSL/TLS engine
io.vertx.core.VertxException: ALPN not available for JDK SSL/TLS engine
	at io.vertx.core.net.impl.SSLHelper.resolveEngineOptions(SSLHelper.java:88)
	at io.vertx.core.net.impl.SSLHelper.<init>(SSLHelper.java:165)
	at io.vertx.core.http.impl.HttpServerImpl.<init>(HttpServerImpl.java:109)
	at io.vertx.core.impl.VertxImpl.createHttpServer(VertxImpl.java:301)
	at Server.start(Server.java:27)
	at io.vertx.core.impl.DeploymentManager.lambda$doDeploy$8(DeploymentManager.java:552)
	at io.vertx.core.impl.DeploymentManager$$Lambda$27/504527234.handle(Unknown Source)
	at io.vertx.core.impl.ContextImpl.executeTask(ContextImpl.java:320)
	at io.vertx.core.impl.EventLoopContext.lambda$executeAsync$0(EventLoopContext.java:38)
	at io.vertx.core.impl.EventLoopContext$$Lambda$28/836514715.run(Unknown Source)
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163)
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:404)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:462)
	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:897)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:744)
```

When enabling the `netty-tcnative-boringssl-static` library, no errors are generated and the server works.