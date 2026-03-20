package com.aambre;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class ApplicationLauncher {
  public static void main(String[] args) throws LifecycleException {
    // Instantiate Tomcat
    Tomcat tomcat = new Tomcat();

    // set port
    tomcat.setPort(8080);

    // bootstraps Tomcat's HTTP engine
    tomcat.getConnector();

    /*
     * You can run different web applications under different paths, called "context"
     * Ex. Two different apps in the same Tomcat living under http://localhost/webapp1 & http://localhost/webapp2
     * Since we have only one app, we do not need context and that's why contextPath is "".
     * docBase is the reference to directory containing static files that Tomcat can deliver
     */
    Context ctx = tomcat.addContext("", null);

    // Add your Servlet to Tomcat
    Wrapper servlet = Tomcat.addServlet(ctx, "myFirstServlet", new MyFirstServlet());

    /*
    * Starting up Tomcat does not automatically load your Servlet. It is initialized on the very first HTTP request
    * If you want to start it immediately, you can set loanOnStartup to 1
    * */
    servlet.setLoadOnStartup(1);

    // Telling Tomcat that your Servlet should react to any request that starts with "/"
    servlet.addMapping("/");

    // Start Tomcat
    tomcat.start();
  }
}
