package com.padcmyanmar.mmhealthcare_kotlin_zmp.events

class FailHealthCareList {

    class ApiErrorEvent(val throwable: Throwable? = null) {
        fun getErrorMsg(): String? {
            if (throwable == null) {
                return "Null throwable in Error"
            } else {
                return throwable.message
            }
        }
    }


}