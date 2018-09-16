package com.yuricfurusho.yurigiphyapi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class TrendingResponse (
        val data: List<Data>,
        val pagination: Pagination,
        val meta: Meta) : Serializable


data class Data(
        var favorited: Boolean,
        val type: String,
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
        val user: User,
        val images: Images,
        val title: String,
        @SerializedName("_score")
        val score: Int
)

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
)

data class Images(
        @SerializedName("fixed_height_still")
        val fixedHeightStill: FixedHeightStill,
        @SerializedName("original_still")
        val originalStill: OriginalStill,
        @SerializedName("fixed_width")
        val fixedWidth: FixedWidth,
        @SerializedName("fixed_height_small_still")
        val fixedHeightSmallStill: FixedHeightSmallStill,
        @SerializedName("fixed_height_downsampled")
        val fixedHeightDownsampled: FixedHeightDownsampled,
        val preview: Preview,
        @SerializedName("fixed_height_small")
        val fixedHeightSmall: FixedHeightSmall,
        @SerializedName("downsized_still")
        val downsizedStill: DownsizedStill,
        val downsized: Downsized,
        @SerializedName("downsized_large")
        val downsizedLarge: DownsizedLarge,
        @SerializedName("fixed_width_small_still")
        val fixedWidthSmallStill: FixedWidthSmallStill,
        @SerializedName("preview_webp")
        val previewWebp: PreviewWebp,
        @SerializedName("fixed_width_still")
        val fixedWidthStill: FixedWidthStill,
        @SerializedName("fixed_width_small")
        val fixedWidthSmall: FixedWidthSmall,
        @SerializedName("downsized_small")
        val downsizedSmall: DownsizedSmall,
        @SerializedName("fixed_width_downsampled")
        val fixedWidthDownsampled: FixedWidthDownsampled,
        @SerializedName("downsized_medium")
        val downsizedMedium: DownsizedMedium,
        val original: Original,
        @SerializedName("fixed_height")
        val fixedHeight: FixedHeight,
        val hd: Hd,
        val looping: Looping,
        @SerializedName("original_mp4")
        val originalMp4: OriginalMp4,
        @SerializedName("preview_gif")
        val previewGif: PreviewGif,
        @SerializedName("480w_still")
        val wStill: WStill
)

data class WStill(
        val url: String,
        val width: String,
        val height: String
)

data class OriginalStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

data class DownsizedLarge(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

data class Preview(
        val width: String,
        val height: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
)

data class FixedWidthStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

data class DownsizedMedium(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

data class Looping(
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
)

data class FixedHeightStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

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
)

data class OriginalMp4(
        val width: String,
        val height: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
)

data class Downsized(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

data class PreviewGif(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

data class FixedHeightDownsampled(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val webp: String,
        @SerializedName("webp_size")
        val webpSize: String
)

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
)

data class DownsizedStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

data class FixedHeightSmallStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

data class Hd(
        val width: String,
        val height: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
)

data class DownsizedSmall(
        val width: String,
        val height: String,
        val mp4: String,
        @SerializedName("mp4_size")
        val mp4Size: String
)

data class FixedWidthDownsampled(
        val url: String,
        val width: String,
        val height: String,
        val size: String,
        val webp: String,
        @SerializedName("webp_size")
        val webpSize: String
)

data class PreviewWebp(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

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
)

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
)

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
)

data class FixedWidthSmallStill(
        val url: String,
        val width: String,
        val height: String,
        val size: String
)

data class Pagination(
        @SerializedName("total_count")
        val totalCount: Int,
        val count: Int,
        val offset: Int
)

data class Meta(
        val status: Int,
        val msg: String,
        @SerializedName("response_id")
        val responseId: String
)
