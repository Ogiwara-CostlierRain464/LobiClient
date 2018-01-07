package jp.ogiwara.lobirepository.model

import jp.ogiwara.lobiapi.model.Activity

class ActivityListTokenWrapper(override val list: List<Activity>,
                               override val token: String?): ListTokenWrapper