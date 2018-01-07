package jp.ogiwara.lobirepository.model

import jp.ogiwara.lobirepository.extention.ReactiveGroup

class GroupListTokenWrapper(override val list: List<ReactiveGroup>,
                            override val token: String?): ListTokenWrapper