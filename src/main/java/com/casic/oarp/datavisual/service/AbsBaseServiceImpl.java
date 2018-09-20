package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.common.ZXFKConsts;
import com.casic.oarp.datavisual.model.zxfk.SumModel;

import java.math.BigDecimal;
import java.util.*;

public class AbsBaseServiceImpl {

    /**
     * 组装sumModel对象
     *
     * @param sumMap
     * @return
     */
    protected List<SumModel> assembleSumModelList(Map sumMap) {
        List<SumModel> result = new ArrayList<>();
        if (null == sumMap || sumMap.isEmpty()) {
            return result;
        }

        Iterator<String> it = sumMap.keySet().iterator();
        SumModel sumModel = null;
        while (it.hasNext()) {
            sumModel = new SumModel();
            String key = it.next();
            sumModel.setName(key);
            sumModel.setValue(sumMap.get(key));
            result.add(sumModel);
        }
        return result;
    }

    protected class SumModelBigDecimalComparator implements Comparator<SumModel> {

        private String sort;

        public SumModelBigDecimalComparator(String sort) {
            this.sort = sort;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        @Override
        public int compare(SumModel o1, SumModel o2) {
            try {
                BigDecimal oldValue = (BigDecimal) o1.getValue();
                BigDecimal newValue = (BigDecimal) o2.getValue();
                if (oldValue == null) {
                    oldValue = new BigDecimal(0);
                }
                if (newValue == null) {
                    newValue = new BigDecimal(0);
                }
                if (ZXFKConsts.SORT_ASC.equalsIgnoreCase(sort)) {
                    return oldValue.compareTo(newValue);
                } else if (ZXFKConsts.SORT_DESC.equalsIgnoreCase(sort)) {
                    return newValue.compareTo(oldValue);
                } else {
                    return 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    protected class SumModelIntegerComparator implements Comparator<SumModel> {

        private String sort;

        public SumModelIntegerComparator(String sort) {
            this.sort = sort;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        @Override
        public int compare(SumModel o1, SumModel o2) {
            Integer oldValue = (Integer) o1.getValue();
            Integer newValue = (Integer) o2.getValue();
            if (ZXFKConsts.SORT_ASC.equalsIgnoreCase(sort)) {
                return oldValue - newValue;
            } else if (ZXFKConsts.SORT_DESC.equalsIgnoreCase(sort)) {
                return newValue - oldValue;
            } else {
                return 0;
            }
        }
    }

}
