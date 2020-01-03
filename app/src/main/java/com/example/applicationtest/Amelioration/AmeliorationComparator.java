package com.example.applicationtest.Amelioration;


import java.util.Comparator;

public class AmeliorationComparator implements Comparator<Amelioration> {

    @Override
    public int compare(Amelioration a, Amelioration b) {
        return Double.compare(a.getCost(), b.getCost());
    }

}
