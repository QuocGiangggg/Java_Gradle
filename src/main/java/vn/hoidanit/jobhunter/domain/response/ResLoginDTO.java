package vn.hoidanit.jobhunter.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import vn.hoidanit.jobhunter.domain.Role;

public class ResLoginDTO {

    @JsonProperty("access_token")
    private String accessToken;
    private UserLogin user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserLogin getUser() {
        return user;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

    public static class UserLogin {
        private long id;
        private String email;
        private String name;
        private Role role;

        public UserLogin() {
        }

        public UserLogin(long id, String email, String name, Role role) {
            this.id = id;
            this.email = email;
            this.name = name;
            this.role = role;
        }

        public long getId() {
            return id;

        }

        public void setId(long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }
    }

    public static class UserGetAccount {
        private UserLogin user;

        public UserLogin getUser() {
            return user;
        }

        public void setUser(UserLogin user) {
            this.user = user;
        }

        public UserGetAccount(UserLogin user) {
            this.user = user;
        }

        public UserGetAccount() {

        }
    }

    public static class UserInsideToken {
        private long id;
        private String name;
        private String email;

        public UserInsideToken() {

        }

        public UserInsideToken(long id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
