package com.example.blu_e.mentoring

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blu_e.MainApplication
import com.example.blu_e.R
import com.example.blu_e.RecruitMenteeActivity
import com.example.blu_e.RecruitMentorActivity
import com.example.blu_e.data.ResponseData
import com.example.blu_e.data.RetroInterface
import com.example.blu_e.data.mentoring.*
import com.example.blu_e.databinding.ActivityRequestMentoringBinding
import com.example.blu_e.mentoring.ProfileActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*


class RequestMentoringActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRequestMentoringBinding
    private var matchingStatus: Int = 1 //모집중 default
    private lateinit var postOfContents: Pick
    private lateinit var infoPost: ArrayList<Pick>
    private var infoPostUserId: Int = 0

    private var commentList: ArrayList<PickComment> = ArrayList<PickComment>()
    private var adapter: RequestMentoringCommentAdapter? = null

    private var userId = MainApplication.prefs.getString("userId", "")
    private var role = MainApplication.prefs.getString("role", "")
    var loginUserId: Int = userId.toInt()
    var isMentor: Int = role.toInt() //멘토: 1, 멘티: 2

    private val api = RetroInterface.create()

    companion object {
        const val pickId: Int = 9 //클릭한 pickId 받아서 여기에 저장!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRequestMentoringBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

/*      //댓글 데이터 임의로 넣음.
        commentList = ArrayList<PickComment>()
        var commentExample: PickComment
        for (i in 0..2) {
            commentExample = PickComment()
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
        //viewBinding.recyclerViewComment.setHasFixedSize(false)

        //글 삭제, 수정 메뉴
        viewBinding.requestMemberPostDeleteIcon?.setOnClickListener {
            var pop = PopupMenu(this, it)
            menuInflater.inflate(R.menu.popup_menu, pop.menu)
            pop.setOnMenuItemClickListener {
                if (it.itemId == R.id.deleteMenu) {
                    if(isMentor == 1) {
                        //멘티 구하는 글의 삭제
                        api.deleteAPostOfMentee(pickId).enqueue(object: Callback<ResponseData> {
                            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                                val body = response.body()
                                if(body != null) {
                                    if (body.code == 1000) {
                                        Log.d("멘티 구인글메뉴", "삭제 성공")
                                        finish()
                                    }
                                }
                            }
                            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                                Log.d("멘티 구인글메뉴","삭제 실패")
                            }
                        })
                    } else {
                        //멘토 구하는 글의 삭제
                        api.deleteAPostOfMentor(pickId).enqueue(object: Callback<ResponseData> {
                            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                                val body = response.body()
                                if(body != null) {
                                    if (body.code == 1000) {
                                        Log.d("멘토 구인글메뉴", body.message)
                                        finish()
                                    }
                                }
                            }
                            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                                Log.d("멘토 구인글 메뉴","삭제 실패")
                            }
                        })
                    }
                } else if (it.itemId == R.id.updateMenu) {
                    //수정 폼으로 화면 넘어감
                    var intent: Intent
                    if(isMentor == 2) {
                        //+ 멘티구인글이라면
                        intent = Intent(this, RecruitMenteeActivity::class.java)
                        intent.putExtra("fromUpdate", 1)
                        intent.putExtra("contents", postOfContents)
                    } else {
                        //+ 멘토구인글이라면
                        intent = Intent(this, RecruitMentorActivity::class.java)
                        intent.putExtra("fromUpdate", 1)
                        intent.putExtra("contents", postOfContents) // 구인글 안불러지면 오류남
                    }
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
            viewBinding.chatEditText.text = null
            disappearCommentForm()
        }

        //글쓴이 프로필 클릭 시
        viewBinding.image.setOnClickListener {
            var intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("infoPostUserId", infoPostUserId)
            startActivity(intent)
        }

        //뒤로 가기
        viewBinding.backToCenterD.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
//        adapter.notifyItemChanged(commentList.size)
        //Flow -> 맨 처음엔 로그인 유저 정보부터 불러오기 -> isMentor
        Log.d("로그인 유저 멘토면 1 멘티면 2", isMentor.toString())
        if (isMentor == 2) { //멘티라면 2
            //<로그인 유저가 멘티면 -> 글쓴 사람은 받드시 멘토>  //(글쓴이(토)/댓쓴이(티)/일반회원(잠재적 댓쓴이)(티))
            callMenteePostAndComment()
        } else { // 멘토라면 1
            //<로그인 유저가 멘토면 -> 글쓴 사람은 반드시 멘티> //(글쓴이(티)/댓쓴이(토)/일반회원(잠재적 댓쓴이)(토))
            callMentorPostAndComment()
        }
    }
    //sendMessage
    fun sendMessage() {
        val now = System.currentTimeMillis()
//        val sdf = SimpleDateFormat("yyyy-MM-dd")
//        val getTime = sdf.format(date)
        val commentContent = viewBinding.chatEditText.text.toString()
        //+ 로그인 유저가 멘티 회원임.
        if(role.toInt() == 2) {
            Log.d("여기로 들어왔을과?", "묭!")
            api.commentWritingAsMentee(pickId, commentContent).enqueue(object: Callback<ResponseData> {
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    val body = response.body()
                    if(body != null) {
                        if(body.code == 1000) {
                            Log.d("멘티 구인글의 댓글 등록하기", body.message)
                            viewBinding.recyclerViewComment.adapter!!.notifyItemChanged(commentList.size)
                        } else if (body.code == 2700 || body.code == 2701 || body.code == 2702) {
                            Log.d("멘티 구인글의 댓글 등록하기 실패: ", body.message)
                            Toast.makeText(this@RequestMentoringActivity, body.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.d("멘티 구인글의 댓글 등록하기","실패")
                }
            })
        }
        else if (role.toInt() == 1) {
            //+ 로그인 유저가 멘토회원임.
            Log.d("여기로 들어왔을과?", "믱!")
            api.commentWritingAsMentor(pickId, commentContent).enqueue(object: Callback<ResponseData> {
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    val body = response.body()
                    if(body != null) {
                        if(body.code == 1000) {
                            Log.d("멘토 구인글의 댓글 등록하기", body.message)
                            viewBinding.recyclerViewComment.adapter!!.notifyItemChanged(commentList.size)
                        } else if (body.code == 2700 || body.code == 2701 || body.code == 2702 || body.code == 2703) {
                            Log.d("멘토 구인글의 댓글 등록하기 실패: ", body.message)
                            Toast.makeText(this@RequestMentoringActivity, body.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.d("멘토 구인글의 댓글 등록하기","실패")
                }
            })
        }

        /*   var pickMemberComment = PickComment()
           pickMemberComment.userId = 0
           pickMemberComment.pickId = 0
           pickMemberComment.contents = "새로 추가 됨!"
           pickMemberComment.createdAt = Timestamp(now)
           commentList.add(3, pickMemberComment)
           adapter.notifyItemInserted(commentList.size)*/
    }

    fun postCommonPart() {
        //로그인한 회원이 글의 주인인지 파악 필요(내가 쓴 글 보기) : 글점3개 //User vs. infoPostUser
        if(infoPostUserId == loginUserId) {
            //+ 글쓴이(멘토)라면
            viewBinding.requestMemberPostDeleteIcon.visibility = View.VISIBLE
        } else {
            //+ 댓쓴이(멘티)라면 //+ 일반회원(멘티)라면
            viewBinding.requestMemberPostDeleteIcon.visibility = View.GONE
        }
        //viewBinding.image.setImageURI() //InfoUser에서 userImage get
        viewBinding.nickTv.text = infoPost.get(0).nickname //+ InfoUser에서 nickname get
        viewBinding.textView4.text = infoPost.get(0).title //viewBinding.create.text = convertTimeToDate(infoPost.get(0).createdAt)
        viewBinding.recruitTv.text = infoPost.get(0).contents //viewBinding.update.text = convertTimeToDate(infoPost.get(0).updatedAt)
        //매칭 상태
        if (infoPost.get(0).status == 0) {//모집 완료
            viewBinding.matchedStatus.visibility = View.VISIBLE
            viewBinding.matchingStatus.visibility = View.GONE
            matchingStatus = 0
        } else {//모집 중
            viewBinding.matchedStatus.visibility = View.GONE
            viewBinding.matchingStatus.visibility = View.VISIBLE
            matchingStatus = 1
        }
        //+ 희망지역..
        val startDate = infoPost.get(0).periodStart
        val endDate  = infoPost.get(0).periodEnd

        /*val calendar = Calendar.getInstance()
        calendar.time = startDate
        val startDate2: LocalDate = LocalDate.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE)
        )
        calendar.time = endDate
        val endDate2: LocalDate = LocalDate.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE)
        )
        val betweenDays: Long =
            ChronoUnit.DAYS.between(startDate2, endDate2) //Month로 바꿀 수도 있음.
        Log.d("멘토링 기간", betweenDays.toString())
        viewBinding.period.text = "$betweenDays 일"*/

        viewBinding.subject.text = infoPost.get(0).subject
        viewBinding.mentoring.text = infoPost.get(0).mentoringMethod

        viewBinding.start.text = startDate.toString()
        viewBinding.end.text = endDate.toString()

        viewBinding.gender.text = infoPost.get(0).wishGender
        viewBinding.countReaded.text = infoPost.get(0).viewCount.toString()
    }
    fun commentCommonPart(receivedCommentList: ArrayList<PickComment>) {
        Log.d("이 함수 안들어왔니?", "?")
        commentList.addAll(receivedCommentList)
        adapter = RequestMentoringCommentAdapter(receivedCommentList,this@RequestMentoringActivity)
        viewBinding.recyclerViewComment.layoutManager = LinearLayoutManager(this@RequestMentoringActivity)
        viewBinding.recyclerViewComment.adapter = adapter

        if (matchingStatus == 1) { //모집중
            if(infoPostUserId == loginUserId) {
                //+글쓴이라면
                adapter!!.updateCompletdTv(0)
                adapter!!.updateAcceptBtnv(1)
                adapter!!.updateCommentMenuv(0)
                disappearCommentForm()
            }
            else {
                //+댓쓴이라면 //+adapter에서 한번 더 체크 (댓쓴이가 로그인유저인지)
                //adapter.updateCompletdTv(0)
                //adapter.updateAcceptBtnv(0)
                //adapter.updateCommentMenuv(1)
                //disappearCommentForm()

                //+일반회원라면
                adapter!!.updateCompletdTv(0)
                adapter!!.updateAcceptBtnv(0)
                adapter!!.updateCommentMenuv(0)
                appearCommentForm()
            }
        } else { //모집완료
            adapter!!.updateCompletdTv(1)
            adapter!!.updateAcceptBtnv(0)
            adapter!!.updateCommentMenuv(0)
        }
    }

    fun callMenteePostAndComment() {
        api.requestAPostOfMentee(pickId).enqueue(object : Callback<PickResponse> {
            override fun onResponse(
                call: Call<PickResponse>,
                response: Response<PickResponse>
            ) {
                val body = response.body()
                if (body != null) {
                    if (body.code == 1000 && body.result.isNotEmpty()) {
                        Log.d("멘티 구인글 불러오기", "성공")
                        infoPost = body.result
                        postOfContents = infoPost.get(0)
                        //+ val infoPostUser: User = User(infoPost.userId)
                        infoPostUserId = infoPost.get(0).userId
                        val infoPostUserRole: Int = infoPost.get(0).role

                        viewBinding.tv.text = "멘토님"
                        viewBinding.careerTv.text = "멘티 수준: "
                        viewBinding.career.text = infoPost.get(0).menteeLevel
                        postCommonPart()
                    } else {
                        Log.d("멘티 구인글 불러오기", "실패 result가 null이라서.. 글쓴이일 가능성 있음")
                        callMentorPostAndComment()
                    }
                }
            }

            override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                val message = if (t.message != null) t.message else "An error occurred!"
                Log.d("멘티 구인글 불러오기 실패 오류 메세지:", message.toString())
            }
        })

        api.requestMenteeComments(pickId).enqueue(object : Callback<PickCommentResponse> {
            override fun onResponse(
                call: Call<PickCommentResponse>,
                response: Response<PickCommentResponse>
            ) {
                val body = response.body()
                if (body != null) {
                    if (body.code == 1000 && body.result.isNotEmpty()) {
                        commentCommonPart(body.result)
                    } else {
                        Log.d("멘티 댓글 불러오기", "실패")
                    }
                }
            }

            override fun onFailure(call: Call<PickCommentResponse>, t: Throwable) {
                Log.d("멘티 댓글 불러오기", "실패")
            }
        })
    }
    fun callMentorPostAndComment() {
        api.requestAPostOfMentor(pickId).enqueue(object : Callback<PickResponse> {
            override fun onResponse(
                call: Call<PickResponse>,
                response: Response<PickResponse>
            ) {
                val body = response.body()
                if (body != null) {
                    if (body.code == 1000 && body.result.isNotEmpty()) {
                        Log.d("멘토 구인글 불러오기", "성공")
                        infoPost = body.result
                        postOfContents = infoPost.get(0)
                        //+ val infoPostUser: User = User(infoPost.userId)
                        infoPostUserId = infoPost.get(0).userId
                        val infoPostUserRole: Int = infoPost.get(0).role

                        viewBinding.tv.text = "멘티님"//글쓴이 말하는 거
                        viewBinding.careerTv.text = "멘토 경력: "
                        viewBinding.career.text = infoPost.get(0).mentorCareer
                        postCommonPart()

                    } else {
                        Log.d("멘토 구인글 불러오기", "실패 result가 null이라서 그럼 글쓴이일 가능성 있음")
                        callMenteePostAndComment()
                    }
                }
            }
            override fun onFailure(call: Call<PickResponse>, t: Throwable) {
                val message = if (t.message != null) t.message else "An error occurred!"
                Log.d("멘토 구인글 불러오기 실패 오류 메세지:", message.toString())
                Toast.makeText(this@RequestMentoringActivity, message, Toast.LENGTH_SHORT).show()
            }
        })
        //retrofit (get 댓글)
        api.requestMentorComments(pickId).enqueue(object: Callback<PickCommentResponse> {
            override fun onResponse(call: Call<PickCommentResponse>, response: Response<PickCommentResponse>) {
                val body = response.body()
                if(body != null) {
                    if(body.code == 1000 && body.result.isNotEmpty()) {
                        commentCommonPart(body.result)
                    }
                    else {
                        Log.d("멘토 댓글 불러오기","실패")
                    }
                }
            }
            override fun onFailure(call: Call<PickCommentResponse>, t: Throwable) {
                val message = if (t.message != null) t.message else "An error occurred!"
                Log.d("멘토 댓글 불러오기", message.toString())
                Toast.makeText(this@RequestMentoringActivity, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    //채팅기능에서 메세지를 입력 후 전송 버튼을 누를 때 키보드 내리기
    fun closeKeyboard() {
        var view = this.currentFocus

        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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