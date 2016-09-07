package myservice;

import static org.junit.Assert.*;

import org.junit.Test;

import org.mockito.Mockito;
import sso.Request;
import sso.Response;
import sso.SSOToken;
import sso.SingleSignOnRegistry;

public class MyServiceTest {

    @Test
    public void registryNotInitialized() {
        MyService service = new MyService(null);
        Response response = service.handleRequest(new Request("Foo", null));
        assertNotEquals("hello Foo!", response.getText());
    }

    @Test
    public void tokenIsNull() {
        SingleSignOnRegistry singleSignOnRegistryMock = Mockito.mock(SingleSignOnRegistry.class);

        MyService service = new MyService(singleSignOnRegistryMock);

        Response response = service.handleRequest(new Request("Foo", null));
        assertEquals("Request contains no token", response.getText());
    }

    @Test
    public void tokenIsNotValid() {
        SSOToken ssoTokenMock = Mockito.mock(SSOToken.class);

        SingleSignOnRegistry singleSignOnRegistryMock = Mockito.mock(SingleSignOnRegistry.class);
        Mockito.when(singleSignOnRegistryMock.is_valid(ssoTokenMock)).thenReturn(false);

        MyService service = new MyService(singleSignOnRegistryMock);
        Response response = service.handleRequest(new Request("Foo", ssoTokenMock));

        assertEquals("Unauthorized token", response.getText());
    }

}
