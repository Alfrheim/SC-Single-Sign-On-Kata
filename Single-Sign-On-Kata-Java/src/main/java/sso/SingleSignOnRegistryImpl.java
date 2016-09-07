package sso;

/**
 * Created by Pau on 5/09/16.
 */
public class SingleSignOnRegistryImpl implements SingleSignOnRegistry {

	private AuthenticationGateway authenticationGateway;

	public SingleSignOnRegistryImpl(AuthenticationGateway authenticationGateway) {
		this.authenticationGateway = authenticationGateway;
	}

	public SSOToken registerNewSession(String userName, String password) {
		if(userName == null || password == null)
			return null;

		if(authenticationGateway.credentialsAreValid(userName, password)) {
			SSOToken ssoToken = SSOToken.generate();
			return ssoToken;
		}
		else {
			return null;
		}
	}

	public boolean is_valid(SSOToken token) {
		return false;
	}

	public void unregister(SSOToken token) {

	}
}
