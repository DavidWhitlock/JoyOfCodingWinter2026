package edu.pdx.cs.joy.whitlock;

import edu.pdx.cs.joy.ParserException;
import edu.pdx.cs.joy.web.HttpRequestHelper;
import edu.pdx.cs.joy.web.HttpRequestHelper.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PhoneBillRestClientTest {

  @Test
  void getPhoneBillPerformsHttpGetWithCustomerNameParameter() throws ParserException, IOException {
    String customerName = "CUSTOMER NAME";

    HttpRequestHelper http = mock(HttpRequestHelper.class);
    when(http.get(eq(Map.of(PhoneBillServlet.CUSTOMER_PARAMETER, customerName)))).thenReturn(phoneBillAsText(new PhoneBill(customerName)));

    PhoneBillRestClient client = new PhoneBillRestClient(http);

    PhoneBill phoneBill = client.getPhoneBill(customerName);
    assertThat(phoneBill, notNullValue());
    assertThat(phoneBill.getCustomer(), equalTo(customerName));
  }

  private Response phoneBillAsText(PhoneBill phoneBill) throws IOException {
    StringWriter writer = new StringWriter();
    new TextDumper(writer).dump(phoneBill);

    return new Response(writer.toString());
  }
}
