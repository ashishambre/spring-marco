package com.aambre.pdfinvoice.web;

import com.aambre.pdfinvoice.context.PdfInvoiceApplicationConfiguration;
import com.aambre.pdfinvoice.model.Invoice;
import com.aambre.pdfinvoice.service.InvoiceService;
import com.aambre.pdfinvoice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class PdfInvoiceServlet extends HttpServlet {

  private UserService userService;
  private InvoiceService invoiceService;
  private ObjectMapper objectMapper;

  /**
   * Every HTTPServlet has an init() method which you can override to do something whenever the servlet gets started.

   * Since we used annotation based configuration -> AnnotationConfigApplicationContext
   * If we use Spring XML configuration -> ClasspathXMLApplicationContext

   * @throws ServletException
   */
  @Override
  public void init() throws ServletException {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PdfInvoiceApplicationConfiguration.class);

    this.userService = ctx.getBean(UserService.class);
    this.invoiceService = ctx.getBean(InvoiceService.class);
    this.objectMapper = ctx.getBean(ObjectMapper.class);

    /**
     * Spring reads in your @Configuration class and is smart enough to construct your @beans/services,
     * that it can give back to you whenever you call ctx.getBean(someClass).
     */
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    if(req.getRequestURI().equalsIgnoreCase("/")) {
      resp.setContentType("text/html; charset=UTF-8");
      resp.getWriter().print(
              "<html>\n" +
                      "<body>\n" +
                      "<h1>Hello World</h1>\n" +
                      "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
                      "</body>\n" +
                      "</html>" );
    } else if(req.getRequestURI().equalsIgnoreCase("/invoices")) {
      resp.setContentType("application/json; charset=UTF-8");
      List<Invoice> invoices = invoiceService.findAll();
      resp.getWriter().print(objectMapper.writeValueAsString(invoices));
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if(req.getRequestURI().equalsIgnoreCase("/invoices")) {

      String userId = req.getParameter("userId");
      Integer amount = Integer.valueOf(req.getParameter("amount"));

      Invoice invoice = invoiceService.create(userId, amount);

      resp.setContentType("application/json; charset=UTF-8");
      String json = objectMapper.writeValueAsString(invoice);
      resp.getWriter().print(json);
    } else {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }
}
