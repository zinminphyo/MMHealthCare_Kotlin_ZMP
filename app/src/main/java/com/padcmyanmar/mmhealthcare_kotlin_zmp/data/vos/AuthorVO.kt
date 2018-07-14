package com.padcmyanmar.mmhealthcare_kotlin_zmp.data.vos

import com.google.gson.annotations.SerializedName

class AuthorVO(@SerializedName("author-id")var authorId:Int ,
               @SerializedName("author-name")var authorName:String ,
               @SerializedName("author-picture")var authorPicture:String) {
}