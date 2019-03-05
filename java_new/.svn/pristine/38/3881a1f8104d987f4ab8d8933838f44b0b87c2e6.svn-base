package com.zhxg.yqzj.entities.v1;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhxg.weibospread.WeiboBean;

public class WeiboEntity extends WeiboBean {

	private String allPeople;

	private List<WeiboEntity> childrens = new ArrayList<WeiboEntity>();

	public List<WeiboEntity> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<WeiboEntity> childrens) {
		this.childrens = childrens;
	}
	
	private String authorId;
	
	private String kn_abstract;
	
	private String url;
	
	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getKn_abstract() {
        return kn_abstract;
    }

    public void setKn_abstract(String kn_abstract) {
        this.kn_abstract = kn_abstract;
    }

    public String getAllPeople() {
		return allPeople;
	}

	public void setAllPeople(String allPeople) {
		this.allPeople = allPeople;
		JSONArray array = null;
		try {
			array = JSON.parseArray(allPeople);
		} catch (Exception e) {
			array = JSON.parseArray("[]");
		}
		ArrayList<String> list = new ArrayList(array);
		setAll_people(list);
	}

}
