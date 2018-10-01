package com.yuricfurusho.yurigiphyapi.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.File
import java.io.Serializable


data class TrendingResponse (
        val data: List<Data>,
        val pagination: Pagination,
        val meta: Meta) : Serializable

@Entity
data class Data(
        var favorited: Boolean,
        var filePath: String,
        val type: String,
        @PrimaryKey
        val id: String,
        val slug: String,
        val url: String,
        @SerializedName("bitly_gif_url")
        val bitlyGifUrl: String,
        @SerializedName("bitly_url")
        val bitlyUrl: String,
        @SerializedName("embed_url")
        val embedUrl: String,
        val username: String,
        val source: String,
        val rating: String,
        @SerializedName("content_url")
        val contentUrl: String,
        @SerializedName("source_tld")
        val sourceTld: String,
        @SerializedName("source_post_url")
        val sourcePostUrl: String,
        @SerializedName("is_sticker")
        val isSticker: Int,
        @SerializedName("import_datetime")
        val importDatetime: String,
        @SerializedName("trending_datetime")
        val trendingDatetime: String,
        @Embedded(prefix = "User")
        val user: User,
        @Embedded
        val images: Images,
        val title: String,
        @Embedded
        @SerializedName("_score")
        val score: Any
) : Serializable

@Entity
data class User(
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("banner_url")
        val bannerUrl: String,
        @SerializedName("banner_image")
        val bannerImage: String,
        @SerializedName("profile_url")
        val profileUrl: String,
        val username: String,
        @SerializedName("display_name")
        val displayName: String,
        @SerializedName("is_verified")
        val isVerified: Boolean
) : Serializable

@Entity
data class Images(
        @Embedded(prefix = "fixed_height_still")
        @SerializedName("fixed_height_still")
        val fixedHeightStill: FixedHeightStill,
        @Embedded(prefix = "original_still")
        @SerializedName("original_still")
        val originalStill: OriginalStill,
        @Embedded(prefix = "fixed_width")
        @SerializedName("fixed_width")
        val fixedWidth: FixedWidth,
        @Embedded(prefix = "fixed_height_small_still")
        @SerializedName("fixed_height_small_still")
        val fixedHeightSmallStill: FixedHeightSmallStill,
        @Embedded(prefix = "images")
        @SerializedName("fixed_height_downsampled")
        val fixedHeightDownsampled: FixedHeightDownsampled,
        @Embedded(prefix = "Preview")
        val preview: Preview,
        @Embedded(prefix = "fixed_height_small")
        @SerializedName("fixed_height_small")
        val fixedHeightSmall: FixedHeightSmall,
        @Embedded(prefix = "downsized_still")
        @SerializedName("downsized_still")
        val downsizedStill: DownsizedStill,
        @Embedded(prefix = "Downsized")
        val downsized: Downsized,
        @Embedded(prefix = "downsized_large")
        @SerializedName("downsized_large")
        val downsizedLarge: DownsizedLarge,
        @Embedded(prefix = "fixed_width_small_still")
        @SerializedName("fixed_width_small_still")
        val fixedWidthSmallStill: FixedWidthSmallStill,
        @Embedded(prefix = "preview_webp")
        @SerializedName("preview_webp")
        val previewWebp: PreviewWebp,
        @Embedded(prefix = "fixed_width_still")
        @SerializedName("fixed_width_still")
        val fixedWidthStill: FixedWidthStill,
        @Embedded(prefix = "fixed_width_small")
        @SerializedName("fixed_width_small")
        val fixedWidthSmall: FixedWidthSmall,
        @Embedded(prefix = "downsized_small")
        @SerializedName("downsized_small")
        val downsizedSmall: DownsizedSmall,
        @Embedded(prefix = "fixed_width_downsampled")
        @SerializedName("fixed_width_downsampled")
        val fixedWidthDownsampled: FixedWidthDownsampled,
        @Embedded(prefix = "downsized_medium")
        @SerializedName("downsized_medium")
        val downsizedMedium: DownsizedMedium,
        @Embedded(prefix = "Original")
        val original: Original,
        @Embedded(prefix = "fixed_height")
        @SerializedName("fixed_height")
        val fixedHeight: FixedHeight,
        @Embedded(prefix = "Hd")
        val hd: Hd,
        @Embedded(prefix = "Looping")
        val looping: Looping,
        @Embedded(prefix = "original_mp4")
        @SerializedName("original_mp4")
        val originalMp4: OriginalMp4,
        @Embedded(prefix = "preview_gif")
        @SerializedName("preview_gif")
        val previewGif: PreviewGif,
        @Embedded(prefix = "480w_still")
        @SerializedName("480w_still")
        val wStill: WStill
) : Serializable

@Entity
data class WStill(
        val url: String,
        val width: String,
        val height: String
) : Serializable

@Entity
data class OriginalStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class DownsizedLarge(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class Preview(
        val width: String,
        val height: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
) : Serializable

@Entity
data class FixedWidthStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

@Entity
data class DownsizedMedium(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class Looping(
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
) : Serializable

@Entity
data class FixedHeightStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class Original(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val frames: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String,
        val webp: String,
        @SerializedName("webp_size")
        val webpSize: String,
        val hash: String
) : Serializable

@Entity
data class OriginalMp4(
        val width: String,
        val height: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
)

@Entity
data class Downsized(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class PreviewGif(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class FixedHeightDownsampled(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val webp: String,
        @SerializedName("webp_size")
        val webpSize: String
) : Serializable

@Entity
data class FixedHeightSmall(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String,
        val webp: String,
        @SerializedName("webp_size")
        val webpSize: String
) : Serializable

@Entity
data class DownsizedStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class FixedHeightSmallStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class Hd(
        val width: String,
        val height: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
) : Serializable

@Entity
data class DownsizedSmall(
        val width: String,
        val height: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
) : Serializable

@Entity
data class FixedWidthDownsampled(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val webp: String,
        @SerializedName("webp_size")
        val webpSize: String
) : Serializable

@Entity
data class PreviewWebp(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class FixedWidth(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String,
        val webp: String,
        @SerializedName("webp_size")
        val webpSize: String
) : Serializable

@Entity
data class FixedHeight(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String,
        val webp: String,
        @SerializedName("webp_size")
        val webpSize: String
) : Serializable

@Entity
data class FixedWidthSmall(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String,
        val webp: String,
        @SerializedName("webp_size")
        val webpSize: String
) : Serializable

@Entity
data class FixedWidthSmallStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
) : Serializable

@Entity
data class Pagination(
        @SerializedName("total_count")
        val totalCount: Int,
        val count: Int,
        val offset: Int
) : Serializable

@Entity
data class Meta(
        val status: Int,
        val msg: String,
        @SerializedName("response_id")
        val responseId: String
) : Serializable
