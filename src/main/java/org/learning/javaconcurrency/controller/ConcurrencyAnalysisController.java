package org.learning.javaconcurrency.controller;

import org.learning.javaconcurrency.reactive.ReactorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by vkasiviswanathan on 1/2/19.
 */
@RestController
public class ConcurrencyAnalysisController {

	private static final Logger LOG = LoggerFactory.getLogger(ConcurrencyAnalysisController.class);

	static {
		int cores = Runtime.getRuntime().availableProcessors();
		LOG.info("No of. cores in System : " + cores);
		LOG.info("com.sun.management.jmxremote : " + System.getProperty("com.sun.management.jmxremote"));
		LOG.info("com.sun.management.jmxremote.port : " + System.getProperty("com.sun.management.jmxremote.port"));
		LOG.info("com.sun.management.jmxremote.authenticate : "
				+ System.getProperty("com.sun.management.jmxremote.authenticate"));
		LOG.info("com.sun.management.jmxremote.ssl : " + System.getProperty("com.sun.management.jmxremote.ssl"));
		LOG.info("java.rmi.server.hostname : " + System.getProperty("java.rmi.server.hostname"));
	}

	@Autowired
	ReactorService reactorService;

	@GetMapping("/reactor")
	public Mono<String> reactor() {
		LOG.info("Analyse Reactor service ");
		return reactorService.getMono();
	}
}
