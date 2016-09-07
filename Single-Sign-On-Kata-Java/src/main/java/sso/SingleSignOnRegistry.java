package sso;

public interface SingleSignOnRegistry {

    SSOToken registerNewSession(String userName, String password);
    boolean is_valid(SSOToken token);
    void unregister(SSOToken token);
    
}