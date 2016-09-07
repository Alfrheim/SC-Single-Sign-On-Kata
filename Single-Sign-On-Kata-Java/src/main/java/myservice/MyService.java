package myservice;


import sso.Request;
import sso.Response;
import sso.SSOToken;
import sso.SingleSignOnRegistry;

public class MyService {
    
    private SingleSignOnRegistry registry;
    
    public MyService(SingleSignOnRegistry registry) {
        this.registry = registry;
    }
    
    public Response handleRequest(Request request) {
        SSOToken ssoToken = request.getSSOToken();
        if(registry == null)
            return new Response("Registry not initialized");
        if(request.getSSOToken() == null)
            return new Response("Request contains no token");
        if(!registry.is_valid(ssoToken))
            return new Response("Unauthorized token");

        return new Response("hello " + request.getName() + "!");
    }
}
