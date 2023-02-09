package com.example.blu_e.mentoring

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.ProfileActivity
import com.example.blu_e.R
import com.example.blu_e.data.mentoring.PickComment
import com.example.blu_e.data.mentoring.RequestMentoringCommentAdapter
import com.example.blu_e.data.mentoring.Solution
import com.example.blu_e.data.mentoring.SolutionCommentAdapter
import com.example.blu_e.databinding.ActivityAskQuestionBinding
import java.sql.Timestamp

class AskQuestionActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAskQuestionBinding
    private lateinit var adapter: SolutionCommentAdapter
    private lateinit var commentList: ArrayList<Solution>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAskQuestionBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //댓글 데이터 임의로 넣음.
        commentList = ArrayList<Solution>()
        var commentExample: Solution
        for (i in 0..2) {
            commentExample = Solution()
            commentList.add(i, commentExample)
        }
//        commentExampleList.get(0).userId = 100
        commentList.get(0).contents = "이 문제는 ~ 이렇게 풀면 돼!"
        commentList.get(0).createdAt = Timestamp(System.currentTimeMillis())
//        commentExampleList.get(1).userId = 101
        commentList.get(1).contents = "이 문제는 ~ 이렇게 풀면 돼!"
        commentList.get(1).createdAt = Timestamp(System.currentTimeMillis() + 1)
//        commentExampleList.get(2).userId = 102
        commentList.get(2).contents = "이 문제는 ~ 이렇게 풀면 돼!"
        commentList.get(2).createdAt = Timestamp(System.currentTimeMillis() + 2)

        Log.d("checkList", commentList.toString())
        adapter = SolutionCommentAdapter(commentList, this)
        viewBinding.recyclerViewAnswer.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerViewAnswer.adapter = adapter

        //댓글 전송 버튼
        viewBinding.sendButton?.setOnClickListener {
            closeKeyboard()
            sendMessage()
            viewBinding.chatEditText.text = null
        }

        //글쓴이 프로필 클릭 시
        viewBinding.image.setOnClickListener {
            //if 멘티 라면
            //else 멘토 라면
            var intent = Intent(this, ProfileActivity::class.java)
            //userId 보내기
            startActivity(intent)
        }

        //뒤로 가기
        viewBinding.backToCenterD.setOnClickListener {
            finish()
        }

        //삭제, 수정 메뉴
        viewBinding.requestMemberPostDeleteIcon?.setOnClickListener {
            var pop = PopupMenu(this, it)
            menuInflater.inflate(R.menu.popup_menu, pop.menu)
            pop.setOnMenuItemClickListener {
                if(it.itemId == R.id.deleteMenu) {
                }
                else if(it.itemId == R.id.updateMenu) {
                }
                false
            }
            pop.show()
        }
    }

    fun sendMessage() {

    }

    fun closeKeyboard()
    {
        var view = this.currentFocus

        if(view != null)
        {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}