package com.aambre.pdfinvoice.service;

import com.aambre.pdfinvoice.model.Invoice;
import com.aambre.pdfinvoice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

  private UserService userService;

  List<Invoice> invoices = new CopyOnWriteArrayList<>();

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public List<Invoice> findAll() {
    return invoices;
  }

  public Invoice create(String userId, Integer amount) {

    User user = userService.findById(userId);
    if(user == null) {
      throw new IllegalStateException("User not found");
    }

    // TODO real pdf creation and storing it on network server
    Invoice invoice = new Invoice(userId, "https://www.rd.usda.gov/sites/default/files/pdf-sample_0.pdf", amount);
    invoices.add(invoice);
    return invoice;
  }

  // Added for testing Prototype Scope issue
  public UserService getUserService() {
    return userService;
  }
}
