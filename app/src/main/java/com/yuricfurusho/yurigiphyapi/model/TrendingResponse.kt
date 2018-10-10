package com.yuricfurusho.yurigiphyapi.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.File
import java.io.Serializable


data class TrendingResponse (
        val data: MutableList<Data>,
        val pagination: Pagination?,
        val meta: Meta) : Serializable

@Entity(tableName = "data")
data class Data(
        var favorited: Boolean,
        var filePath: String?,
        val type: String?,
        @PrimaryKey
        val id: String,
        val slug: String?,
        val url: String?,
        @SerializedName("bitly_gif_url")
        val bitlyGifUrl: String?,
        @SerializedName("bitly_url")
        val bitlyUrl: String?,
        @SerializedName("embed_url")
        val embedUrl: String?,
        val username: String?,
        val source: String?,
        val rating: String?,
        @SerializedName("content_url")
        val contentUrl: String?,
        @SerializedName("source_tld")
        val sourceTld: String?,
        @SerializedName("source_post_url")
        val sourcePostUrl: String?,
        @SerializedName("is_sticker")
        val isSticker: Int?,
        @SerializedName("import_datetime")
        val importDatetime: String?,
        @SerializedName("trending_datetime")
        val trendingDatetime: String?,
        @Embedded(prefix = "user_")
        val user: User?,
        @Embedded(prefix = "images_")
        val images: Images?,
        val title: String?,
        @Embedded(prefix = "_score_")
        @SerializedName("_score")
        val score: Any?
) : Serializable

@Entity
data class User(
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("banner_url")
        val bannerUrl: String?,
        @SerializedName("banner_image")
        val bannerImage: String?,
        @SerializedName("profile_url")
        val profileUrl: String?,
        val username: String?,
        @SerializedName("display_name")
        val displayName: String?,
        @SerializedName("is_verified")
        val isVerified: Boolean
) : Serializable

@Entity
data class Images(
        @Embedded(prefix = "fixed_height_still_")
        @SerializedName("fixed_height_still")
        val fixedHeightStill: FixedHeightStill?,
        @Embedded(prefix = "original_still_")
        @SerializedName("original_still")
        val originalStill: OriginalStill?,
        @Embedded(prefix = "fixed_width_")
        @SerializedName("fixed_width")
        val fixedWidth: FixedWidth?,
        @Embedded(prefix = "fixed_height_small_still_")
        @SerializedName("fixed_height_small_still")
        val fixedHeightSmallStill: FixedHeightSmallStill?,
        @Embedded(prefix = "images_")
        @SerializedName("fixed_height_downsampled")
        val fixedHeightDownsampled: FixedHeightDownsampled?,
        @Embedded(prefix = "preview_")
        val preview: Preview?,
        @Embedded(prefix = "fixed_height_small_")
        @SerializedName("fixed_height_small")
        val fixedHeightSmall: FixedHeightSmall?,
        @Embedded(prefix = "downsized_still_")
        @SerializedName("downsized_still")
        val downsizedStill: DownsizedStill?,
        @Embedded(prefix = "downsized_")
        val downsized: Downsized?,
        @Embedded(prefix = "downsized_large_")
        @SerializedName("downsized_large")
        val downsizedLarge: DownsizedLarge?,
        @Embedded(prefix = "fixed_width_small_still_")
        @SerializedName("fixed_width_small_still")
        val fixedWidthSmallStill: FixedWidthSmallStill?,
        @Embedded(prefix = "preview_webp_")
        @SerializedName("preview_webp")
        val previewWebp: PreviewWebp?,
        @Embedded(prefix = "fixed_width_still_")
        @SerializedName("fixed_width_still")
        val fixedWidthStill: FixedWidthStill?,
        @Embedded(prefix = "fixed_width_small_")
        @SerializedName("fixed_width_small")
        val fixedWidthSmall: FixedWidthSmall?,
        @Embedded(prefix = "downsized_small_")
        @SerializedName("downsized_small")
        val downsizedSmall: DownsizedSmall?,
        @Embedded(prefix = "fixed_width_downsampled_")
        @SerializedName("fixed_width_downsampled")
        val fixedWidthDownsampled: FixedWidthDownsampled?,
        @Embedded(prefix = "downsized_medium_")
        @SerializedName("downsized_medium")
        val downsizedMedium: DownsizedMedium?,
        @Embedded(prefix = "original_")
        val original: Original?,
        @Embedded(prefix = "fixed_height_")
        @SerializedName("fixed_height")
        val fixedHeight: FixedHeight?,
        @Embedded(prefix = "hd_")
        val hd: Hd?,
        @Embedded(prefix = "looping_")
        val looping: Looping?,
        @Embedded(prefix = "original_mp4_")
        @SerializedName("original_mp4")
        val originalMp4: OriginalMp4?,
        @Embedded(prefix = "preview_gif_")
        @SerializedName("preview_gif")
        val previewGif: PreviewGif?,
        @Embedded(prefix = "_480w_still_")
        @SerializedName("_480w_still")
        val wStill: WStill?
) : Serializable

@Entity
data class WStill(
        val url: String?,
        val width: String?,
        val height: String?
) : Serializable

@Entity
data class OriginalStill(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
) : Serializable

@Entity
data class DownsizedLarge(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
) : Serializable

@Entity
data class Preview(
        val width: String?,
        val height: String?,
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?
) : Serializable

@Entity
data class FixedWidthStill(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
)

@Entity
data class DownsizedMedium(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
) : Serializable

@Entity
data class Looping(
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?
) : Serializable

@Entity
data class FixedHeightStill(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
) : Serializable

@Entity
data class Original(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?,
        val frames: String?,
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?,
        val webp: String?,
        @SerializedName("webp_size")
        val webpSize: String?,
        val hash: String?
) : Serializable

@Entity
data class OriginalMp4(
        val width: String?,
        val height: String?,
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?
)

@Entity
data class Downsized(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String
) : Serializable

@Entity
data class PreviewGif(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
) : Serializable

@Entity
data class FixedHeightDownsampled(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?,
        val webp: String?,
        @SerializedName("webp_size")
        val webpSize: String?
) : Serializable

@Entity
data class FixedHeightSmall(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?,
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?,
        val webp: String?,
        @SerializedName("webp_size")
        val webpSize: String?
) : Serializable

@Entity
data class DownsizedStill(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
) : Serializable

@Entity
data class FixedHeightSmallStill(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
) : Serializable

@Entity
data class Hd(
        val width: String?,
        val height: String?,
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?
) : Serializable

@Entity
data class DownsizedSmall(
        val width: String?,
        val height: String?,
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?
) : Serializable

@Entity
data class FixedWidthDownsampled(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?,
        val webp: String?,
        @SerializedName("webp_size")
        val webpSize: String
) : Serializable

@Entity
data class PreviewWebp(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
) : Serializable

@Entity
data class FixedWidth(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?,
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?,
        val webp: String?,
        @SerializedName("webp_size")
        val webpSize: String?
) : Serializable

@Entity
data class FixedHeight(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?,
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?,
        val webp: String?,
        @SerializedName("webp_size")
        val webpSize: String?
) : Serializable

@Entity
data class FixedWidthSmall(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?,
        val mp4: String?,
        @SerializedName("mp4_size")
        val mp4Size: String?,
        val webp: String?,
        @SerializedName("webp_size")
        val webpSize: String?
) : Serializable

@Entity
data class FixedWidthSmallStill(
        val url: String?,
        val width: String?,
        val height: String?,
        val size: String?
) : Serializable

@Entity
data class Pagination(
        @SerializedName("total_count")
        val totalCount: Int?,
        val count: Int?,
        val offset: Int?
) : Serializable

@Entity
data class Meta(
        val status: Int?,
        val msg: String?,
        @SerializedName("response_id")
        val responseId: String?
) : Serializable
