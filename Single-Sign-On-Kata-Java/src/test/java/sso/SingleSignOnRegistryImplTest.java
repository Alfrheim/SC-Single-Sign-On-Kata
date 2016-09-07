package sso;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by Pau on 5/09/16.
 */
public class SingleSignOnRegistryImplTest {

	@Test
	public void registerNewSessionSuccess() throws Exception {

		String username = "user";
		String password = "123";

		AuthenticationGateway authenticationGatewayMock = Mockito.mock(AuthenticationGateway.class);
		Mockito.when(authenticationGatewayMock.credentialsAreValid(username,password)).thenReturn(true);

		SingleSignOnRegistry singleSignOnRegistry = new SingleSignOnRegistryImpl(authenticationGatewayMock);

		SSOToken ssoToken = singleSignOnRegistry.registerNewSession(username, password);

		assertNotNull(ssoToken);
	}

	@Test
	public void registerNewSessionFailure() throws Exception {

		String username = "user2";
		String password = "123";

		AuthenticationGateway authenticationGatewayMock = Mockito.mock(AuthenticationGateway.class);
		Mockito.when(authenticationGatewayMock.credentialsAreValid(username,password)).thenReturn(false);

		SingleSignOnRegistry singleSignOnRegistry = new SingleSignOnRegistryImpl(authenticationGatewayMock);
		SSOToken ssoToken = singleSignOnRegistry.registerNewSession(username, password);

		assertNull(ssoToken);
	}

	@Test
	public void registerNewSessionUserPassNull() throws Exception {

		String username = null;
		String password = null;

		AuthenticationGateway authenticationGatewayMock = Mockito.mock(AuthenticationGateway.class);

		SingleSignOnRegistry singleSignOnRegistry = new SingleSignOnRegistryImpl(authenticationGatewayMock);
		SSOToken ssoToken = singleSignOnRegistry.registerNewSession(username, password);

		assertNull(ssoToken);
		Mockito.verify(authenticationGatewayMock,Mockito.never()).credentialsAreValid(username,password);
	}


}