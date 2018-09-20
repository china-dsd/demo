package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.model.RestResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/common")
@CrossOrigin
public class CommonAPI {

    public static final Date nowDate = new Date();

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public RestResult test() {
        return new RestResult(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(nowDate));
    }

}
