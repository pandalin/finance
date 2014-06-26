package com.company.finance.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 分页对象,存放单页数据
 * 
 * @author lin
 * 
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final List<T> content = new ArrayList<T>();
	private final PageList pageList;
	private final long total;

	public Page(List<T> content, PageList pageList, long total) {

		if (null == content) {
			throw new IllegalArgumentException("Content must not be null!");
		}

		this.content.addAll(content);
		this.total = total;
		this.pageList = pageList;
	}

	public Page(List<T> content) {
		this(content, null, null == content ? 0 : content.size());
	}

	public int getNumber() {
		return pageList == null ? 0 : pageList.getPageNumber();
	}

	public int getSize() {
		return pageList == null ? 0 : pageList.getPageSize();
	}

	public int getTotalPages() {
		return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
	}

	public int getNumberOfElements() {
		return content.size();
	}

	public long getTotalElements() {
		return total;
	}

	public boolean hasPreviousPage() {
		return getNumber() > 0;
	}

	public boolean isFirstPage() {
		return !hasPreviousPage();
	}

	public boolean hasNextPage() {
		return getNumber() + 1 < getTotalPages();
	}

	public boolean isLastPage() {
		return !hasNextPage();
	}

	public PageList nextPageable() {
		return hasNextPage() ? pageList.next() : null;
	}

	public PageList previousPageable() {

		if (hasPreviousPage()) {
			return pageList.previousOrFirst();
		}

		return null;
	}

	public Iterator<T> iterator() {
		return content.iterator();
	}

	public List<T> getContent() {
		return Collections.unmodifiableList(content);
	}

	public boolean hasContent() {
		return !content.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String contentType = "UNKNOWN";

		if (content.size() > 0) {
			contentType = content.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
	}

}
