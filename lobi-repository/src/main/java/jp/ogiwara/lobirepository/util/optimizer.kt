package jp.ogiwara.lobirepository.util

import jp.ogiwara.lobiapi.model.Group

/**
 * 各modelを軽量化するutil
 */

fun cleanGroup(g: Group): Group{
    //listをnullに
    g.subleaders = arrayListOf()

    return g
}
