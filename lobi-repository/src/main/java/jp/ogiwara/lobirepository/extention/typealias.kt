package jp.ogiwara.lobirepository.extention

import io.reactivex.Observable
import jp.ogiwara.lobiapi.model.Chat
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.lobiapi.model.User

typealias ReactiveVal<T> = Observable<T>
typealias ReactiveList<T> = Observable<T>

typealias ReactiveGroup = ReactiveVal<Group>
typealias ReactiveUser = ReactiveVal<User>
typealias ReactiveChat = ReactiveVal<Chat>