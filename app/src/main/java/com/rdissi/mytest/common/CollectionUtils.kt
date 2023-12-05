package com.rdissi.mytest.common

import java.util.ArrayList

object CollectionUtils {

    fun <T> combine(list1: List<T>, list2: List<T>): List<T> {
        val listCount1 = list1.size
        val listCount2 = list2.size
        val combined = ArrayList<T>(listCount1 + listCount2)
        val count = Math.max(listCount1, listCount2)
        for (i in 0 until count) {
            if (i < listCount1) {
                combined.add(list1[i])
            }
            if (i < listCount2) {
                combined.add(list2[i])
            }
        }
        return combined
    }

}
