package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.exception.ZXFKException;
import com.casic.oarp.datavisual.model.ResultCode;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class APIUtils {

    public static Date[] parseDate(String startDate, String endDate) throws ZXFKException {
        Date oStartDate = null;
        Date oEndDate = null;
        try {
            startDate = startDate.trim();
            endDate = endDate.trim();
            if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
                oEndDate = ZXFKUtils.nowDate();
                oStartDate = ZXFKUtils.calcDateByNowDate(oEndDate, 0, -1, 0);
            } else {
                oStartDate = new SimpleDateFormat(ZXFKUtils.DATE_PATTERN).parse(startDate);
                oEndDate = new SimpleDateFormat(ZXFKUtils.DATE_PATTERN).parse(endDate);
            }
        } catch (ParseException e) {
            throw new ZXFKException(ResultCode.INVALID_PARAMS);
        }
        return new Date[]{oStartDate, oEndDate};
    }
}
