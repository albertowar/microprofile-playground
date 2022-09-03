
package org.example;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class Configuration {
    @Inject @ConfigProperty(name="application.name")
    private String appName;

    public String getAppName() {
      return appName;
    }
}
