package com.zhxg.yqzj.dao.impl.v1;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import com.zhxg.framework.base.curd.impl.BaseDaoImpl;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.yqzj.dao.v1.SolrDao;
import com.zhxg.yqzj.entities.v1.QueryResult;

@Repository
public class SolrDaoimpl extends BaseDaoImpl<BaseEntity> implements SolrDao {

    /*
     * solr.url=http\://beta-solr-fulltext-2.istarshine.net.cn\:8080/solr/collection/
     * solr.timeout=10000
     * solr.connection.timeout=10000
     */
    /**
     * 查询条件
     */
    private SolrQuery solrQuery;

    /**
     * 服务器new HttpSolrClient(solrUrl);
     */
    private Builder builder = new Builder(this.getUrl());

    @Override
    public String getUrl() {
        return PropertiesUtil.getValue("solr.url");
    }

    @Override
    public int getSoTimeOut() {
        return Integer.parseInt(PropertiesUtil.getValue("solr.timeout"));
    }

    @Override
    public int getConnectionTimeOut() {
        return Integer.parseInt(PropertiesUtil.getValue("solr.connection.timeout"));
    }

    @Override
    public boolean optimize() {
        return true;
    }

    @Override
    public QueryResult search(String query,String filter, String[] fields, String sortField,
            ORDER order, int pageNo, int pagesize, String searchType) throws  SysException {

        QueryResult result = new QueryResult();
        try {
            this.solrQuery = new SolrQuery();
            // 设置默认查询content
            this.solrQuery.setQuery(query);
            this.solrQuery.setParam("shards.tolerant", "true");
            solrQuery.setFilterQueries(filter);
            if("1".equals(searchType)){
                solrQuery.set("q.op", "OR");
                solrQuery.set("defType", "edismax");
                solrQuery.set("mm", "60%");
            }
            if (fields != null) {
                for (String field : fields) {
                    this.solrQuery.addField(field);
                }
            }
            this.solrQuery.setHighlight(true);
            this.solrQuery.setStart(pageNo * pagesize);
            if (pagesize != 0)
                this.solrQuery.setRows(pagesize);
            solrQuery.set("hl.fl", "kvTitle,kvContent,kvAbstract,kvSite");
            solrQuery.setHighlightSimplePre(""); 
            solrQuery.setHighlightSimplePost(""); 
            if (sortField != null && !"1".equals(searchType)){
                this.solrQuery.addSort(sortField, order);
            }
            QueryResponse response = null;
            HttpSolrClient server = builder.build();
            response = server.query(this.solrQuery, METHOD.POST);
            SolrDocumentList docs = response.getResults();
            Map<String, Map<String, List<String>>> highlights = response.getHighlighting();
            for (SolrDocument doc : docs) { 
            	if(doc.get("kvUuid") == null){
            		continue;
            	}else{
            		List<String> hlRows = highlights.get(doc.get("kvUuid")).get("kvContent");
                    if(hlRows!=null&&hlRows.size()>0){
                        doc.setField("kvAbstract", hlRows.get(0));
                    }
            	}
            }
            result.setResultlist(docs);
            result.setTotalrecord(docs.getNumFound());
        } catch (Exception e) {
            throw new SysException();
        }
        return result;
    }
    
    @Override
    public QueryResult searchAndMerge(String query,String filter, String[] fields, String sortField,
            ORDER order, int pageNo, int pagesize, String searchType) throws  SysException {

        QueryResult result = new QueryResult();
        try {
            if(StringUtils.isNotBlank(filter)){
                filter = filter+" && singleSimHash:[\"\" TO *] ";
            }else{
                filter = "singleSimHash:[\"\" TO *] ";
            }
            this.solrQuery = new SolrQuery();
            // 设置默认查询content
            this.solrQuery.setQuery(query);
            this.solrQuery.setParam("shards.tolerant", "true");
            solrQuery.setFilterQueries(filter);
            if("1".equals(searchType)){
                solrQuery.set("q.op", "OR");
                solrQuery.set("defType", "edismax");
                solrQuery.set("mm", "60%");
            }
            solrQuery.set("group", "true");
            solrQuery.set("group.sort", "kvCtime desc");
            solrQuery.set("group.field", "singleSimHash");
            solrQuery.set("group.limit", "1");
//            solrQuery.setParam("group.ngroups", "true");
            
            if (fields != null) {
                for (String field : fields) {
                    this.solrQuery.addField(field);
                }
            }
            this.solrQuery.setHighlight(true);
            this.solrQuery.setStart(pageNo * pagesize);
            if (pagesize != 0)
                this.solrQuery.setRows(pagesize);
            solrQuery.set("hl.fl", "kvTitle,kvContent,kvAbstract,kvSite");
            solrQuery.setHighlightSimplePre(""); 
            solrQuery.setHighlightSimplePost(""); 
            if (sortField != null && !"1".equals(searchType)){
                this.solrQuery.addSort(sortField, order);
            }
            QueryResponse response = null;
            SolrDocumentList list = new SolrDocumentList();
            int total = 0;
            HttpSolrClient server = builder.build();
            response = server.query(this.solrQuery, METHOD.POST);
            GroupResponse groupResponse = response.getGroupResponse();
            List<GroupCommand> listCommand = groupResponse.getValues();
            if(!listCommand.isEmpty()){
                List<Group> listGroup = listCommand.get(0).getValues();
                total = listCommand.get(0).getMatches();  //去重后总数
                for(Group group:listGroup){
                    if(group.getResult()!=null&&!group.getResult().isEmpty()){
                        group.getResult().get(0).addField("count", group.getResult().getNumFound());
                        list.addAll(group.getResult());
                    }
                    
                }
            }
            Map<String, Map<String, List<String>>> highlights = response.getHighlighting();
            for (SolrDocument doc : list) { 
                List<String> hlRows = highlights.get(doc.get("kvUuid")).get("kvContent");
                if(hlRows!=null&&hlRows.size()>0){
                    doc.setField("kvAbstract", hlRows.get(0));
                }
            }
            result.setResultlist(list);
            result.setTotalrecord(total);
        } catch (Exception e) {
            throw new SysException();
        }
        return result;
    }
}
