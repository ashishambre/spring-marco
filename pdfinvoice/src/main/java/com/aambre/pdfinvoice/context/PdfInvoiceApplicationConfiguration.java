package com.aambre.pdfinvoice.context;

import com.aambre.pdfinvoice.service.InvoiceService;
import com.aambre.pdfinvoice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PdfInvoiceApplicationConfiguration {

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
