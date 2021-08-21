package libraries.utils.base


import com.google.gson.annotations.SerializedName

data class ValidationResponse(
    @SerializedName("ctx")
    val ctx: Ctx,
    @SerializedName("loc")
    val loc: List<String>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("type")
    val type: String
)

data class Ctx(
    @SerializedName("limit_value")
    val limitValue: Int
)