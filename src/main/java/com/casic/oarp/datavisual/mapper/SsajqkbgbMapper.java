package com.casic.oarp.datavisual.mapper;

import com.casic.oarp.datavisual.po.Ssajqkbgb;
import com.casic.oarp.datavisual.po.SsajqkbgbExample;
import com.casic.oarp.datavisual.po.SsajqkbgbWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SsajqkbgbMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    long countByExample(SsajqkbgbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int deleteByExample(SsajqkbgbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int deleteByPrimaryKey(String zId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int insert(SsajqkbgbWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int insertSelective(SsajqkbgbWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    List<SsajqkbgbWithBLOBs> selectByExampleWithBLOBs(SsajqkbgbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    List<Ssajqkbgb> selectByExample(SsajqkbgbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    SsajqkbgbWithBLOBs selectByPrimaryKey(String zId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int updateByExampleSelective(@Param("record") SsajqkbgbWithBLOBs record, @Param("example") SsajqkbgbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") SsajqkbgbWithBLOBs record, @Param("example") SsajqkbgbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int updateByExample(@Param("record") Ssajqkbgb record, @Param("example") SsajqkbgbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int updateByPrimaryKeySelective(SsajqkbgbWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(SsajqkbgbWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_ssajqkbgb
     *
     * @mbg.generated Mon Jul 09 13:22:16 CST 2018
     */
    int updateByPrimaryKey(Ssajqkbgb record);
}