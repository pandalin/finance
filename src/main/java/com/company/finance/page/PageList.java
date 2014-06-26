package com.company.finance.page;


/**
 * 分页类
 * @author lin
 * @param <T>
 */
public class PageList {

	private final int page;
	private final int size;

	public PageList(int page, int size) {

		if (page < 0) {
			throw new IllegalArgumentException("Page index must not be less than zero!");
		}

		if (size < 0) {
			throw new IllegalArgumentException("Page size must not be less than zero!");
		}

		this.page = page;
		this.size = size;
	}

	public int getPageSize() {

		return size;
	}

	public int getPageNumber() {
		return page;
	}

	public int getOffset() {
		return page * size;
	}

	public boolean hasPrevious() {
		return page > 0;
	}

	public PageList next() {
		return new PageList(page + 1, size);
	}

	public PageList previousOrFirst() {
		return hasPrevious() ? new PageList(page - 1, size) : this;
	}

	public PageList first() {
		return new PageList(0, size);
	}

	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Page request [number: %d, size %d, sort: %s]", page, size);
	}
}
