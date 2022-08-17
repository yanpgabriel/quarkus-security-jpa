package com.yanpgabriel;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.flywaydb.core.Flyway;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.sql.SQLException;

@ApplicationScoped
public class AppLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger("AppLifecycle");

    @Inject
    Flyway flyway;
    
    Server server;
    
    void onStart(@Observes StartupEvent event) throws SQLException {
        LOGGER.info("A aplicação iniciou usando o profile: {}", ProfileManager.getActiveProfile());
        
        LOGGER.info("Inicializando H2...");
        server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
        LOGGER.info("===================");

        // LOGGER.info("Inicializando BD com Flyway...");
        // flyway.clean();
        // flyway.migrate();
        // MigrationInfo current = flyway.info().current();
        // if (current != null) {
        //     LOGGER.info(String.valueOf(current.getVersion()));
        //     LOGGER.info(String.valueOf(current.getState()));
        // } else {
        //     LOGGER.info("SEM VERSÃO DE FLYWAY");
        // }
        // LOGGER.info("Flyway finalizado.");

    }
    void onStop(@Observes ShutdownEvent event) {
        server.stop();
    }
}
