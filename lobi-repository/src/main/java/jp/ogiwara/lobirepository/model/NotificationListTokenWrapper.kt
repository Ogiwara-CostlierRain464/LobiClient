package jp.ogiwara.lobirepository.model

import jp.ogiwara.lobiapi.model.Notification


class NotificationListTokenWrapper(override val list: List<Notification>,
                                   override val token: String?): ListTokenWrapper
