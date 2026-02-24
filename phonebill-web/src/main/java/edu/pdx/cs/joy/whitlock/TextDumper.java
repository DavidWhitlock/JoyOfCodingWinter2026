package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.PhoneBillDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class TextDumper implements PhoneBillDumper<PhoneBill> {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  public void dump(Map<String, String> dictionary) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ){
      for (Map.Entry<String, String> entry : dictionary.entrySet()) {
        pw.println(entry.getKey() + " : " + entry.getValue());
      }

      pw.flush();
    }
  }

  @Override
  public void dump(PhoneBill bill) throws IOException {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ){
      pw.println(bill.getCustomer());
      bill.getPhoneCalls().forEach(phoneCall -> {
        pw.println(phoneCall.getCaller());
      });
      pw.flush();
    }

  }

}
