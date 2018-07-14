package com.padcmyanmar.mmhealthcare_kotlin_zmp.data.vos

import com.google.gson.annotations.SerializedName

class HealthCareVO(@SerializedName("id") var healthCareId: Int,
                   @SerializedName("title") var healthCareTitle: String,
                   @SerializedName("image") var healthCareImage: String,
                   @SerializedName("author") var authorList: AuthorVO,
                   @SerializedName("short-description") var shortDesc: String,
                   @SerializedName("published-date") var publishedDate: String,
                   @SerializedName("complete-url") var completeUrl: String
) {
}