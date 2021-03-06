package com.casic.oarp.datavisual.mapper;

import com.casic.oarp.datavisual.po.Shenjfxwtbajzggzqkb;
import com.casic.oarp.datavisual.po.ShenjfxwtbajzggzqkbExample;
import com.casic.oarp.datavisual.po.ShenjfxwtbajzggzqkbWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShenjfxwtbajzggzqkbMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    long countByExample(ShenjfxwtbajzggzqkbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int deleteByExample(ShenjfxwtbajzggzqkbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int deleteByPrimaryKey(String zId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int insert(ShenjfxwtbajzggzqkbWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int insertSelective(ShenjfxwtbajzggzqkbWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    List<ShenjfxwtbajzggzqkbWithBLOBs> selectByExampleWithBLOBs(ShenjfxwtbajzggzqkbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    List<Shenjfxwtbajzggzqkb> selectByExample(ShenjfxwtbajzggzqkbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    ShenjfxwtbajzggzqkbWithBLOBs selectByPrimaryKey(String zId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int updateByExampleSelective(@Param("record") ShenjfxwtbajzggzqkbWithBLOBs record, @Param("example") ShenjfxwtbajzggzqkbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") ShenjfxwtbajzggzqkbWithBLOBs record, @Param("example") ShenjfxwtbajzggzqkbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int updateByExample(@Param("record") Shenjfxwtbajzggzqkb record, @Param("example") ShenjfxwtbajzggzqkbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int updateByPrimaryKeySelective(ShenjfxwtbajzggzqkbWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(ShenjfxwtbajzggzqkbWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table y_shenjfxwtbajzggzqkb
     *
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    int updateByPrimaryKey(Shenjfxwtbajzggzqkb record);
}