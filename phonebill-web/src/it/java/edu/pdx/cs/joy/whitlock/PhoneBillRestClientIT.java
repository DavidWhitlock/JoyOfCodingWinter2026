package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.ParserException;
import edu.pdx.cs.joy.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration test that tests the REST calls made by {@link PhoneBillRestClient}
 */
@TestMethodOrder(MethodName.class)
class PhoneBillRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private PhoneBillRestClient newPhoneBillRestClient() {
    int port = Integer.parseInt(PORT);
    return new PhoneBillRestClient(HOSTNAME, port);
  }

  @Test
  void test0RemoveAllPhoneBills() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.removeAllPhoneBills();
  }

  @Test
  void test2CreatePhoneCall() throws IOException, ParserException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    String customeName = "CUSTOMER";
    String callerNumber = "123-456-7890";
    PhoneCall phoneCall = new PhoneCall(callerNumber);
    client.addPhoneCall(customeName, phoneCall);

    PhoneBill phoneBill = client.getPhoneBill(customeName);
    assertThat(phoneBill.getCustomer(), equalTo(customeName));
    assertThat(phoneBill.getPhoneCalls().size(), equalTo(1));
    assertThat(phoneBill.getPhoneCalls().iterator().next().getCaller(), equalTo(callerNumber));
  }

  @Test
  void test4EmptyCallerNumberThrowsException() {
    PhoneBillRestClient client = newPhoneBillRestClient();
    String emptyString = "";

    RestException ex = assertThrows(RestException.class, () -> client.addPhoneCall("CUSTOMER NAME", new PhoneCall(emptyString)));
    assertThat(ex.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
    assertThat(ex.getMessage(), containsString(Messages.missingRequiredParameter(PhoneBillServlet.CALLER_NUMBER_PARAMETER)));
  }

}
