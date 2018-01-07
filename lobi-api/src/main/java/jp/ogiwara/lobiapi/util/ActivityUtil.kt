package jp.ogiwara.lobiapi.util

import jp.ogiwara.lobiapi.model.Activity

//TODO link(nakamap://)
object ActivityUtil {

    fun makeTitle(a: Activity): String{

        var result = a.title.template

        a.title.items.forEachIndexed { index, activityTitleItem ->
            result = result.replace("{{p${index + 1}}}",activityTitleItem.label)
        }
        return result
    }

}