package jp.ogiwara.lobirepository.util

import android.support.v4.util.ArrayMap

class SizeFixedMap<K,V>(val maxSize: Int): ArrayMap<K,V>(maxSize){
    override fun put(key: K, value: V): V? {
        if(size >= maxSize){
            removeFirst()
        }

        return super.put(key, value)
    }

    private fun removeFirst(){
        val firstKey = keys.first()
        remove(firstKey)
    }
}