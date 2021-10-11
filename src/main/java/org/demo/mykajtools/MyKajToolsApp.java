package org.demo.mykajtools;

import es.jbp.kajtools.configuration.Configuration;
import es.jbp.kajtools.KajToolsApp;
import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"es.jbp.kajtools", "org.demo.mykajtools"})
public class MyKajToolsApp extends KajToolsApp {

  public MyKajToolsApp(@Autowired JFrame mainFrame) {
    super(mainFrame);
  }

  public static void main(String[] args) {

    try {
      Configuration.load();
    } catch (IOException e) {
      System.err.println("Failed to load environment configuration");
    }

    ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MyKajToolsApp.class)
        .web(WebApplicationType.NONE)
        .headless(false).run(args);

    EventQueue.invokeLater(() -> {
      KajToolsApp kajToolsApp = ctx.getBean(KajToolsApp.class);
      kajToolsApp.showWindow("My KAJ Tools");
    });
  }

}
