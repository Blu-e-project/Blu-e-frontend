package com.example.blu_e.data

import android.graphics.Bitmap
import java.sql.Timestamp

data class Report(var id: Int) {
    val reportId
        get() = id
    var userId: Int = 0
    var targetId: Int = 0
    var title: String = ""
    var contents: String = ""
    var image: String = ""
    var createdAt: Timestamp = Timestamp(0)
    var updatedAt: Timestamp = Timestamp(0)
}
