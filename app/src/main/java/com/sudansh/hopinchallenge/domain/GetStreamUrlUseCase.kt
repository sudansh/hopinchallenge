package com.sudansh.hopinchallenge.domain

import com.auth0.android.jwt.JWT
import com.sudansh.hopinchallenge.data.HopinDataSource

class GetStreamUrlUseCase(
    private val hopinDataSource: HopinDataSource
) {
    class Params(val eventSlug: String, val sessionToken: String)

    suspend operator fun invoke(params: Params): String {
        val response = hopinDataSource.getExchangeToken(params.sessionToken, params.eventSlug)
        val jwt = JWT(response.token)
        val eventId = jwt.getClaim("event_id").asString().orEmpty()
        val uuid = hopinDataSource.getUuid(response.token, eventId)
        return hopinDataSource.getStreamUrl(response.token, eventId, uuid)
    }
}