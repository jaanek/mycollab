package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.MetaData;
import com.esofthead.mycollab.module.tracker.domain.MetaDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MetaDataMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int countByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int deleteByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int insert(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int insertSelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    List<MetaData> selectByExampleWithBLOBs(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    List<MetaData> selectByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    MetaData selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByExampleSelective(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByExampleWithBLOBs(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByExample(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    int updateByPrimaryKeySelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    Integer insertAndReturnKey(MetaData value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Sun Feb 09 16:15:23 ICT 2014
     */
    void massUpdateWithSession(@Param("record") MetaData record, @Param("primaryKeys") List primaryKeys);
}