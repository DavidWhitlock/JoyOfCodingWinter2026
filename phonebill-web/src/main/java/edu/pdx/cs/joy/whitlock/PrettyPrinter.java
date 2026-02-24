package edu.pdx.cs.joy.whitlock;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs.joy.PhoneBillDumper;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

public class PrettyPrinter implements PhoneBillDumper<PhoneBill> {
  private final Writer writer;

  @VisibleForTesting
  static String formatWordCount(int count )
  {
    return String.format( "Dictionary on server contains %d words", count );
  }

  @VisibleForTesting
  static String formatDictionaryEntry(String word, String definition )
  {
    return String.format("  %s -> %s", word, definition);
  }


  public PrettyPrinter(Writer writer) {
    this.writer = writer;
  }

  @Override
  public void dump(PhoneBill phoneBill) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ) {

      pw.println(phoneBill.getCustomer());

      for (PhoneCall call : phoneBill.getPhoneCalls()) {
        pw.println("  " + call.getCaller());
      }

      pw.flush();
    }

  }
}
