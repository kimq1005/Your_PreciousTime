package com.example.your_precioustime.ObjectManager

class citycodeCallObject {

    companion object{
       val citycodeCallObject = citycodeCallObject()
    }


    fun citycode(cityname:String): String {
        when(cityname){
            "수원"->{
                return "31010"
            }

            "성남"->{
                return "31020"
            }

            "안양 "->{
                return "31040"
            }

            "부천"->{
                return "31050"
            }

            "광명"->{
                return "31060"
            }

            "평택"->{
                return "31070"
            }

            "동두천"->{
                return "31080"
            }

            "안산"->{
                return "31090"
            }

            "고양"->{
                return "31100"
            }
            "과천"->{
                return "31110"
            }

            "구리"->{
                return "31120"
            }

            "남양주"->{
                return "31130"
            }

            "오산"->{
                return "31140"
            }

            "시흥"->{
                return "31150"
            }

            "군포"->{
                return "31160"
            }

            "의왕"->{
                return "31170"
            }

            "하남"->{
                return "31180"
            }

            "용인"->{
                return "31190"
            }

            "파주"->{
                return "31200"
            }

            "이천"->{
                return "31210"
            }

            "안성"->{
                return "31220"
            }

            "김포"->{
                return "31230"
            }

            "화성"->{
                return "31240"
            }


            "광주"->{
                return "31250"
            }

            "양주"->{
                return "31260"
            }

            "포천"->{
                return "31270"
            }

            "여주"->{
                return "31320"
            }

            "연천"->{
                return "31350"
            }

            "가평"->{
                return "31370"
            }

            "양평"->{
                return "31380"
            }


        }
        return "31010"
    }


}