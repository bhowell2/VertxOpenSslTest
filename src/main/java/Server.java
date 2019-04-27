import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.PemKeyCertOptions;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Blake Howell
 */
public class Server extends AbstractVerticle {

	@Override
	public void start(Future<Void> startFuture) throws Exception {

		// should pick up from fat jar
		PemKeyCertOptions pemKeyCertOptions = new PemKeyCertOptions().addCertPath("cert.pem")
		                                                             .addKeyPath("key.pem");

		HttpServerOptions serverOptions = new HttpServerOptions();
		serverOptions
				.setSsl(true)
				.setUseAlpn(true)
				.setPemKeyCertOptions(pemKeyCertOptions);

		vertx.createHttpServer(serverOptions)
		     .requestHandler(req -> {
			     System.out.println("Got a request! Using SSL = " + req.isSSL() + ". Http Version = " + req.version());
			     req.response().end("all good.");
		     })
		     .listen(443, res -> {
			     if (res.failed()) {
			     	startFuture.fail(res.cause());
			     } else {
			     	startFuture.complete();
			     }
		     });
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		super.stop(stopFuture);
	}

}
