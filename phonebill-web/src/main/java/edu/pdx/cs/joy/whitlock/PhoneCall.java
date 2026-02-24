package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {
  private final String callerNumber;

  public PhoneCall(String callerNumber) {
    this.callerNumber = callerNumber;
  }

  @Override
  public String getCaller() {
    return this.callerNumber;
  }

  @Override
  public String getCallee() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getBeginTimeString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
