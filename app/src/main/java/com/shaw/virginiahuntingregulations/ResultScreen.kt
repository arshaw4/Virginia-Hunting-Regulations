package com.shaw.virginiahuntingregulations

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class ResultScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val month = intent.getIntExtra("month", -1)
        val day = intent.getIntExtra("day", -1)
        val year = intent.getIntExtra("year", -1)
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val tealValue = intent.getIntExtra("tealValue", -1)
        val mergansersValue = intent.getIntExtra("mergansersValue", -1)
        val mallardDrakeValue = intent.getIntExtra("mallardDrakeValue", -1)
        val mallardHenValue = intent.getIntExtra("mallardHenValue", -1)
        val woodDuckValue = intent.getIntExtra("woodDuckValue", -1)
        val blackDuckValue = intent.getIntExtra("blackDuckValue", -1)
        val scaupValue = intent.getIntExtra("scaupValue", -1)
        val redheadValue = intent.getIntExtra("redheadValue", -1)
        val canvasbackValue = intent.getIntExtra("canvasbackValue", -1)
        val pintailValue = intent.getIntExtra("pintailValue", -1)
        val mottledDuckValue = intent.getIntExtra("mottledDuckValue", -1)
        val fulvousWhistlingValue = intent.getIntExtra("fulvousWhistlingValue", -1)
        val cootValue = intent.getIntExtra("cootValue", -1)
        val geeseValue = intent.getIntExtra("geeseValue", -1)
        val scoterValue = intent.getIntExtra("scoterValue", -1)
        val eiderDrakeValue = intent.getIntExtra("eiderDrakeValue", -1)
        val eiderHenValue = intent.getIntExtra("eiderHenValue", -1)
        val longTailValue = intent.getIntExtra("longTailValue", -1)
        val huntZoneSwitchValue = intent.getBooleanExtra("huntZoneSwitchValue", false)
        val i95ZoneSwitchValue = intent.getBooleanExtra("i95ZoneSwitchValue", false)
        findViewById<TextView>(R.id.tealResults).text =
            tealResults(calendar, tealValue, i95ZoneSwitchValue)
        findViewById<TextView>(R.id.mergansersResults).text =
            mergansersResults(calendar, mergansersValue)
        findViewById<TextView>(R.id.cootResults).text = cootResults(calendar, cootValue)
        findViewById<TextView>(R.id.geeseResults).text =
            geeseResults(calendar, geeseValue, huntZoneSwitchValue)
        findViewById<TextView>(R.id.duckResults).text = duckResults(
            calendar,
            mallardDrakeValue,
            mallardHenValue,
            woodDuckValue,
            blackDuckValue,
            scaupValue,
            redheadValue,
            canvasbackValue,
            pintailValue,
            mottledDuckValue,
            fulvousWhistlingValue,
            scoterValue,
            eiderDrakeValue,
            eiderHenValue,
            longTailValue
        )
    }

    private fun dateInRange(
        startRange: Calendar,
        endRange: Calendar,
        checkDate: Calendar
    ): Boolean {
        return checkDate.after(startRange) && checkDate.before(endRange)
    }

    private fun tealResults(calendar: Calendar, tealInput: Int, i95SwitchResult: Boolean): String {
        val seasonStart = Calendar.getInstance()
        val seasonEnd = Calendar.getInstance()

        if (i95SwitchResult) {
            //if its true it should be west of i95
            //September 21th of current year
            seasonStart.set(calendar.get(Calendar.YEAR), 8, 21, 0, 0, 0)
            println(seasonStart.toString())
            //September 30th of current year
            seasonEnd.set(calendar.get(Calendar.YEAR), 8, 30, 23, 59, 59)
            return if (dateInRange(seasonStart, seasonEnd, calendar)) {
                if (tealInput < 6) {
                    "You can bag " + (6 - tealInput) + " more Teal today"
                } else if (tealInput == 6) {
                    "You have reached the daily Teal bag limit"
                } else {
                    "You have surpassed the daily Teal bag limit by " + (tealInput - 6)
                }
            } else {
                "Teal are not in season for your area"
            }
        } else {
            //if its false its east of i95
            //September 17th of current year
            seasonStart.set(calendar.get(Calendar.YEAR), 8, 17, 0, 0, 0)
            //September 30th of current year
            seasonEnd.set(calendar.get(Calendar.YEAR), 8, 30, 23, 59, 59)
            return if (dateInRange(seasonStart, seasonEnd, calendar)) {
                if (tealInput < 6) {
                    "You can bag " + (6 - tealInput) + " more Teal today"
                } else if (tealInput == 6) {
                    "You have reached the daily Teal bag limit"
                } else {
                    "You have surpassed the daily Teal bag limit by " + (tealInput - 6)
                }
            } else {
                "Teal are not in season for your area"
            }
        }
    }

    private fun mergansersResults(calendar: Calendar, mergansersInput: Int): String {
        var inOneOfRanges = false
        val seasonStart = Calendar.getInstance()
        val seasonEnd = Calendar.getInstance()
//        October 6-9
        seasonStart.set(calendar.get(Calendar.YEAR), 9, 6, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 9, 9, 23, 59, 59)
        inOneOfRanges = dateInRange(seasonStart, seasonEnd, calendar)
//        November 15-26
        seasonStart.set(calendar.get(Calendar.YEAR), 10, 15, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 10, 26, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
//        December 19-January 31 we have to break up into december 19-31 and jan 1-31
        seasonStart.set(calendar.get(Calendar.YEAR), 11, 19, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 11, 31, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        seasonStart.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 0, 31, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        return if (inOneOfRanges) {
            if (mergansersInput < 5) {
                "You can bag " + (5 - mergansersInput) + " more Mergansers today"
            } else if (mergansersInput == 5) {
                "You have reached the daily Mergansers bag limit"
            } else {
                "You have surpassed the daily Mergansers bag limit by " + (mergansersInput - 5)
            }
        } else {
            "Mergansers are not in season"
        }
    }

    private fun cootResults(calendar: Calendar, cootInput: Int): String {
        var inOneOfRanges = false
        val seasonStart = Calendar.getInstance()
        val seasonEnd = Calendar.getInstance()
        //October 6-9
        seasonStart.set(calendar.get(Calendar.YEAR), 9, 6, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 9, 9, 23, 59, 59)
        inOneOfRanges = dateInRange(seasonStart, seasonEnd, calendar)
        //November 15-26
        seasonStart.set(calendar.get(Calendar.YEAR), 10, 15, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 10, 26, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        //December 19-January 31
        seasonStart.set(calendar.get(Calendar.YEAR), 11, 19, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 11, 31, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        seasonStart.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 0, 31, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        return if (inOneOfRanges) {
            if (cootInput < 15) {
                "You can bag " + (15 - cootInput) + " more Coot today"
            } else if (cootInput == 15) {
                "You have reached the daily Coot bag limit"
            } else {
                "You have surpassed the daily Coot bag limit by " + (cootInput - 15)
            }
        } else {
            "Coot are not in season"
        }
    }

    private fun geeseResults(
        calendar: Calendar,
        geeseInput: Int,
        huntZoneValue: Boolean
    ): String {
        //atlantic is false
        val seasonStart = Calendar.getInstance()
        val seasonEnd = Calendar.getInstance()
        //check September Canada Goose first, September 1-25
        seasonStart.set(calendar.get(Calendar.YEAR), 8, 1, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 8, 25, 23, 59, 59)
        if (dateInRange(seasonStart, seasonEnd, calendar)) {
            return if (geeseInput < 10) {
                "You can bag " + (10 - geeseInput) + " more Geese today"
            } else if (geeseInput == 10) {
                "You have reached the daily Geese bag limit"
            } else {
                "You have surpassed the daily Geese bag limit by " + (geeseInput - 10)
            }
        } else {
            //now check for normal geese season
            //Atlantic Population Zone (AP) Seasons
            if (!huntZoneValue) {
                var inOneOfRanges = false
                //November 22-26
                seasonStart.set(calendar.get(Calendar.YEAR), 10, 22, 0, 0, 0)
                seasonEnd.set(calendar.get(Calendar.YEAR), 10, 26, 23, 59, 59)
                inOneOfRanges = dateInRange(seasonStart, seasonEnd, calendar)
                //December 23 – January 31
                seasonStart.set(calendar.get(Calendar.YEAR), 11, 23, 0, 0, 0)
                seasonEnd.set(calendar.get(Calendar.YEAR), 11, 31, 23, 59, 59)
                inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
                seasonStart.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0)
                seasonEnd.set(calendar.get(Calendar.YEAR), 0, 31, 23, 59, 59)
                inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
                return if (inOneOfRanges) {
                    if (geeseInput < 2) {
                        "You can bag " + (2 - geeseInput) + " more Geese today"
                    } else if (geeseInput == 2) {
                        "You have reached the daily Geese bag limit"
                    } else {
                        "You have surpassed the daily Geese bag limit by " + (geeseInput - 2)
                    }
                } else {
                    "Geese are not in season"
                }
            } else {
                //Resident Population Zone (RP) Seasons
                var inOneOfRanges = false
                //November 15-26
                seasonStart.set(calendar.get(Calendar.YEAR), 10, 15, 0, 0, 0)
                seasonEnd.set(calendar.get(Calendar.YEAR), 10, 26, 23, 59, 59)
                inOneOfRanges = dateInRange(seasonStart, seasonEnd, calendar)
                //December 19 – February 24
                seasonStart.set(calendar.get(Calendar.YEAR), 11, 19, 0, 0, 0)
                seasonEnd.set(calendar.get(Calendar.YEAR), 11, 31, 23, 59, 59)
                inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
                seasonStart.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0)
                seasonEnd.set(calendar.get(Calendar.YEAR), 1, 24, 23, 59, 59)
                inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
                return if (inOneOfRanges) {
                    if (geeseInput < 5) {
                        "You can bag " + (5 - geeseInput) + " more Geese today"
                    } else if (geeseInput == 5) {
                        "You have reached the daily Geese bag limit"
                    } else {
                        "You have surpassed the daily Geese bag limit by " + (geeseInput - 5)
                    }
                } else {
                    "Geese are not in season"
                }
            }
        }
    }

    private fun duckResults(
        calendar: Calendar,
        mallardDrakeInput: Int,
        mallardHenInput: Int,
        woodDuckInput: Int,
        blackDuckInput: Int,
        scaupInput: Int,
        redheadInput: Int,
        canvasbackInput: Int,
        pintailInput: Int,
        mottledDuckInput: Int,
        fulvousInput: Int,
        scoterInput: Int,
        eiderDrakeInput: Int,
        eiderHenInput: Int,
        longTailInput: Int
    ): String {
        val returnString = StringBuilder()
        val seasonStart = Calendar.getInstance()
        val seasonEnd = Calendar.getInstance()
        var inOneOfRanges = false
        var blackDuckClosed = false;
        //October 6-9 (Black duck closed)
        seasonStart.set(calendar.get(Calendar.YEAR), 9, 6, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 9, 9, 23, 59, 59)
        blackDuckClosed = dateInRange(seasonStart, seasonEnd, calendar)
        inOneOfRanges = inOneOfRanges || blackDuckClosed
        //November 15-26
        seasonStart.set(calendar.get(Calendar.YEAR), 10, 15, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 10, 26, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        //December 19-January 31
        seasonStart.set(calendar.get(Calendar.YEAR), 11, 19, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 11, 31, 23, 59, 59)
        inOneOfRanges = dateInRange(seasonStart, seasonEnd, calendar)
        seasonStart.set(calendar.get(Calendar.YEAR), 0, 1, 0, 0, 0)
        seasonEnd.set(calendar.get(Calendar.YEAR), 0, 31, 23, 59, 59)
        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        if (inOneOfRanges) {
            returnString.append("You can bag up to 6 total ducks of the following options: \n")
            if (mallardDrakeInput + mallardHenInput == 4) {
                returnString.append("You have reached the daily Mallard bag limit, ")
                if (mallardHenInput > 2) {
                    returnString.append("You have surpassed the daily Mallard Hen bag limit by " + (mallardHenInput - 2) + ", ")
                    //dont check for hen being under 2 because we have already reached limit
                }
            } else if (mallardDrakeInput + mallardHenInput < 4) {
                returnString.append("You can bag up to " + (4 - (mallardDrakeInput + mallardHenInput)) + " more Mallard Drakes, ")
                if (mallardHenInput > 2) {
                    returnString.append("You have surpassed the daily Mallard Hen bag limit by " + (mallardHenInput - 2) + ", ")
                } else if (mallardHenInput == 2) {
                    returnString.append("You have reached the daily Mallard Hen bag limit, ")
                } else {
                    returnString.append("You can bag up to " + (2 - mallardHenInput) + " more Mallard Hens, ")
                }


            } else {
                returnString.append("You have surpassed the daily Mallard bag limit by " + (mallardDrakeInput + mallardHenInput - 4) + ", ")
                if (mallardHenInput > 2) {
                    returnString.append("You have surpassed the daily Mallard Hen bag limit by " + (mallardHenInput - 2) + ", ")
                }
            }
            if (woodDuckInput > 3) {
                returnString.append("You have surpassed the daily Wood Duck bag limit by " + (woodDuckInput - 3) + ", ")
            } else if (woodDuckInput == 3) {
                returnString.append("You have reached the daily Wood Duck bag limit, ")
            } else {
                returnString.append("You can bag up to " + (3 - woodDuckInput) + " more Wood Ducks, ")
            }
            if (blackDuckClosed) {
                returnString.append("Black Ducks are not in season October 6-9, ")
            } else {
                if (blackDuckInput > 2) {
                    returnString.append("You have surpassed the daily Black Duck bag limit by " + (blackDuckInput - 2) + ", ")
                } else if (blackDuckInput == 2) {
                    returnString.append("You have reached the daily Black Duck bag limit, ")
                } else {
                    returnString.append("You can bag up to " + (2 - blackDuckInput) + ", ")
                }
            }
            var scaupTwoPerDay = false;
            //January 12-31
            seasonStart.set(calendar.get(Calendar.YEAR), 0, 12, 0, 0, 0)
            seasonEnd.set(calendar.get(Calendar.YEAR), 0, 31, 23, 59, 59)
            scaupTwoPerDay = dateInRange(seasonStart, seasonEnd, calendar);
            if (scaupTwoPerDay) {
                //limit is 2
            } else {
                //limit is 1
            }
            return returnString.toString()
        }
        //October 21 & February 3 – Youth and Veterans Waterfowl Hunting Days
//        seasonStart.set(calendar.get(Calendar.YEAR), 9, 21, 0, 0, 0)
//        seasonEnd.set(calendar.get(Calendar.YEAR), 9, 21, 23, 59, 59)
//        inOneOfRanges = dateInRange(seasonStart, seasonEnd, calendar)
//        seasonStart.set(calendar.get(Calendar.YEAR), 1, 3, 0, 0, 0)
//        seasonEnd.set(calendar.get(Calendar.YEAR), 1, 3, 23, 59, 59)
//        inOneOfRanges = inOneOfRanges || dateInRange(seasonStart, seasonEnd, calendar)
        //not in any range
        else {
            return "Ducks are not in season (Mallard, Wood, Black, Scaup, Redhead, Canvasback, Pintail, Mottled, Fulvous, Scoter, Eider, and Long-tail"
        }
    }
}