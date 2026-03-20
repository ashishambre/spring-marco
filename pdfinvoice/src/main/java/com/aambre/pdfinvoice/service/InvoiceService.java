package com.aambre.pdfinvoice.service;

import com.aambre.pdfinvoice.context.Application;
import com.aambre.pdfinvoice.model.Invoice;
import com.aambre.pdfinvoice.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

  List<Invoice> invoices = new CopyOnWriteArrayList<>();

  public List<Invoice> findAll() {
    return invoices;
  }

  public Invoice create(String userId, Integer amount) {

    User user = Application.userService.findById(userId);
    if(user == null) {
      throw new IllegalStateException("User not found");
    }

    // TODO real pdf creation and storing it on network server
    Invoice invoice = new Invoice(userId, "https://www.rd.usda.gov/sites/default/files/pdf-sample_0.pdf", amount);
    invoices.add(invoice);
    return invoice;
  }
}
