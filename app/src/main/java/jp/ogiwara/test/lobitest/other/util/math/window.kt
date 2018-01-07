package jp.ogiwara.test.lobitest.other.util.math

fun getGroupGridSpanCount(width: Int,height: Int) = if(width <= height) 2 else 3

fun getUserGridSpanCount(width: Int,height: Int) = if(width <= height) 4 else 6