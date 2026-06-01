package vn.hoidanit.jobhunter.domain;

public class RestResponse<T> {
    private int statuscode;
    private String error;

    private Object message;
    private T data;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }
}
