package com.ShoppingCart.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Filter {

    @Column(name = "Filter1")
    private String Filter1;
    @Column(name = "Filter2")
    private String Filter2;
    @Column(name = "Filter3")
    private String Filter3;
    @Column(name = "Filter4")
    private String Filter4;
    @Column(name = "Filter5")
    private String Filter5;

    public Filter(){

    }

    public String getFilter1() {
        return Filter1;
    }

    public void setFilter1(String filter1) {
        Filter1 = filter1;
    }

    public String getFilter2() {
        return Filter2;
    }

    public void setFilter2(String filter2) {
        Filter2 = filter2;
    }

    public String getFilter3() {
        return Filter3;
    }

    public void setFilter3(String filter3) {
        Filter3 = filter3;
    }

    public String getFilter4() {
        return Filter4;
    }

    public void setFilter4(String filter4) {
        Filter4 = filter4;
    }

    public String getFilter5() {
        return Filter5;
    }

    public void setFilter5(String filter5) {
        Filter5 = filter5;
    }
}
