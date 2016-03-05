package com.reagroup.beans;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class Table {

    private int width = 5;

    private int height = 5;

    public Table() {

    }

    public Table(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
