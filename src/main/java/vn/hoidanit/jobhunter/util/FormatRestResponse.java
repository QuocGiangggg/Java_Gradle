package vn.hoidanit.jobhunter.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import jakarta.servlet.http.HttpServletResponse;
import vn.hoidanit.jobhunter.controller.AuthController;
import vn.hoidanit.jobhunter.domain.RestResponse;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {
    private final AuthController authController;

    FormatRestResponse(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true; // Áp dụng cho tất cả các controller
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        // if (body instanceof String) {
        // return body;
        // }
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int statusCode = servletResponse.getStatus();

        RestResponse<Object> res = new RestResponse<Object>();
        // case error
        res.setStatuscode(statusCode);

        if (body instanceof String) {
            return body;
        }

        if (statusCode >= 400) {
            return body;
        } else {
            // case success
            res.setData(body);
            res.setMessage("success");
        }
        return res;
    }
}
