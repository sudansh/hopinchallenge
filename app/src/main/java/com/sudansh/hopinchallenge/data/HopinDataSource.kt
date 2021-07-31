package com.sudansh.hopinchallenge.data

import com.sudansh.hopinchallenge.HopinService
import com.sudansh.hopinchallenge.data.remote.model.ExchangeResponse

class HopinDataSource(
    private val hopinService: HopinService
) {

    /**
     * Get event Id
     *
     * @param token session token
     * @param eventSlug
     * @return
     */
    suspend fun getExchangeToken(token: String, eventSlug: String): ExchangeResponse {
        val headerMap = "user.token=$token"
        return hopinService.exchangeToken(headerMap, eventSlug)
    }

    /**
     * Gets uuid of the event
     *
     * @param token session token
     * @param eventId
     * @return
     */
    suspend fun getUuid(token: String, eventId: String): String {
        val headerToken = "Bearer $token"
        val response = hopinService.stages(headerToken, eventId)
        return response.stages?.firstOrNull()?.uuid.orEmpty()
    }

    /**
     * Gets stream url of the event broadcast
     *
     * @param token session token
     * @param eventId
     * @param uuid
     * @return
     */
    suspend fun getStreamUrl(token: String, eventId: String, uuid: String): String {
        val headerToken = "Bearer $token"
        val response = hopinService.status(headerToken, eventId, uuid = uuid)
        val channels = response.video_channels.orEmpty()
        return channels.firstOrNull { it.status == "active" }?.stream_url.orEmpty()
    }

}