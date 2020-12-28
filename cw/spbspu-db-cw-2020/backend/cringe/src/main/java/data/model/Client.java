package data.model;

import org.json.simple.JSONObject;

import java.sql.Date;

public class Client {
  private int id;
  private int accountId;
  private Credentials credentials;
  private String name;
  private Date birthDate;
  private String phoneNumber;
  private String email;
  private String documentNumber;

  public Client(
      int id,
      Credentials credentials,
      String name,
      Date birthDate,
      String phoneNumber,
      String email,
      String documentNumber
  ) {
    this.id = id;
    this.credentials = credentials;
    this.name = name;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.documentNumber = documentNumber;
  }

  public Client(
      Credentials credentials,
      String name,
      Date birthDate,
      String phoneNumber,
      String email,
      String documentNumber
  ) {
    this.credentials = credentials;
    this.name = name;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.documentNumber = documentNumber;
  }

  public Client(
      Credentials credentials
  ) {
    this.credentials = credentials;
  }

  public Client(
      int id,
      int accountId,
      String name,
      Date birthDate,
      String phoneNumber,
      String email,
      String documentNumber
  ) {
    this.id = id;
    this.accountId = accountId;
    this.name = name;
    this.birthDate = birthDate;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.documentNumber = documentNumber;
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

  public Date getBirthDate() {
    return birthDate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getDocumentNumber() {
    return documentNumber;
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

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDocumentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  public void setCredentials(Credentials credentials) {
    this.credentials = credentials;
  }

  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("clientId", id);
    if (credentials.getLogin() != null)
      jsonObject.put("login", credentials.getLogin());
    if (name != null)
      jsonObject.put("name", name);
    if (birthDate != null)
      jsonObject.put("birthDate", birthDate.toString());
    if (phoneNumber != null)
      jsonObject.put("phoneNumber", phoneNumber);
    if (email != null)
      jsonObject.put("email", email);
    if (documentNumber != null)
      jsonObject.put("documentNumber", documentNumber);
    return jsonObject;
  }
}
