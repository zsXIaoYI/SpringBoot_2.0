package cn.zsza.Page;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * Created By zhangsong
 * 18:04 2018/7/18
 */

public class PageBean<T> {
	private Integer page;        // 当前页码
	private Long totalSize;      // 总记录数
	private Integer pageSize;   // 每页记录数

	private List<T> data;

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Long getTotalSize() {
		PageInfo<T> pageInfo = new PageInfo<>(data);
		return pageInfo.getTotal();
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
