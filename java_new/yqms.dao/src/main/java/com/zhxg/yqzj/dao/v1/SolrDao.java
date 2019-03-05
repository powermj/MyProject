package com.zhxg.yqzj.dao.v1;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.yqzj.entities.v1.QueryResult;

public interface SolrDao extends BaseDao<BaseEntity> {

    /**
     * 获取solr服务器
     * 
     * @param url
     */
    public String getUrl();

    /**
     * 获取连接超时信息
     * 
     * @return
     */
    public int getSoTimeOut();

    /**
     * 获取连接超时信息
     * 
     * @return
     */
    public int getConnectionTimeOut();

    /**
     * 是否优化索引
     * 
     * @return
     */
    public boolean optimize();

    /**
     * 普通查询
     * 
     * @param query
     *            查询条件
     * @param string 
     * @param field
     *            返回字段
     * @param sortField
     *            排序字段
     * @param order
     *            排序方式
     * @param pageNo
     *            起始数
     * @param pagesize
     *            查询条数
     * @param searchType 
     * @return
     * @throws IOException 
     * @throws SolrServerException 
     * @throws SolrException 
     * @throws SysException 
     */
    public QueryResult search(String query,String filter,  String[] fields, String sortField,
            ORDER order, int pageNo, int pagesize, String searchType) throws SysException;

    public QueryResult searchAndMerge(String query, String filter, String[] fields, String sortField, 
            ORDER order, int pageNo, int pagesize, String searchType) throws SysException;

}
