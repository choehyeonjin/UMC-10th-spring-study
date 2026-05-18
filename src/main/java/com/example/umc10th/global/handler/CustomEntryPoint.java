package com.example.umc10th.global.handler;

import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.security.util.HttpResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        HttpResponseUtil.setErrorResponse(response, GeneralErrorCode.UNAUTHORIZED);    }
}
