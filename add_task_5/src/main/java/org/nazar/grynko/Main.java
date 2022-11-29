package org.nazar.grynko;

import org.nazar.grynko.task.SkipList;

public class Main {

    public static void main(String[]args){
        SkipList skipList = new SkipList(5);
        for (int i = 0; i < 6; i++){
            skipList.add(i);
            skipList.remove(4);
            System.out.println(skipList.contains(5));
        }
    }

}
