package com.zhxg.yqzj.entities.v1;

import java.util.List;
import java.util.Map;

public class QueryResult<T> {

	private List<T> resultlist; // 查询结果

	private long totalrecord;

	private List<T> clustering; // 聚类后结果

	private int nextStart;// 下一个开始位置

	private Map<String, Map<String, List<String>>> highlighting;

	public Map<String, Map<String, List<String>>> getHighlighting() {
		return highlighting;
	}

	public void setHighlighting(
			Map<String, Map<String, List<String>>> highlighting) {
		this.highlighting = highlighting;
	}

	public List<T> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}

	public long getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}

	public List<T> getClustering() {
		return clustering;
	}

	public void setClustering(List<T> clustering) {
		this.clustering = clustering;
	}

	public int getNextStart() {
		return nextStart;
	}

	public void setNextStart(int nextStart) {
		this.nextStart = nextStart;
	}
}
