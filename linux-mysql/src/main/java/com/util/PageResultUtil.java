package com.util;

/**
 * @ClassNmae PageResultUtil
 * @Discription
 * @Author wh
 * @Date 2020/10/5  21:09
 * @Version 1.0
 */

import cn.hutool.core.util.PageUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果类
 * @author Administrator
 *
 */
public class PageResultUtil implements Serializable {

    private long total;//总记录数
    private List rows;//当前页记录
    private int page;//当前页
    private int size;//页面大小
    private int totalPage;//总页数
    private int pre;//上一页
    private int next;//下一页
    private  int[] indexs;

    public int[] getIndexs() {
        return indexs;
    }

    public void setIndexs(int[] indexs) {
        this.indexs = indexs;
    }

    public PageResultUtil(){}

    public PageResultUtil(long total, List rows, int page, int size) {
        super();
        this.total = total;
        this.rows = rows;
        this.page = page;
        this.size = size;
        // 计算
        this.totalPage = (int) (total % size == 0 ? (total/size): (total/size+1));
        // 获取显示起始页码
        this.pre = page == 1 ? 1: page-1;
        this.next = page == totalPage ? totalPage:page+1;
        this.indexs = PageUtil.rainbow(page, (int)total, 7);
    }


    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPre() {
        return pre;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

}
