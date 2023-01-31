package com.example.blu_e.data

import java.sql.Timestamp

data class ListInMyPageData (val listNum: Int, var settingTitle: String) {
    constructor(listNum: Int) :
            this(listNum, "")
}