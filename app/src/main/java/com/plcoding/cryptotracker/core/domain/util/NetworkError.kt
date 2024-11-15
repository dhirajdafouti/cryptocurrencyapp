package com.plcoding.cryptotracker.core.domain.util

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    NO_INTERNET,
    TOO_MANY_REQUEST,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN,
}