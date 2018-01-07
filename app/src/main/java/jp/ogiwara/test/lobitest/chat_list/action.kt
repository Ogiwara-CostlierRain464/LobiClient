package jp.ogiwara.test.lobitest.chat_list

import jp.ogiwara.cycle.Action
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobirepository.extention.ReactiveChat

class LOAD: Action
class RELOAD: Action
class LOADED(val list: List<ReactiveChat>): Action
//class ADD(val chat: ReactiveChat): Action
object DESTROY: Action
