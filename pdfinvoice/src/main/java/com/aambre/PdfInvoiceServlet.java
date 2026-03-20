package com.aambre;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PdfInvoiceServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // Servlet will now check the request URI and decide whether to show HTML or invoices JSON
    
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
      resp.getWriter().print("[]");
    }
  }
}
