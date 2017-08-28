package jp.ogiwara.test.lobitest.group_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import jp.ogiwara.lobiapi.model.Group
import jp.ogiwara.test.lobitest.R
import org.jetbrains.anko.find


class GroupView : FrameLayout {

    constructor(context: Context?): super(context)

    constructor(context: Context?,
                attrs: AttributeSet?): super(context,attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int): super(context,attrs,defStyleAttr)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes)

    init{
        LayoutInflater.from(context).inflate(R.layout.group_view,this,true)
    }

    fun setGroup(group: Group){
        val image = find<ImageView>(R.id.group_image)
        val title = find<TextView>(R.id.group_title)
        val people = find<TextView>(R.id.group_people)
        val desc = find<TextView>(R.id.group_description)

        title.text = group.name
        people.text = group.members_count?.toString() ?: "NaN"
        desc.text = group.description

        Picasso.with(context).load(group.icon).into(image)
    }
}