package com.sudansh.hopinchallenge

import com.sudansh.hopinchallenge.data.remote.model.ExchangeResponse
import com.sudansh.hopinchallenge.data.remote.model.StageResponse
import com.sudansh.hopinchallenge.data.remote.model.StatusResponse
import retrofit2.http.*

interface HopinService {
    @POST("users/sso")
    @Headers("Content-Type: application/json")
    suspend fun exchangeToken(
        @Header("Cookie") userToken: String,
        @Query("event_slug") eventSlug: String
    ): ExchangeResponse

    @GET("api/v2/events/{eventId}/stages")
    suspend fun stages(
        @Header("Authorization") sessionToken: String,
        @Path("eventId") eventId: String
    ): StageResponse

    @GET("api/v2/events/{eventId}/studio/{uuid}/status")
    suspend fun status(
        @Header("Authorization") sessionToken: String,
        @Path("eventId") eventId: String,
        @Path("uuid") uuid: String
    ): StatusResponse
}