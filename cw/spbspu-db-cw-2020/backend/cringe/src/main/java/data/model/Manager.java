package data.model;

import org.json.simple.JSONObject;


public class Manager {
  private int id;
  private int accountId;
  private Credentials credentials;
  private String name;
  private String phoneNumber;
  private String email;

  public Manager(
      int id,
      Credentials credentials,
      String name,
      String phoneNumber,
      String email
  ) {
    this.id = id;
    this.credentials = credentials;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public Manager(
      Credentials credentials
  ) {
    this.credentials = credentials;
  }

  public Manager(
      Credentials credentials,
      String name,
      String phoneNumber,
      String email
  ) {
    this.credentials = credentials;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public Manager(
      int id,
      int accountId,
      String name,
      String phoneNumber,
      String email
  ) {
    this.id = id;
    this.accountId = accountId;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public int getAccountId() {
    return accountId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public Credentials getCredentials() {
    return credentials;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setCredentials(Credentials credentials) {
    this.credentials = credentials;
  }

  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    if (credentials.getLogin() != null)
      jsonObject.put("login", credentials.getLogin());
    if (name != null)
      jsonObject.put("name", name);
    if (phoneNumber != null)
      jsonObject.put("phoneNumber", phoneNumber);
    if (email != null)
      jsonObject.put("email", email);
    return jsonObject;
  }
}
