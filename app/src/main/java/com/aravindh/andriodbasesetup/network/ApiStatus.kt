package com.aravindh.andriodbasesetup.network


/*API STATUS
* LOADING  -> API INTERACT WITH WEB SERVICE UNTIL THE RESPONSE BACK
* SUCCESS -> API GETTING SUCCESS STATE(200)
* FAILURE -> API GETTING FAILURE STATE(400,401,500)*/

enum class ApiStatus { LOADING, SUCCESS, FAILURE }