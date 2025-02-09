package by.mikhalachkin.config;

import by.mikhalachkin.entity.UserCredential;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
  private String username;
  private String password;

  public CustomUserDetails(UserCredential userCredential) {
    this.username = userCredential.getUsername();
    this.password = userCredential.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {return null;}

  @Override
  public String getPassword() {return password;}

  @Override
  public String getUsername() {return username;}
}
