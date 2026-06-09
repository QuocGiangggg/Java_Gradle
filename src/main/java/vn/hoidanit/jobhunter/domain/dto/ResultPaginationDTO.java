package vn.hoidanit.jobhunter.domain.dto;

public class ResultPaginationDTO {
    private Meta meta;
    private Object result;

    public Meta getMeta() {
        return meta;
    }

    public Object getResult() {
        return result;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
