package com.app.fr.leboncoin

class Constants {

    companion object {
        const val DEV_URL = "https://static.leboncoin.fr/"
    }

    enum class HTTPStatus constructor(val code: Int) {
        BAD_REQUEST(400),
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        NOT_FOUND(404),
        INTERNAL_SERVER_ERROR(500);

        companion object {
            fun from(findValue: Int): HTTPStatus =
                HTTPStatus.values().first { it.code == findValue }
        }
    }


}