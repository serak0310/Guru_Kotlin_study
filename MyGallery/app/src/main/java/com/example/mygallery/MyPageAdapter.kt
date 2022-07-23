package com.example.mygallery

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val items = ArrayList<Fragment>()

    // 프래그먼트 개수
    override fun getCount(): Int {
        return items.size
    }

    // postion 위치의 프래그먼트
    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    fun updateFragmednts(items : List<Fragment>){
        this.items.addAll(items)
    }

}