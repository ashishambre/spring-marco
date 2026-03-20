package com.aambre.pdfinvoice.context;

import com.aambre.pdfinvoice.service.InvoiceService;
import com.aambre.pdfinvoice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {

  /**
   * Instead of creating services in each Servlet class, we moved it to a common class
   * public static final effectively makes it Singleton
   * Now any other class can access and use the same objects
   */
  public static final InvoiceService invoiceService = new InvoiceService();
  public static final ObjectMapper objectMapper = new ObjectMapper();
  public static final UserService userService = new UserService();
}
