package com.yyg.common.data.api

data class ApiLogin(
    val userName: String,
    val password: String,
    val clientType: String = "android"
)