package com.aravindh.andriodbasesetup.model

data class LoginResponse(
    val auth_token: String?,
    val hasura_id: Int?,
    val hasura_roles: List<String?>?,
    val username: String?
)