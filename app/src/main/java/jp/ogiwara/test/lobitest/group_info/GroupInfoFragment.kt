package jp.ogiwara.test.lobitest.group_info

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.ogiwara.test.lobitest.R
import jp.ogiwara.test.lobitest.other.strategy.user_list.GroupMemberUserListStrategy
import jp.ogiwara.test.lobitest.repo
import jp.ogiwara.test.lobitest.user_list.UserListActivity
import jp.ogiwara.test.lobitest.user_list.UserListFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.app.AlertDialog
import com.squareup.picasso.Target
import io.reactivex.disposables.Disposable
import jp.ogiwara.lobirepository.extention.postGroupQuit
import jp.ogiwara.test.lobitest.E

class GroupInfoFragment : Fragment(){

    companion object{
        const val ARG_GROUP_ID = "group_id"

        fun newInstance(groupId: String): GroupInfoFragment{
            val f = GroupInfoFragment()
            val args = Bundle()
            args.putString(ARG_GROUP_ID,groupId)
            f.arguments = args
            return f
        }
    }

    lateinit var groupId: String
    var disposer: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null){
            groupId =arguments
                    .getString(ARG_GROUP_ID)
        }else{
            groupId = savedInstanceState.getString(ARG_GROUP_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_group_info,container,false)
        setUpView(root)
        return root
    }

    private fun setUpView(v: View){
        val desc = v.find<TextView>(R.id.text_description)
        val members = v.find<Button>(R.id.button_members)
        val quit = v.find<Button>(R.id.button_quit_group)

        members.setOnClickListener {
            startActivity(context.intentFor<UserListActivity>(UserListActivity.EXTRA.GROUP_ID to groupId))
        }

        disposer = repo.environment.getGroup(repo,groupId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ g ->
                    desc.text = g.description

                    quit.setOnClickListener {
                        AlertDialog.Builder(context,R.style.AlertDialog)
                                .setTitle(R.string.quit_group_check)
                                .setPositiveButton(R.string.yes,{_,_ ->
                                    g.postGroupQuit(repo).observeOn(AndroidSchedulers.mainThread())
                                            .subscribe({
                                               this@GroupInfoFragment.activity.finish()
                                            },{})
                                })
                                .setNegativeButton(R.string.no,{_,_ ->})
                                .show()
                    }
                },context.E)
    }

    override fun onDestroy() {
        super.onDestroy()

        disposer?.dispose()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(ARG_GROUP_ID,groupId)
    }
}