package com.aambre.pdfinvoice.context;

import com.aambre.pdfinvoice.service.InvoiceService;
import com.aambre.pdfinvoice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {

  /**
   * InvoiceService needed to call Application, to get the instance of UserService.
   * It needed to know about UserService and where it can get UserService.

   * Instead of actively getting dependencies from Application class, UserService is now wired with InvoiceService

   * What id even UserService had other dependencies? More interdependent services. Then putting everything into one
   * Application class will get cumbersome.

   * THIS IS EXACTLY WHERE Spring Framework comes in: to REPLACE that Application class
   */
  public static final UserService userService = new UserService();
  public static final InvoiceService invoiceService = new InvoiceService(userService);
  public static final ObjectMapper objectMapper = new ObjectMapper();
}
