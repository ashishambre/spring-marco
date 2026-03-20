package com.aambre.pdfinvoice.context;

import com.aambre.pdfinvoice.service.InvoiceService;
import com.aambre.pdfinvoice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * We had a central class containing all services - the Application class
 * It is where we defined UserService & InvoiceService and also made sure the constructor "injection"

 * Every Spring app also consist of such a central class. Spring calls it ApplicationContext.
 * It will replace the Application class.
 */
@Configuration
public class PdfInvoiceApplicationConfiguration {

  // Spring needs a configuration class in order to construct an "ApplicationContext"
  // It DOES NOT need to implement a specific interface or extend a specific class

  /**
   * @Bean tells Spring that, on ApplicationContext startup, it should create one instance of the returned type,
   * by calling the very @Bean method.
   */

  @Bean
  public UserService userService() {
    return new UserService();
  }

  // InvoiceService has a dependency
  @Bean
  public InvoiceService invoiceService(UserService userService) {
    return new InvoiceService(userService);
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

}
