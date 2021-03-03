package com.permissionx.jerrydev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 *作者：张晋瑞
 *日期：2021/3/3
 *说明：
 */
class InvisibleFragment : Fragment() {
    private var callback:((Boolean,List<String>)->Unit)?=null
    fun requestNow(cb:(Boolean,List<String>)->Unit,vararg permissions:String){
        callback=cb
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==1){
            val deniedList=ArrayList<String>()
            for ((index,result)in grantResults.withIndex()){
                if (result!=PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            val allGranted=deniedList.isEmpty()
            callback?.let {
                it(allGranted,deniedList)
            }
        }
    }
}