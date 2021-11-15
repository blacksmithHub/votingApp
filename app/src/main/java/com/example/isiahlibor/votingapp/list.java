package com.example.isiahlibor.votingapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Isiah Libor on 3/12/2018.
 */

public class list implements Serializable {

    private ArrayList<String> list;

    public ArrayList<String> getList()
    {
        return list;
    }
    public void setList (ArrayList<String> list)
    {
        this.list = list;
    }
}
