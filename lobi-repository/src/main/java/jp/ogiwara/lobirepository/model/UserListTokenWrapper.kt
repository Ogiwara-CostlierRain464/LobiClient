package jp.ogiwara.lobirepository.model

import jp.ogiwara.lobirepository.extention.ReactiveUser

class UserListTokenWrapper(override val list: List<ReactiveUser>,
                           override val token: String?): ListTokenWrapper