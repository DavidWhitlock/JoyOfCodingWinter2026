package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {
  private final String customerName;
  private final Collection<PhoneCall> calls = new ArrayList<>();

  public PhoneBill(String customerName) {

    this.customerName = customerName;
  }

  @Override
  public String getCustomer() {
    return this.customerName;
  }

  @Override
  public void addPhoneCall(PhoneCall call) {
    this.calls.add(call);
  }

  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    return this.calls;
  }
}
