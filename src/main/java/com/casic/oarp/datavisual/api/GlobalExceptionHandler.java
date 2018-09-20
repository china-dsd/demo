package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.exception.ZXFKException;
import com.casic.oarp.datavisual.model.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 所有异常报错
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public RestResult allExceptionHandler(HttpServletRequest request,
                                          Exception exception) {
        logger.error(String.format("全局异常处理：url：%s", parseURL(request)), exception);
        if (exception instanceof ZXFKException) {
            ZXFKException e = (ZXFKException) exception;
            if ("100001".equalsIgnoreCase(e.getResultCode().getCode())) {
                RestResult restResult = new RestResult(e.getResultCode());
                restResult.setData("token=9385d137-a82f-439c-bbc6-fbb5979edf4e");
                return restResult;
            } else {
                return new RestResult(e.getResultCode());
            }
        }
        return null;
    }

    private String parseURL(HttpServletRequest request) {
        String url = request.getRequestURI() + "?";
        for (String param : request.getParameterMap().keySet()) {
            url += String.format("%s=%s&", param, request.getParameter(param));
        }
        url = url.substring(0, url.length() - 1);
        return url;
    }
}
