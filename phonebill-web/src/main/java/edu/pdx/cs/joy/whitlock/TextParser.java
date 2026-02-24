package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.ParserException;
import edu.pdx.cs.joy.PhoneBillParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements PhoneBillParser<PhoneBill> {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }

  @Override
  public PhoneBill parse() throws ParserException {
    try (
      BufferedReader br = new BufferedReader(this.reader)
    ) {

      PhoneBill phoneBill = null;
      for (String line = br.readLine(); line != null; line = br.readLine()) {
        if (phoneBill == null) {
          phoneBill = new PhoneBill(line);

        } else {
          String callerNumber = line.trim();
          PhoneCall call = new PhoneCall(callerNumber);
          phoneBill.addPhoneCall(call);
        }
      }

      return phoneBill;

    } catch (IOException e) {
      throw new ParserException("While parsing PhoneBill", e);
    }
  }
}
