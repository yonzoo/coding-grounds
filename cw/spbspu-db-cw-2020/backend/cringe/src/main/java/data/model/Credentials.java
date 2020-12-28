package data.model;

public class Credentials {
  private int id;
  private String login;
  private String password;
  private Role role;

  public Credentials(String login, String password, Role role) {
    this.login = login;
    this.password = password;
    this.role = role;
  }

  public Credentials(int id, String login, String password, Role role) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
