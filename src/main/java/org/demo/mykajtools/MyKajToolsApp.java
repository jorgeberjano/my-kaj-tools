package org.demo.mykajtools;

import es.jbp.kajtools.EnvironmentConfiguration;
import es.jbp.kajtools.KajToolsApp;
import es.jbp.kajtools.TestProducer;
import es.jbp.kajtools.util.SchemaRegistryService;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = { "es.jbp.kajtools", "es.eci.firefly.test.producer" })
public class MyKajToolsApp extends KajToolsApp {

  public MyKajToolsApp(@Autowired List<TestProducer> producerList,
      @Autowired SchemaRegistryService schemaRegistryService) {
    super(producerList, schemaRegistryService);
  }

  public static void main(String[] args) {

    try {
      EnvironmentConfiguration.loadEnvironmentConfig();
    } catch (IOException e) {
      System.err.println("Failed to load environment configuration");
    }

    ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MyKajToolsApp.class)
        .web(WebApplicationType.NONE)
        .headless(false).run(args);

    EventQueue.invokeLater(() -> {
      KajToolsApp kajToolsApp = ctx.getBean(KajToolsApp.class);
      kajToolsApp.showWindow(args);
    });
  }

}
