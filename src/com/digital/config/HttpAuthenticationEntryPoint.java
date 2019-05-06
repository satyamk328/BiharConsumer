package com.digital.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HttpAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
            final AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final PrintWriter writer = response.getWriter();

        RestStatus<?> status = new RestStatus<>(HttpStatus.UNAUTHORIZED.toString(),
				"Your Session has expired. Please log in again");
        
        final RestResponse<String> responseObj = new RestResponse<String>(null,status);
        final String result = mapper.writeValueAsString(responseObj);
        writer.write(result);
        writer.flush();
    }

}