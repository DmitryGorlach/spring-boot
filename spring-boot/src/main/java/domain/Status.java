package domain;

import org.springframework.security.core.GrantedAuthority;

public enum Status implements GrantedAuthority {
	PERFORM, REFUSE;

	@Override
	public String getAuthority() {
		return null;
	}
}
