package service.helper.validation;

import data.model.Client;
import data.model.Credentials;
import data.model.Manager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserDataValidationService {

  public static Client getValidatedClient(Client user,
                                          boolean isDefaultLoginProvided,
                                          boolean isDefaultPasswordProvided) {
    getValidatedCredentials(user.getCredentials(), isDefaultLoginProvided, isDefaultPasswordProvided);
    validateCommonDetails(user.getEmail(), user.getName(), user.getPhoneNumber());
    if (user.getBirthDate() != null) {
      LocalDate today = LocalDate.now();
      LocalDate birthday = user.getBirthDate().toLocalDate();
      if (ChronoUnit.YEARS.between(birthday, today) < 18) {
        throw new IllegalArgumentException("Age must be 18 or older");
      }
    }
    if (user.getDocumentNumber() != null && !user.getDocumentNumber().equals("")) {
      if (!user.getDocumentNumber().matches("[0-9]{8,15}")) {
        throw new IllegalArgumentException("Document number is invalid");
      }
    }
    return user;
  }

  public static Manager getValidatedManager(Manager user,
                                            boolean isDefaultLoginProvided,
                                            boolean isDefaultPasswordProvided) {
    getValidatedCredentials(user.getCredentials(), isDefaultLoginProvided, isDefaultPasswordProvided);
    validateCommonDetails(user.getEmail(), user.getName(), user.getPhoneNumber());
    return user;
  }

  private static void getValidatedCredentials(Credentials credentials,
                                              boolean isDefaultLoginProvided,
                                              boolean isDefaultPasswordProvided) {
    if (!isDefaultLoginProvided) {
      if (credentials.getLogin() == null || credentials.getLogin().equals("")) {
        throw new IllegalArgumentException("Login must be provided");
      } else {
        if (!credentials.getLogin().matches("[a-zA-Z0-9.\\-_]{3,30}")) {
          throw new IllegalArgumentException("Login is invalid");
        }
      }
      if (credentials.getPassword() == null  || credentials.getPassword().equals("")) {
        throw new IllegalArgumentException("Password must be provided");
      }
    }
    if (!isDefaultPasswordProvided) {
      if (!credentials.getPassword().matches("[a-zA-Z0-9.\\-_ ]{6,30}")) {
        throw new IllegalArgumentException("Password is invalid");
      }
    }
    if (credentials.getRole() == null) {
      throw new IllegalArgumentException("Role is invalid");
    }
  }

  private static void validateCommonDetails(String email, String name, String phoneNumber) {
    if (email != null && !email.equals(""))
      if (!email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
        throw new IllegalArgumentException("Email is invalid");
      }
    if (name != null && !name.equals(""))
      if (!name.matches("[a-zA-Z0-9.\\-_]{3,60}")) {
        throw new IllegalArgumentException("Name is invalid");
      }
    if (phoneNumber != null && !phoneNumber.equals(""))
      if (!phoneNumber.matches("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$")) {
        throw new IllegalArgumentException("Phone number is invalid");
      }
  }
}
