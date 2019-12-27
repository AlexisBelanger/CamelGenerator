package com.example.applicationtest.employe;

import java.util.Comparator;

public class EmployeComparator implements Comparator<Employe> {
    @Override
    public int compare(Employe a, Employe b) {
        return Integer.compare(a.getRank(), b.getRank());
    }
}