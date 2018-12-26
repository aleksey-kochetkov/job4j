package e.model;

import java.security.Principal;

public class StubPrincipal implements Principal {
    @Override
    public String getName() {
        return "Name";
    }
}
