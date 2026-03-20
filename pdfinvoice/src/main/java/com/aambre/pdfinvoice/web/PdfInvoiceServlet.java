package com.aambre.pdfinvoice.web;

import com.aambre.pdfinvoice.context.Application;
import com.aambre.pdfinvoice.model.Invoice;
import com.aambre.pdfinvoice.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class PdfInvoiceServlet extends HttpServlet {

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
      List<Invoice> invoices = Application.invoiceService.findAll();
      resp.getWriter().print(Application.objectMapper.writeValueAsString(invoices));
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if(req.getRequestURI().equalsIgnoreCase("/invoices")) {

      String userId = req.getParameter("userId");
      Integer amount = Integer.valueOf(req.getParameter("amount"));

      Invoice invoice = Application.invoiceService.create(userId, amount);

      resp.setContentType("application/json; charset=UTF-8");
      String json = Application.objectMapper.writeValueAsString(invoice);
      resp.getWriter().print(json);
    } else {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }
}
