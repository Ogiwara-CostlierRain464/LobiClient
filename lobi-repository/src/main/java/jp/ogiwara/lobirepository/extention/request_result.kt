package jp.ogiwara.lobirepository.extention

import jp.ogiwara.lobiapi.model.RequestResult

fun RequestResult.isSuccess() =
        success == 1