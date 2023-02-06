package com.example.blu_e.mentoring

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.ProfileActivity
import com.example.blu_e.R
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mentoring.*
import com.example.blu_e.databinding.ActivityRequestMentoringBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class RequestMentoringActivity : AppCompatActivity()  {
    private lateinit var viewBinding: ActivityRequestMentoringBinding
    private lateinit var adapter: RequestMentoringCommentAdapter
    private lateinit var commentList: ArrayList<PickComment>
    private var pickId: Int = 0
    private var matchingStatus: Int = 1 //모집중 default
//    private val api = RetroInterface.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRequestMentoringBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

       /* //댓글 데이터 임의로 넣음.
        commentList = ArrayList<PickComment>()
        var commentExample: PickComment
        for (i in 0..2) {
            commentExample = PickComment(i)
            commentList.add(i, commentExample)
        }
//        commentExampleList.get(0).userId = 100
        commentList.get(0).contents = "수락해주세요!"
        commentList.get(0).createdAt = Timestamp(System.currentTimeMillis())
//        commentExampleList.get(1).userId = 101
        commentList.get(1).contents = "수락 부탁드려요!"
        commentList.get(1).createdAt = Timestamp(System.currentTimeMillis() + 1)
//        commentExampleList.get(2).userId = 102
        commentList.get(2).contents = "수락 부탁합니다!"
        commentList.get(2).createdAt = Timestamp(System.currentTimeMillis() + 2)

        Log.d("checkList", commentList.toString())
        adapter = RequestMentoringCommentAdapter(commentList, this)
        viewBinding.recyclerViewComment.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerViewComment.adapter = adapter*/

        //글 삭제, 수정 메뉴
        viewBinding.requestMemberPostDeleteIcon?.setOnClickListener {
            var pop = PopupMenu(this, it)

            menuInflater.inflate(R.menu.popup_menu, pop.menu)

            pop.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.deleteMenu ->
                        Toast.makeText(this,"삭제될겁니다.", Toast.LENGTH_SHORT).show()
                    R.id.updateMenu ->
                        Toast.makeText(this, "수정될겁니다.",Toast.LENGTH_SHORT).show()
                }
                false
            }
            pop.show()
        }

        //댓글 전송 버튼
        viewBinding.sendButton?.setOnClickListener {
            closeKeyboard()
            sendMessage()
            //+ 댓글 한번만 날려야해서 댓글창 View.GONE
        }

        //글쓴이 프로필 클릭 시
        viewBinding.image.setOnClickListener {
            //if 멘티 라면
            //else 멘토 라면
            var intent = Intent(this, ProfileActivity::class.java)
            //userId 보내기
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        //맨 처음엔 로그인 유저(글쓴이/댓쓴이/일반회원) 정보부터 불러오기

        //0.0 글쓴이 님 로그인함 (댓글창 x)
            //글쓴이님 -> 멘토님 | 멘티님 (setText)
            //댓글창 View들
            /*viewBinding.backgroundWhite.visibility = View.GONE
            viewBinding.memberPicture2.visibility = View.GONE
            viewBinding.chatEditText.visibility = View.GONE
            viewBinding.sendButton.visibility = View.GONE*/
            //1. 글을 불러와야 함 (글점3개 o), 댓글도 불러와야
                //retrofit (get 글)
                /*api.requestAPostOfMento("", 1).enqueue(object: Callback<PickResponse> {
                    override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                        val body = response.body()
                        if(body!!.code == 1000) {
                            Log.d("구인글 불러오기","성공")
                            val infoPost: ArrayList<Pick> = body.result
                            //+ val infoPostUser: User = User(infoPost.userId)
                            //+ 글쓴이가 멘토인지 멘티인지 파악필요
                            viewBinding.tv.text = "멘토님"
                            viewBinding.careerTv.text = "멘토 경력: "
                            viewBinding.career.text = infoPost.get(0).mentorCareer.toString()
                            viewBinding.tv.text = "멘티님"
                            viewBinding.careerTv.text = "멘티 수준: "
                            viewBinding.career.text = infoPost.get(0).menteeLevel

                            //+ 로그인한 회원이 글의 주인인지 파악 필요 : 글점3개
                            viewBinding.requestMemberPostDeleteIcon.visibility = View.VISIBLE
                            viewBinding.requestMemberPostDeleteIcon.visibility = View.GONE

                            viewBinding.nickTv.text = ""
                            viewBinding.textView4.text = infoPost.get(0).title
                            viewBinding.recruitTv.text = infoPost.get(0).contents
                            if(infoPost.get(0).status == 0) {
                                viewBinding.matchedStatus.visibility = View.VISIBLE
                                viewBinding.matchingStatus.visibility = View.GONE
                                matchingStatus = 0
                            } else {
                                viewBinding.matchedStatus.visibility = View.GONE
                                viewBinding.matchingStatus.visibility = View.VISIBLE
                                matchingStatus = 1
                            }
                            //추가해야 하는 부분
                            // + 기간 세기, 날짜 포맷, 조회수 기능, 희망지역
                            viewBinding.mentoring.text = infoPost.get(0).mentoringMethod
                            viewBinding.subject.text = infoPost.get(0).subject
                            viewBinding.start.text = infoPost.get(0).periodStart.toString()
                            viewBinding.end.text = infoPost.get(0).periodEnd.toString()
                            viewBinding.gender.text = infoPost.get(0).wishGender
                            viewBinding.countReaded.text = infoPost.get(0).viewCount.toString()
                            viewBinding.create.text = infoPost.get(0).createdAt.toString()
                            viewBinding.update.text = infoPost.get(0).createdAt.toString()
                        }
                        else {
                            Log.d("구인글 불러오기","실패")
                        }
                    }

                    override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                        Log.d("구인글 불러오기","실패")
                    }
                })*/

                //retrofit (get 댓글)
            /* api.requestComments("", pickId).enqueue(object: Callback<PickCommentResponse> {
                 override fun onResponse(call: Call<PickCommentResponse>, response: Response<PickCommentResponse>) {
                    val body = response.body()?: return
                    if(body.code == 1000) {
                        val receivedCommentList: ArrayList<PickComment>? = body.result
                        if(matchingStatus == 0) {
                            adapter.updateCompletdTv(0)
                            adapter.updateAcceptBtnv(1)
//                            adapter.updateCommentMenuv(0) + 로그인 한 회원과 댓글 작성자 비교
                        }
                        else {
                            adapter.updateCompletdTv(1)
                            adapter.updateAcceptBtnv(0)
//                            adapter.updateCommentMenuv(0)  + 로그인 한 회원과 댓글 작성자 비교
                        }
                        if (receivedCommentList != null) {
                            commentList.addAll(receivedCommentList)
                        }
                        adapter = RequestMentoringCommentAdapter(receivedCommentList, this@RequestMentoringActivity)
                        viewBinding.recyclerViewComment.layoutManager = LinearLayoutManager(this@RequestMentoringActivity)
                        viewBinding.recyclerViewComment.adapter = adapter
                    }
                    else{
                      Log.d("댓글 불러오기","실패")
                    }
                 }
                 override fun onFailure(call: Call<PickCommentResponse>, t: Throwable) {
                     Log.d("댓글 불러오기","실패")
                 }
             })*/
            //2. 매칭중 여부,, 데이터 불러와서 -> matchingStatus|matchedStatus
                //retrofit (get 글 - 매칭 필드)
            //3.0 매칭 중일 때
                //3.0.1 댓글들 보임.
                    //매칭 완료 UI x, 수락 UI o, 댓점3개 x
            //3.1 매칭중 아닐 때 (매칭 완료)
                //3.1.1 댓글 매칭된 사람 하나만 보임. (그 사람 댓글 데이터의 매칭완료 데이터 불러와)
                    // 수락 UI x, 댓점3개 x
                    //retrofit (get 댓글 - 매칭 필드)
        //0.1 댓쓴이 님 로그인 함 (댓글 창 x, 글점3개 x)
            //1. 내가 쓴 댓글
                 //매칭 완료 UI x, 수락 UI x, 댓점3개 o
            //2. 다른 사람들이 쓴 댓글
                //매칭 완료 UI x, 수락 UI x, 댓점3개 x
        //0.2 일반 회원(잠재적 댓쓴이)님 로그인 함 (댓글 창 o, 글점3개 x)
            //1. 다른 사람들이 쓴 댓글
                //매칭 완료 UI x, 수락 UI x, 댓점3개 x
    }

    //sendMessage
    fun sendMessage() {
        val now = System.currentTimeMillis()
//        val sdf = SimpleDateFormat("yyyy-MM-dd")
//        val getTime = sdf.format(date)
        val commentContent = viewBinding.chatEditText.text.toString()
        /*api.commentWriting("", pickId, commentContent).enqueue(object: Callback<PickCommentResponse> {
            override fun onResponse(call: Call<PickCommentResponse>, response: Response<PickCommentResponse>) {
                val body = response.body()?: return
                val message: String = body.message
                Log.d("댓글 등록하기", message)
                adapter.notifyItemInserted(commentList.size)
            }
            override fun onFailure(call: Call<PickCommentResponse>, t: Throwable) {
                Log.d("댓글 등록하기","실패")
            }
        })*/

     /*   var pickMemberComment = PickComment()
        pickMemberComment.userId = 0
        pickMemberComment.pickId = 0
        pickMemberComment.contents = "새로 추가 됨!"
        pickMemberComment.createdAt = Timestamp(now)
        commentList.add(3, pickMemberComment)
        adapter.notifyItemInserted(commentList.size)*/
    }
    //채팅기능에서 메세지를 입력 후 전송 버튼을 누를 때 키보드 내리기
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
