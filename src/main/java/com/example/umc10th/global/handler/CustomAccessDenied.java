package com.example.umc10th.global.handler;

import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.security.util.HttpResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDenied implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {

        HttpResponseUtil.setErrorResponse(response, GeneralErrorCode.FORBIDDEN);    }
}