package com.example.heartstone_repository.model

import com.google.gson.annotations.SerializedName

data class LocalesResponse(
    @SerializedName("DE_DE")
    val deDe: String,
    @SerializedName("EN_GB")
    val enGb: String,
    @SerializedName("EN_US")
    val enUs: String,
    @SerializedName("ES_ES")
    val esEs: String,
    @SerializedName("ES_MX")
    val esMx: String,
    @SerializedName("FR_FR")
    val frFr: String,
    @SerializedName("IT_IT")
    val itIt: String,
    @SerializedName("JA_JP")
    val jaJp: String,
    @SerializedName("KO_KR")
    val koKr: String,
    @SerializedName("PL_PL")
    val plPl: String,
    @SerializedName("PT_BR")
    val ptBr: String,
    @SerializedName("RU_RU")
    val ruRu: String,
    @SerializedName("TH_TH")
    val thTh: String,
    @SerializedName("ZH_CN")
    val zhCn: String,
    @SerializedName("ZH_TW")
    val zhTw: String
) {
    fun toList(): List<String> {
        return listOf(deDe, enGb, enUs, esEs, esMx, frFr, itIt, jaJp, koKr, plPl, ptBr, ruRu, thTh, zhCn, zhTw)
    }
}
