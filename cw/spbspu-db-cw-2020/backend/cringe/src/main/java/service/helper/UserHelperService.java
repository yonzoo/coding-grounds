package service.helper;

import at.favre.lib.crypto.bcrypt.BCrypt;
import data.model.Credentials;

public class UserHelperService {
  public static Credentials withHashedPassword(Credentials credentials) {
    if (credentials.getPassword() == null) return credentials;
    String hashString = BCrypt.withDefaults().hashToString(12, credentials.getPassword().toCharArray());
    credentials.setPassword(hashString);
    return credentials;
  }
}
