package com.gladfish.work.browse.form;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2018/12/27 23:40
 */
public class TestForm {

    private String name;
    private int num;
    private Integer count;

    @Override
    public String toString() {
        return "TestForm{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", count=" + count +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
