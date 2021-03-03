package com.permissionx.jerrydev

import androidx.fragment.app.FragmentActivity

/**
 *作者：张晋瑞
 *日期：2021/3/3
 *说明：
 */
object PermissionX {
    private const val TAG="InvisibleFragment"

    fun request(activity:FragmentActivity,vararg permissions:String,callback:(Boolean,List<String>)->Unit){
        val fragmentManager=activity.supportFragmentManager
        val existedFragment=fragmentManager.findFragmentByTag(TAG)
        val fragment=if (existedFragment!=null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment=InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback,*permissions)
    }
}