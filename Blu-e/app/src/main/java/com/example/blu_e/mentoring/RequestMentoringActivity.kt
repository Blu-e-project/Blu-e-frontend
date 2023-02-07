package com.example.blu_e.mentoring

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.ProfileActivity
import com.example.blu_e.R
import com.example.blu_e.RecruitMenteeActivity
import com.example.blu_e.RecruitMentorActivity
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mentoring.*
import com.example.blu_e.databinding.ActivityRequestMentoringBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList

class RequestMentoringActivity : AppCompatActivity()  {
    private lateinit var viewBinding: ActivityRequestMentoringBinding
    private lateinit var adapter: RequestMentoringCommentAdapter
    private lateinit var commentList: ArrayList<PickComment>
    private var pickId: Int = 0
    private var matchingStatus: Int = 1 //모집중 default
    private lateinit var postOfContents: Pick
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
            pop.setOnMenuItemClickListener {
                if(it.itemId == R.id.deleteMenu) {
                    //멘티 구하는 글의 삭제
                    /*api.deleteAPostOfMentee("", 0).enqueue(object: Callback<PickResponse> {
                        override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                            val body = response.body()
                            if (body!!.code == 1000) {
                                Log.d("멘티 구인글메뉴", "삭제 성공")
                                //바깥 화면으로 이동하는 코드
                            }
                        }
                        override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                            Log.d("멘티 구인글메뉴","삭제 실패")
                        }
                    })*/
                    //멘토 구하는 글의 삭제
                    /*api.deleteAPostOfMentor("", 0).enqueue(object: Callback<PickResponse> {
                        override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                            val body = response.body()
                            if (body!!.code == 1000) {
                                Log.d("멘토 구인글메뉴", "삭제 성공")
                                //바깥 화면으로 이동하는 코드
                            }
                        }
                        override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                            Log.d("멘토 구인글 메뉴","삭제 실패")
                        }
                    })*/
                }
                else if(it.itemId == R.id.updateMenu) {
                    //수정 폼으로 화면 넘어가는 코드..
                    //+ 멘티구인글이라면
                    var intent: Intent = Intent(this, RecruitMenteeActivity::class.java)
                    intent.putExtra("fromUpdate", 1)
                    intent.putExtra("contents", postOfContents)
                    //+ 멘토구인글이라면
                    intent = Intent(this, RecruitMentorActivity::class.java)
                    intent.putExtra("fromUpdate", 1)
                    intent.putExtra("contents", postOfContents)

                    startActivity(intent)
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
        adapter.notifyItemChanged(commentList.size)
        //+ 조회수 올리는 기능!

        //Flow -> 맨 처음엔 로그인 유저 정보부터 불러오기
        //<           멘티면 ->                멘토>  //(글쓴이(토)/댓쓴이(티)/일반회원(잠재적 댓쓴이)(티))
        //<로그인 유저가 멘토면 -> 글쓴 사람은 반드시 멘티> //(글쓴이(티)/댓쓴이(토)/일반회원(잠재적 댓쓴이)(토))

                //로그인 유저가 멘티면 //+ pickId
               /* api.requestAPostOfMentee("", 1).enqueue(object: Callback<PickResponse> {
                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                        val body = response.body()
                        if(body!!.code == 1000) {
                            Log.d("멘티 구인글 불러오기","성공")
                            val infoPost: ArrayList<Pick> = body.result
                            postOfContents = infoPost.get(0)
                            //+ val infoPostUser: User = User(infoPost.userId)
                            viewBinding.tv.text = "멘티님"
                            viewBinding.careerTv.text = "멘티 수준: "
                            viewBinding.career.text = infoPost.get(0).menteeLevel

                            //로그인한 회원이 글의 주인인지 파악 필요(내가 쓴 글 보기) : 글점3개
                            //User vs. infoPostUser
                            //+ 글쓴이(멘토)라면
                            viewBinding.requestMemberPostDeleteIcon.visibility = View.VISIBLE
                            //+ 댓쓴이(멘티)라면 //+ 일반회원(멘티)라면
                            viewBinding.requestMemberPostDeleteIcon.visibility = View.GONE

                            //+ viewBinding.image.setImageURI() //InfoUser에서 userImage get
                            viewBinding.nickTv.text = "" //+ InfoUser에서 nickname get
                            viewBinding.textView4.text = infoPost.get(0).title
                            viewBinding.recruitTv.text = infoPost.get(0).contents
                            viewBinding.create.text = convertTimeToDate(infoPost.get(0).createdAt)
                            viewBinding.update.text = convertTimeToDate(infoPost.get(0).updatedAt)
                            //매칭 상태
                            if(infoPost.get(0).status == 0) {//모집 완료
                                viewBinding.matchedStatus.visibility = View.VISIBLE
                                viewBinding.matchingStatus.visibility = View.GONE
                                matchingStatus = 0
                            } else {//모집 중
                                viewBinding.matchedStatus.visibility = View.GONE
                                viewBinding.matchingStatus.visibility = View.VISIBLE
                                matchingStatus = 1
                            }
                            //+ 희망지역
                            val startDate: Date = infoPost.get(0).periodStart
                            val endDate: Date = infoPost.get(0).periodEnd

                            val calendar = Calendar.getInstance()
                            calendar.time = startDate
                            val startDate2: LocalDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE))
                            calendar.time = endDate
                            val endDate2: LocalDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE))
                            val betweenDays: Long =  ChronoUnit.DAYS.between(startDate2, endDate2) //Month로 바꿀 수도 있음.
                            Log.d("멘토링 기간", betweenDays.toString())
                            viewBinding.period.text = "$betweenDays 일"

                            viewBinding.subject.text = infoPost.get(0).subject
                            viewBinding.mentoring.text = infoPost.get(0).mentoringMethod

                            viewBinding.start.text = startDate.toString()
                            viewBinding.end.text = endDate.toString()

                            viewBinding.gender.text = infoPost.get(0).wishGender
                            viewBinding.countReaded.text = infoPost.get(0).viewCount.toString()
                        }
                        else {
                            Log.d("멘티 구인글 불러오기","실패")
                        }
                    }

                    override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                        Log.d("멘티 구인글 불러오기","실패")
                    }
                })

                //retrofit (get 댓글)
             api.requestMenteeComments("", pickId).enqueue(object: Callback<PickCommentResponse> {
                 override fun onResponse(call: Call<PickCommentResponse>, response: Response<PickCommentResponse>) {
                    val body = response.body()?: return
                    if(body.code == 1000) {
                        val receivedCommentList: ArrayList<PickComment>? = body.result
                        if(matchingStatus == 1) { //모집중
                            //+글쓴이(멘토)라면
                            adapter.updateCompletdTv(0)
                            adapter.updateAcceptBtnv(1)
                            adapter.updateCommentMenuv(0)
                            disappearCommentForm()
                            //+댓쓴이(멘티)라면 //+adapter에서 한번 더 체크 (댓쓴이가 로그인유저인지)
                            adapter.updateCompletdTv(0)
                            adapter.updateAcceptBtnv(0)
                            adapter.updateCommentMenuv(1)
                            disappearCommentForm()

                            //+일반회원(멘티)라면
                            adapter.updateCompletdTv(0)
                            adapter.updateAcceptBtnv(0)
                            adapter.updateCommentMenuv(0)
                            appearCommentForm()
                        }
                        else { //모집완료
                            adapter.updateCompletdTv(1)
                            adapter.updateAcceptBtnv(0)
                            adapter.updateCommentMenuv(0)
                        }
                        if (receivedCommentList != null) {
                            commentList.addAll(receivedCommentList)
                        }
                        adapter = RequestMentoringCommentAdapter(receivedCommentList, this@RequestMentoringActivity)
                        viewBinding.recyclerViewComment.layoutManager = LinearLayoutManager(this@RequestMentoringActivity)
                        viewBinding.recyclerViewComment.adapter = adapter
                    }
                    else{
                      Log.d("멘티 댓글 불러오기","실패")
                    }
                 }
                 override fun onFailure(call: Call<PickCommentResponse>, t: Throwable) {
                     Log.d("멘티 댓글 불러오기","실패")
                 }
             })
            //+ 매칭중 아닐 때 (매칭 완료) -> 댓글 매칭된 사람 하나만 보임.

            //로그인 유저가 멘토면
            //<로그인 유저가 멘토면 -> 글쓴 사람은 반드시 멘티> //(글쓴이(티)/댓쓴이(토)/일반회원(잠재적 댓쓴이)(토))
            api.requestAPostOfMentor("", 1).enqueue(object: Callback<PickResponse> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<PickResponse>, response: Response<PickResponse>) {
                    val body = response.body()
                    if(body!!.code == 1000) {
                        Log.d("멘토 구인글 불러오기","성공")
                        val infoPost: ArrayList<Pick> = body.result
                        postOfContents = infoPost.get(0)
                        //+ val infoPostUser: User = User(infoPost.userId)
                        viewBinding.tv.text = "멘토님"
                        viewBinding.careerTv.text = "멘토 경력: "
                        viewBinding.career.text = infoPost.get(0).mentorCareer

                        //로그인한 회원이 글의 주인인지 파악 필요(내가 쓴 글 보기) : 글점3개
                        //User vs. infoPostUser
                        //+ 글쓴이(멘티)라면
                        viewBinding.requestMemberPostDeleteIcon.visibility = View.VISIBLE
                        //+ 댓쓴이(멘토)라면 //+ 일반회원(멘토)라면
                        viewBinding.requestMemberPostDeleteIcon.visibility = View.GONE

                        //+ viewBinding.image.setImageURI() //InfoUser에서 userImage get
                        viewBinding.nickTv.text = "" //+ InfoUser에서 nickname get
                        viewBinding.textView4.text = infoPost.get(0).title
                        viewBinding.recruitTv.text = infoPost.get(0).contents
                        viewBinding.create.text = convertTimeToDate(infoPost.get(0).createdAt)
                        viewBinding.update.text = convertTimeToDate(infoPost.get(0).updatedAt)
                        //매칭 상태
                        if(infoPost.get(0).status == 0) {//모집 완료
                            viewBinding.matchedStatus.visibility = View.VISIBLE
                            viewBinding.matchingStatus.visibility = View.GONE
                            matchingStatus = 0
                        } else {//모집 중
                            viewBinding.matchedStatus.visibility = View.GONE
                            viewBinding.matchingStatus.visibility = View.VISIBLE
                            matchingStatus = 1
                        }
                        //+ 희망지역
                        val startDate: Date = infoPost.get(0).periodStart
                        val endDate: Date = infoPost.get(0).periodEnd

                        val calendar = Calendar.getInstance()
                        calendar.time = startDate
                        val startDate2: LocalDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE))
                        calendar.time = endDate
                        val endDate2: LocalDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE))
                        val betweenDays: Long =  ChronoUnit.DAYS.between(startDate2, endDate2) //Month로 바꿀 수도 있음.
                        Log.d("멘토링 기간", betweenDays.toString())
                        viewBinding.period.text = "$betweenDays 일"

                        viewBinding.subject.text = infoPost.get(0).subject
                        viewBinding.mentoring.text = infoPost.get(0).mentoringMethod

                        viewBinding.start.text = startDate.toString()
                        viewBinding.end.text = endDate.toString()

                        viewBinding.gender.text = infoPost.get(0).wishGender
                        viewBinding.countReaded.text = infoPost.get(0).viewCount.toString()
                    }
                    else {
                        Log.d("멘토 구인글 불러오기","실패")
                    }
                }

                override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                    Log.d("멘토 구인글 불러오기","실패")
                }
            })

            //retrofit (get 댓글)
            api.requestMentorComments("", pickId).enqueue(object: Callback<PickCommentResponse> {
                override fun onResponse(call: Call<PickCommentResponse>, response: Response<PickCommentResponse>) {
                    val body = response.body()?: return
                    if(body.code == 1000) {
                        val receivedCommentList: ArrayList<PickComment>? = body.result
                        if(matchingStatus == 1) { //모집중
                            //+글쓴이(멘티)라면
                            adapter.updateCompletdTv(0)
                            adapter.updateAcceptBtnv(1)
                            adapter.updateCommentMenuv(0)
                            disappearCommentForm()
                            //+댓쓴이(멘토)라면 //+adapter에서 한번 더 체크 (댓쓴이가 로그인유저인지)
                            adapter.updateCompletdTv(0)
                            adapter.updateAcceptBtnv(0)
                            adapter.updateCommentMenuv(1)
                            disappearCommentForm()

                            //+일반회원(멘토)라면
                            adapter.updateCompletdTv(0)
                            adapter.updateAcceptBtnv(0)
                            adapter.updateCommentMenuv(0)
                            appearCommentForm()
                        }
                        else { //모집완료
                            adapter.updateCompletdTv(1)
                            adapter.updateAcceptBtnv(0)
                            adapter.updateCommentMenuv(0)
                        }
                        if (receivedCommentList != null) {
                            commentList.addAll(receivedCommentList)
                        }
                        adapter = RequestMentoringCommentAdapter(receivedCommentList, this@RequestMentoringActivity)
                        viewBinding.recyclerViewComment.layoutManager = LinearLayoutManager(this@RequestMentoringActivity)
                        viewBinding.recyclerViewComment.adapter = adapter
                    }
                    else{
                        Log.d("멘토 댓글 불러오기","실패")
                    }
                }
                override fun onFailure(call: Call<PickCommentResponse>, t: Throwable) {
                    Log.d("멘토 댓글 불러오기","실패")
                }
            })*/

        }

    //sendMessage
    fun sendMessage() {
        val now = System.currentTimeMillis()
//        val sdf = SimpleDateFormat("yyyy-MM-dd")
//        val getTime = sdf.format(date)
        val commentContent = viewBinding.chatEditText.text.toString()
        //+ 로그인 유저가 멘티 회원임.
        /*api.commentWritingAsMentee("", pickId, commentContent).enqueue(object: Callback<PickCommentResponse> {
            override fun onResponse(call: Call<PickCommentResponse>, response: Response<PickCommentResponse>) {
                val body = response.body()?: return
                val message: String = body.message
                Log.d("멘티 구인글의 댓글 등록하기", message)
                adapter.notifyItemInserted(commentList.size)
            }
            override fun onFailure(call: Call<PickCommentResponse>, t: Throwable) {
                Log.d("멘티 구인글의 댓글 등록하기","실패")
            }
        })*/
        //+ 로그인 유저가 멘토회원임.
        /*api.commentWritingAsMentor("", pickId, commentContent).enqueue(object: Callback<PickCommentResponse> {
            override fun onResponse(call: Call<PickCommentResponse>, response: Response<PickCommentResponse>) {
                val body = response.body()?: return
                val message: String = body.message
                Log.d("멘토 구인글의 댓글 등록하기", message)
                adapter.notifyItemInserted(commentList.size)
            }
            override fun onFailure(call: Call<PickCommentResponse>, t: Throwable) {
                Log.d("멘토 구인글의 댓글 등록하기","실패")
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

    //Timestamp -> Date
    fun convertTimeToDate(timestamp: Timestamp): String {
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        val date = sdf.format(timestamp)
        return date
    }

    fun disappearCommentForm() {
        viewBinding.backgroundWhite.visibility = View.GONE
        viewBinding.memberPicture2.visibility = View.GONE
        viewBinding.chatEditText.visibility = View.GONE
        viewBinding.sendButton.visibility = View.GONE
    }

    fun appearCommentForm() {
        viewBinding.backgroundWhite.visibility = View.VISIBLE
        viewBinding.memberPicture2.visibility = View.VISIBLE
        viewBinding.chatEditText.visibility = View.VISIBLE
        viewBinding.sendButton.visibility = View.VISIBLE
    }
}
