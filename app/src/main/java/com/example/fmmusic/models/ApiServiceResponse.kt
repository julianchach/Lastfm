package com.example.fmmusic.models

import retrofit2.Response

class ApiServiceResponse <T> (var state: State, var response: Response<T>?, var error: Throwable?) {
}