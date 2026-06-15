package vn.hoidanit.jobhunter.domain.response;

import vn.hoidanit.jobhunter.util.constant.GenderEnum;
import java.time.Instant;

public class ResUpdateUserDTO {
    private long id;
    private String name;
    private GenderEnum gender;
    private String Address;
    private int age;
    private Instant updatedAt;
    private CompanyUser company;

    public static class CompanyUser {
        private long id;
        private String name;

        public CompanyUser() {

        }

        public CompanyUser(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public CompanyUser getCompany() {
        return company;
    }

    public void setCompany(CompanyUser company) {
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
