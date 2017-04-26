package com.ivan.vts.mapper;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;
import com.ivan.vts.mapper.extended.GsonParser;
import com.ivan.vts.mapper.extended.Route;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    public static final String json = "{\n" +
            "   \"geocoded_waypoints\" : [\n" +
            "      {\n" +
            "         \"geocoder_status\" : \"OK\",\n" +
            "         \"place_id\" : \"ChIJ-TivaMFmQ0cRjoM9KpK9Gd8\",\n" +
            "         \"types\" : [ \"locality\", \"political\" ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"geocoder_status\" : \"OK\",\n" +
            "         \"place_id\" : \"ChIJvT-116N6WkcR5H4X8lxkuB0\",\n" +
            "         \"types\" : [ \"locality\", \"political\" ]\n" +
            "      }\n" +
            "   ],\n" +
            "   \"routes\" : [\n" +
            "      {\n" +
            "         \"bounds\" : {\n" +
            "            \"northeast\" : {\n" +
            "               \"lat\" : 46.1029798,\n" +
            "               \"lng\" : 20.4495317\n" +
            "            },\n" +
            "            \"southwest\" : {\n" +
            "               \"lat\" : 44.78642989999999,\n" +
            "               \"lng\" : 19.6651787\n" +
            "            }\n" +
            "         },\n" +
            "         \"copyrights\" : \"Map data ©2017 Google\",\n" +
            "         \"legs\" : [\n" +
            "            {\n" +
            "               \"distance\" : {\n" +
            "                  \"text\" : \"189 km\",\n" +
            "                  \"value\" : 188999\n" +
            "               },\n" +
            "               \"duration\" : {\n" +
            "                  \"text\" : \"2 hours 4 mins\",\n" +
            "                  \"value\" : 7411\n" +
            "               },\n" +
            "               \"end_address\" : \"Belgrade, Serbia\",\n" +
            "               \"end_location\" : {\n" +
            "                  \"lat\" : 44.78642989999999,\n" +
            "                  \"lng\" : 20.4486815\n" +
            "               },\n" +
            "               \"start_address\" : \"Subotica, Serbia\",\n" +
            "               \"start_location\" : {\n" +
            "                  \"lat\" : 46.1012613,\n" +
            "                  \"lng\" : 19.6651787\n" +
            "               },\n" +
            "               \"steps\" : [\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.2 km\",\n" +
            "                        \"value\" : 157\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 53\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 46.1023995,\n" +
            "                        \"lng\" : 19.6657473\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Head \\u003cb\\u003enortheast\\u003c/b\\u003e on \\u003cb\\u003ePartizanska\\u003c/b\\u003e toward \\u003cb\\u003eVase Stajića\\u003c/b\\u003e\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"{ckxGkz_wBOW_BoCiAZi@X\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 46.1012613,\n" +
            "                        \"lng\" : 19.6651787\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.2 km\",\n" +
            "                        \"value\" : 211\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 66\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 46.1029798,\n" +
            "                        \"lng\" : 19.6683412\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Gues House i Best Fast Food onto \\u003cb\\u003eVase Stajića\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Creative Line (on the right)\\u003c/div\\u003e\",\n" +
            "                     \"maneuver\" : \"turn-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"_kkxG}}_wBOgBy@eGACg@sC\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 46.1023995,\n" +
            "                        \"lng\" : 19.6657473\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.4 km\",\n" +
            "                        \"value\" : 409\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"2 mins\",\n" +
            "                        \"value\" : 106\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 46.099498,\n" +
            "                        \"lng\" : 19.6700538\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Turn \\u003cb\\u003eright\\u003c/b\\u003e at Fotokopirnica \\\"KONJ\\\" onto \\u003cb\\u003eĐure Đakovića\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by BALKANBET Kladionica (on the right in 350&nbsp;m)\\u003c/div\\u003e\",\n" +
            "                     \"maneuver\" : \"turn-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"snkxGcn`wBBAzF{BtDwA`CaA~B}@\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 46.1029798,\n" +
            "                        \"lng\" : 19.6683412\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"8.6 km\",\n" +
            "                        \"value\" : 8627\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"10 mins\",\n" +
            "                        \"value\" : 597\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 46.0573024,\n" +
            "                        \"lng\" : 19.7580656\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Continue onto \\u003cb\\u003eTrg Lazara Nešića\\u003c/b\\u003e/\\u003cb\\u003eRoute 11\\u003c/b\\u003e/\\u003cb\\u003eRoute 300\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003eContinue to follow Route 11/Route 300\\u003c/div\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by MESC (on the left)\\u003c/div\\u003e\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"{xjxGyx`wBtMcFbAe@nBeAr@q@hA}BfA}Bb@_A`IgQt@}A|AkDBEtAoC|A}C~BuEBCxAsC?Ab@c@tDyDh@i@zIyI@ApDyDj@_AdHuTTaAJk@Pg@b@y@z@{Bb@yATeARmBLuBXuBV_AVu@\\\\i@v@w@jAu@|FoE|AsAdAkAPSlAuAnFyGrG{H~GiIhEiFNQHIz@gAxFeHbDwEdA_Bt@_AhAcAn@o@f@u@`@_AVeARoAJ_BNsFl@qUf@_YN}JHmBPkAf@qBpCqLhGsWnH{ZvH}[Lk@hFkT`Kic@`@gBjHs]x@_Dx@oC~D_MLq@p@_C|@gE`AiE\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 46.099498,\n" +
            "                        \"lng\" : 19.6700538\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"160 km\",\n" +
            "                        \"value\" : 160034\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 hour 34 mins\",\n" +
            "                        \"value\" : 5648\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 44.8421754,\n" +
            "                        \"lng\" : 20.2498962\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Take the ramp onto \\u003cb\\u003eА1\\u003c/b\\u003e\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"cqbxG}~qwB`@m@HIFCHAP?PHbB~@j@VLDP@PAREbA]n@Kf@Ab@N\\\\^`CvC`AdAfBbCdCfDhC`DjCdDjC~CzDtEdBlBjBnBnBtBjBnBfD~C|CtCzClChCzB|CfC~CbC`DdCtDlCdErCv@f@t@f@d@VdJfF`F`CdEnBvB~@|At@nAj@lAh@rGjChGxB~Af@|E~AbLlElEtA`UvG`JjCpDbAnDjA~ExA|EzA~FjB|Bz@zBr@pFnBxB|@tB~@jOpFn@VrOrHhKzFvIxEz@l@@@zPrJ~JzFrPxJRJfCzAtBdApKvFr@\\\\vKdFr\\\\bLfG`BbZbG|Fx@~IdAtPtApHZzAD|@Bx@@zBDfCDdC?dD?jEE`DGbDIpAEnAGdI[dXcA|Mi@vJY~WG~FHdKTdK`@|R|AbDZrGt@hGx@NB|B\\\\tCd@nGfApB^dDr@|AZvBl@hG|Ah@LpHnBxQrFdj@hPxH|B~SvFTF@?xJrBvKrBjNrB~NbBnCZhG`@|Oz@xMZlEHjHB`IGtDEvKYtk@oBlk@oBxIOhMGbA@nPPhGR~Id@lLx@vJ`ApGt@~HfAhQ~C~EfA~HfBpHpBtVjHbj@fPxZ`J@?bFxAzBp@lFrAtRtDrK|AhANxIz@vIt@rDP~D@~A@rCFzCBxFTjEJx@?xBCpKWbFWjFWzIy@zIcA~Fy@nKkBdH{AdEaAlLaDbAWv`@oLhHsB`I{BzWwHnTmGlA_@xQyExGwAtGsArJcBvNuB|Fm@jGm@jJq@rMs@nIYpk@oBvOi@xZaArk@oBvDMfDMzDObCK~AIbEU`CO~CU`DWbD[rDc@lD_@bDc@|Dk@dBW|AWzCi@~Ck@`Cc@~Bg@zCq@`EaA~Bk@`Cm@vF_BzC}@zCaA~Bu@xBu@|Ak@|DwAnGeCxB_A~Aq@|BaArB}@xBaAxFgCjHaDtDaBtDcBhJcEvAo@|Ao@pEqBlIsD~QiIzAq@rB}@rFcCnHeDzPsH~As@xAo@tAm@~@a@tEuB`N_G|Ao@|Ao@zCgAvBu@|Ag@zBs@zAe@`D}@vFwA`Ci@nCm@jGiA`AOxAU`C]~AS`BS|C]dAI`AK~BQ`F[`CKtBInCGbCE|CCdCA`B@~A@bDD~CFbEPbBH|AHbCP`CP~BT`CT|C`@bC\\\\bBVxAV~AV`BZ~AX`B\\\\xD|@zD`A`Cn@xBn@`AX|Af@zCdAxBv@zBx@tElB|Ap@xB`ArDfBtCxArDpBrDtBx@d@t@d@zA~@lDzBz@j@nBtAvAdAtAbArAbAtAfA|FpExAhApAbAtAbAdDdCpCjB\\\\VpChB|@h@r@d@|@h@fEdCzAz@pAr@~@f@vAt@z@b@tAn@zAr@|@`@|@^tAl@|Al@xAj@zAl@|Al@zAh@tC`A~Af@zAb@PFbAZd@LxCx@|Cv@|Bf@~A\\\\|AX~AZ|AV`AP|AT~AV~AT|C\\\\`BP~AN|BRbEZ`BJ|CN`BHzADdDF~AB`B@|B?bA?|A?`BC`DEbCE~BI|AG`AG`CM~BOh@EDAlAI~AQ`BQ`BS`AM|B[|@O`AO|AU~@O|Ck@~Co@|A]|Bg@xBk@~Ac@~Ac@zBo@xDoAxBu@zBw@vCiAzAo@zAm@|@_@z@_@zAo@z@_@xAo@zAo@`Ac@tAm@vCoArEoBtDcBtD_BvCoAvCoAxCoAvB}@bBs@bC}@|CiA`DgAzE{A~Bq@`Cq@~Bm@~Bk@|A_@~A_@`Cg@|Cm@`Cc@`BWbBY`BU`BWbBSbCY`CYvBSj@G`CS`DS`EUdBIbCK~BIbCEdEG~BAdBAhC@bFDdEJbEJdFRfEN~BFdBFbCJbCH`CHbBF~AFdAB~@D`AD`BF`ADbAB|AFdADfADbBFl@Bt@DbAB~AD`FN`DLfBDxAFbAB~ADbADx@Bd@B`ADbBF~@BbAD~ERbELbBF~AD`BF`@@lAD|On@`EPdDJ~DLbDH|BFdAB~@@fA@xA@fA@~B?bBAbB?~BE`BCbGQdCI`DQ~AK~AKbE]bBO`BObD_@|BYbC]`C_@~B_@~Ck@`Dm@bCg@|Cs@|DaA|Aa@~@WbBg@zAc@|@Y|@Y`A[bA[~@[tJgDxEcBzCeAzDsAtE}AzBs@|Ae@zDgAzDaA`Bc@|A]zA[dASvBe@`Eu@~Dq@~B]~AU`Ee@~BW~AQ~BSbE[~CSdCM~DQ~BG`AC~BGbECjUSbJIbEE`EC`DC~AA~@AdEE~DCdII`DAbBC`BA|@AbAA~AC`AC~BE`@A|@C`AC`BGbBI|EUdBK|BObEY|AM`BO~Da@~AQ`AK|BYpG}@zFy@nEy@~E_AxEaAvEcA`FkAxLqCbB_@bHgBtFuA`@K|G{A~GyAxFiA\\\\G`BW|B_@`Ca@lLyAtDc@~ASzPqAd@Eh@Cj@EhRu@zAKvBCvTCnOZnk@`CxAHnOh@`KN|JDl@AdIEH?r@?~J]`WaBvRwBfCa@tDk@zGoArGsArOwDlTqFbM{ChFwAhe@oLrI}Bt_@sJvHoBn`@{JfO}DfZuH`UuGlAa@nAa@dBm@|RqHpO_HxDoBdTiLbGsDtBsA|B{AnBsArBwApA}@|@o@nA_ArAaApAaAv@m@rB}AhByApAeAdDoChB_BvAoAzCoCnAkAlAiAjBgBfBgBh@g@b@e@lAmAlAoAfBkBr@w@t@w@zBeC|BiCzBkCdBsBhAsAhAwAhAuAfAyAxBsChAwAbAuArCuDvBuCnCqDxBwChGiItKwNpEaGvBuC~AuB|AwBxC{DnBgCnBkCdHkJvCyDdBaCh@s@|EmGpBgCdByBtAoBhAcB^m@`BiCxCwE~AwCtAwClAeCzBmFpBwFhByFhAaEr@yCLm@v@gDTgAhA_GF]d@oCl@aEh@cEj@yFZuCP_CTuDLyCJaDJ_DFmE@qEFcI@{E@aC?oB?qB@qB?kB@cE?iE?{C?mA@aA?K?O?eB?iA@oB@_C?qB@kB?aE@wD?eC@mC?cC@_C?cC@aC@aG?eC@{A@_BB{A@kBBkAByB@y@@g@BqADqADqADaADmADwABu@Dw@JqBDaAHkAHwADo@Di@LcBLeBPmBR}BPkBRkBj@eFl@qEVoBHo@ToAV{ANu@ZgBXeB\\\\iBHa@Ha@ZsAd@{BNw@XkAZsAH]r@sCp@sCr@qCZgAXcAv@kCX_ARm@\\\\eAv@eCh@_Bb@oAp@kBFSx@mB~@yB~@sB~@uB~@sB`AsB`AuBdAyB~AiDtBmElEeJlEiJlEiJx@cBrBmEdCiFxE}J|EcKrEyJb@}@pAqClAkCnAqC`BsDpBwEjAqCb@gAv@mBN_@t@gBv@wBv@qBd@qAvAwDlAeDjAiDlAiDx@eCv@aCt@aCt@_Cp@yBp@{Bt@gCt@mCz@}Cx@aDz@eD`@_B~@{Dv@cD`AmE|AgH~@oEn@iDpA{GN}@TiAxAwIj@iD~@aG|@_GdB{Ll@mE^iCd@eD\\\\aCf@mD\\\\cC\\\\gCp@wEf@gDd@iDf@mDd@iDh@sDp@{En@sE\\\\eC\\\\cCHk@RuAn@sEp@{Ez@_Gp@yEn@yEp@wEf@sDlAqIlAqIxCeT`BkLbBqLdEaZZ}BPkA~Em]x@cGp@{Ep@yEx@{Ff@sDz@aGz@iGp@uE`@oC?A?AXqBn@uEf@kDh@sDp@wEx@aGp@yEz@aGp@yEp@yEz@cGr@wE\\\\cCf@cDvB}MnBuKt@_EFa@lCeNl@wC~@mEpA}FjBkIfAqEfAoEfAiEbCgJn@}Bl@yB|@_DlAcE`AeD~@yCnAcEdDeKZ}@?AlAkDvAcEdAyCjBcF|BeGfE}KnB_FrB{EpDkI`D_HvD_IfAqB`IwOv@_BxAiCl@gAbAiBh@_AV_@p@mApAyBbAiBbA_BvA_C|AaCxDeG|A}BfA_BxAuBfA}AtBuCfDuEjA_BlA{AlA_BtAcBbBuBnA{AbAoAhAsAbBmBjEcF`BiBt@s@?AjAoAvDaEbBaBvAwA`C_CnAoAlAiAbC{BvCoChC_ChCwB~AwApAeApB}AfCmBdBuAfBuAtAcA|@o@|DqClCkBtA}@fBmAbEkCv@c@vA{@tA{@`FuCfCyApBiArBgApBaAzAw@rB}@xAw@hAi@b@UtBaAnF}BlCiApCiAlCkAxCeAzBw@|Ak@pBs@vAe@|Ag@|CaAbDaAtHwB~Cw@`Ds@rD{@r@Oj@MbAWpDw@nDq@nDq@zEw@fK}AhJiAxJiAdOuAbGm@`Gg@nBSdSsBjIw@hIy@zFk@fQeB~Gi@x@Af@C@?bG[xCQ`Gi@hHk@nRgBzJu@lI}@~C]HAxKkAzDYxASbK}AvDm@bDq@pKkBzMkCvLsChGeB~C}@|KaD|Bq@|E{ArG{BjAc@tI_CdDuAzAs@|Ao@v@]~EuBtHiDxGqC|@c@rC{AxJkFrGwDtBuAjCgBvDaCtEkCrDcClEyCnE_DrB}AlEaDnIwGnJ_IzJsIbC{B~@{@~@}@jBeBt@s@r@q@fBgB~GaHDG~FaHNQ@AfC{ClKyLnEoFrMiPX]`JaMPSnToZnJwMvTyZtSeYrKkOf`@ki@xNcSt@cAxAwBdDsE~BcDlCsDfCmDhCmDT]LQ~AyB`IwKNUPSdE{F|NoRfFkGxEwF~EqF`FoF|BgCbDeDzB{BpFqFxFmFzFkF|FcFfCyBhCuBjDqCp@g@vAiAhCqBbFwDhDcCpBuAnCkBlCiBfD{BrCgB~CoB`DoBpDuBlDqBpC}AtBiAtC}AfDcB|C}AnDcBrEyBzDiBxG_D`EmBnB_AdAg@lB}@vDgBjJmEtEyBfEoBxDkBpEsBhK_FlFgCxHoD~GaDjI{DjFgCzAs@rDcBtDeBzCyAvHuDnF_C~E_C`D{AfEsBfEqB`EqBzJkFrDoBrBmAZS|CgB`HiErGkEdAs@jAw@r@i@t@g@vB_B~FkEzFsE`EeDvF}Ej@e@nBeBxCsCxCsCxCsCvByBjCkCvEcF`EoEvDkEpEmFbEeFbDeEpBmCpBkC~AwB|AwB|A{B|A}BrCcElCgEjFmIdCgEfByClBeDpBmDbB{Cv@qAl@kAtCuFjBsDjAaCrDuHzEoKvEkK~EsKtEgKbDkHdDeHbEaJ`CkFbHqOfHuOnHiPxEiKvEgK|EoK|EmKfEqJzEiK~D}IfDoH`E_JNY?ABEJSPa@FOVk@rBmElBeEnBgEfBwDxBoE|BmEnBmDpAyBzAiC~AiCzAyBpAkBbCeD~BwC|BqC`CmCnEsE|DsDvEaEtAcAvAgAlCmBlD}BlDwBlC{AxBiAxCwAdCeAtD}AtDqAfBi@lBi@nCw@dCm@|Co@`F{@|B[~B[zC[zAQdAMx@G~@G|@E`AE~AGbBC~AC`CA`D@`BDbA@~AF`AD~@BbAF`ADzAN~ANbBN|BXbBTzAX~B`@~AZ~A\\\\zA^bB`@zBn@zAf@|@ZtBv@|An@xAn@|@`@|@b@x@b@\\\\P`@Tx@b@z@d@tAt@vAv@z@b@z@d@vAt@vBhApBfAtBhAz@b@z@d@rAr@|@f@x@b@|@d@xAv@`B~@PJXPr@f@x@h@v@p@ZVZZXZXXVX\\\\^TXX\\\\V^V\\\\Xb@Rd@\\\\n@Xd@f@bAl@tAl@lAb@hA`@fA^lAZfABHH\\\\Jd@XhAr@tC^~A\\\\xAv@nDn@lCp@rCp@rC~@`EdB~HjBdIb@nBr@vC|@fErA|FbA`EbAzD|@jDv@lCr@|Bj@hBbAxCp@pBbArCv@pBfApC|AtDz@lBz@hBz@hBf@dAdBfDrAfCjBbDbAbBj@~@l@~@`A~A|A|Bn@|@fAzA~AvBp@z@hAvAjAtA|BlClApAnApAlApAnAlAfC|BnAfAv@r@h@`@fBxATNnBzArA~@x@j@rBrAtA|@v@f@z@f@vAv@xAv@xAt@vAr@xAn@zAp@x@^|@\\\\|An@xAh@|Ah@~@Zz@VzAd@|A`@|@T~@T`AT~Bh@xAX`AP`C`@~@L~@L|AR~@H~@J~AL`AH~AJ|@F`AD~AFbBD`CF`B@~B?`BA`BC~ACbBI`BI~AI|BM~AKfCM~BM`CO`CM`BK|BO|BM|@G\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 46.0573024,\n" +
            "                        \"lng\" : 19.7580656\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.4 km\",\n" +
            "                        \"value\" : 352\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 16\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 44.8390145,\n" +
            "                        \"lng\" : 20.2500451\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Take the \\u003cb\\u003eE70\\u003c/b\\u003e/\\u003cb\\u003eСремска Митровица\\u003c/b\\u003e/\\u003cb\\u003eSremska Mitrovica\\u003c/b\\u003e exit toward \\u003cb\\u003eZagreb\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"ramp-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"sfupG{`rzBdAD|@Gp@C`ACz@@v@Br@A`@Ah@Ch@Ct@El@G\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 44.8421754,\n" +
            "                        \"lng\" : 20.2498962\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.4 km\",\n" +
            "                        \"value\" : 379\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 20\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 44.8356175,\n" +
            "                        \"lng\" : 20.2504434\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Keep \\u003cb\\u003eleft\\u003c/b\\u003e at the fork, follow signs for \\u003cb\\u003eНови Београд\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"fork-left\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"yrtpGyarzBvAKv@Ez@G~@En@G|AEhAGd@Eh@CpBM\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 44.8390145,\n" +
            "                        \"lng\" : 20.2500451\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.5 km\",\n" +
            "                        \"value\" : 526\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 38\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 44.8355981,\n" +
            "                        \"lng\" : 20.2521176\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Keep \\u003cb\\u003eright\\u003c/b\\u003e at the fork, follow signs for \\u003cb\\u003eBeograd\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"fork-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"s}spGgdrzBL@X?V@RFNFLHPRNVJ^DX@R?XAXI\\\\EPIPKLILMHMFMBMBM?QCKCKGOKIKGMIQEMEOCYA]?u@L_BDe@Dg@@G?GDm@XsE\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 44.8356175,\n" +
            "                        \"lng\" : 20.2504434\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"16.6 km\",\n" +
            "                        \"value\" : 16565\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"12 mins\",\n" +
            "                        \"value\" : 703\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 44.79865849999999,\n" +
            "                        \"lng\" : 20.4485419\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Keep \\u003cb\\u003eleft\\u003c/b\\u003e at the fork and merge onto \\u003cb\\u003eA3\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"fork-left\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"o}spGwnrzB~@aN@a@Dk@De@Bc@Bk@BaA@oABgA@s@FoAZoIRmEf@aLXgHRqEh@kNxA_n@NyJ@{A@E?cALuKF}HH{T@mE?{J?qEAsDA{JAaCo@en@mAkl@GcDMeKEmCCgAIcLIkMEmGCgGAgDCcJAgU@gG@_GDuH@sDDqHFcIFiGHyGHoGFyEJoGZ{OP}IR{HRyHBcBBcBh@_QBo@T{Gp@oOxBmc@x@oPDkA@I@Y@IHoAl@gKDe@dAmOJcBj@cIBU~AgSz@{JlAyLbA{Hh@gD\\\\{B|AgIr@qDxAqG|@oDv@oCz@mDhB_G~A{ERk@BG|@{BTo@?ALY?ANc@Nc@FQhCoH|ByGn@kBjBkF`@eAFMHWHU@ABKBIVs@ZaAlD{JtAyDtA{DZ{@J]HSL_@rAcEL]?ADMp@mBtEoMDMfBsEJYL_@xC}HrCkIDM`AkCHURi@x@yBfBiFdBeFVu@dAqCjA}CZy@Zy@l@iB~@qCX{@lD}JpBwF?ADIz@cC?ArAwDxAaEr@oBpC}HhDyJBM@?FS@A@EFQJ]tAsDzE{MvBcGxA}D|CkI`@cAh@sAz@sBhAkCbBcE`@aAjGiOrD{IbBeEhAoC~@wBlAyCt@iB|@uBTk@\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 44.8355981,\n" +
            "                        \"lng\" : 20.2521176\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.1 km\",\n" +
            "                        \"value\" : 110\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 8\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 44.7979353,\n" +
            "                        \"lng\" : 20.4494673\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Take the exit toward \\u003cb\\u003eBulevar vojvode Putnika\\u003c/b\\u003e\",\n" +
            "                     \"maneuver\" : \"ramp-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"svlpGkzx{Bx@sAVi@PWPONKHG\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 44.79865849999999,\n" +
            "                        \"lng\" : 20.4485419\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"1.1 km\",\n" +
            "                        \"value\" : 1066\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"2 mins\",\n" +
            "                        \"value\" : 98\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 44.789251,\n" +
            "                        \"lng\" : 20.444236\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"Keep \\u003cb\\u003eright\\u003c/b\\u003e at the fork and merge onto \\u003cb\\u003eBulevar vojvode Putnika\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Colonial Sun Belgrade (on the right in 400&nbsp;m)\\u003c/div\\u003e\",\n" +
            "                     \"maneuver\" : \"fork-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"crlpGe`y{BTGPCNAR@RBPF`AZh@Rv@Zl@NbARfATf@Pn@XdA`@TJNF^NPHRJ~@^^@HBbA^l@\\\\JF`@ZlA`ApIzGx@l@vBfBrCjC\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 44.7979353,\n" +
            "                        \"lng\" : 20.4494673\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  },\n" +
            "                  {\n" +
            "                     \"distance\" : {\n" +
            "                        \"text\" : \"0.6 km\",\n" +
            "                        \"value\" : 563\n" +
            "                     },\n" +
            "                     \"duration\" : {\n" +
            "                        \"text\" : \"1 min\",\n" +
            "                        \"value\" : 58\n" +
            "                     },\n" +
            "                     \"end_location\" : {\n" +
            "                        \"lat\" : 44.78642989999999,\n" +
            "                        \"lng\" : 20.4486815\n" +
            "                     },\n" +
            "                     \"html_instructions\" : \"At the roundabout, take the \\u003cb\\u003e4th\\u003c/b\\u003e exit onto \\u003cb\\u003eUžička\\u003c/b\\u003e\\u003cdiv style=\\\"font-size:0.9em\\\"\\u003ePass by Topčiderska zvezda (on the right)\\u003c/div\\u003e\",\n" +
            "                     \"maneuver\" : \"roundabout-right\",\n" +
            "                     \"polyline\" : {\n" +
            "                        \"points\" : \"y{jpGo_x{BADAB?BAB?D?B?D?B@B?D@B?B@B@BBB@B@@B@@BB@@?B@B?@?B?D?BABA@ABA@C@A@C@CBE@E?C@C?E?C?C?E?CAC?EACACACACACAACCCA^kB~@eEPq@Rs@Re@Re@Tc@RQ\\\\YVU^[DGBCNSl@u@`@i@`@g@dAsA@A\"\n" +
            "                     },\n" +
            "                     \"start_location\" : {\n" +
            "                        \"lat\" : 44.789251,\n" +
            "                        \"lng\" : 20.444236\n" +
            "                     },\n" +
            "                     \"travel_mode\" : \"DRIVING\"\n" +
            "                  }\n" +
            "               ],\n" +
            "               \"traffic_speed_entry\" : [],\n" +
            "               \"via_waypoint\" : []\n" +
            "            }\n" +
            "         ],\n" +
            "         \"overview_polyline\" : {\n" +
            "            \"points\" : \"{ckxGkz_wBoBgDsBt@sBeO`i@eThFmJxMwYvHgOrHsJxP_QrJcZtCwHpA_LdCwElNeLfb@og@vVo[xAuHtBccAtE}U~ZyqAx\\\\qzA`IaXrE_Ql@OnDfBzBc@vAM`An@pLhOj_@`c@`g@~a@xIzFd[xOzn@fVpgAj\\\\dR|Gde@|RbhAzn@bUvLji@hSjb@dJ|Q~BfZpBnIPfb@_@`}@cD~_@@jWv@~h@hFrPnClt@lRtiAf\\\\~f@zIvl@|Etg@`@pjB_Gvk@Cnk@tDzj@dJf|Bzo@`i@vJd`@dChSb@pWOfc@mDzb@cI`rB_l@ve@eLtj@uH`pAoF|}AiFne@gCfe@iGxf@eLve@uP|i@cVtnBk{@ff@_Rte@{Jj]uCr_@Sl`@|BbZzEf`@~KhPzGpThLrUrO`^`XtUpNvQtI~WlJnVbG|WxDd_@rBhZI~SiA`UwCr[eHx\\\\uLxTqJdf@uS`d@iNf[aGr[cDza@gAjk@rAzj@pB`wB`Hn]Yt_@qCfb@mHxUwG|j@oRn\\\\gIbc@_Gnd@gBl_BsAfW{@h]sCzYkErZqGp{@{R~JaB~f@cFdVeAzk@FzjAdEpVA~d@_Cpe@uGvtCss@vuA_^d]gKnc@qQ~YyOfRwLdTuO|YeWxQ_RnViYdZq`@dpAccB`HkK|KmS`La\\\\nCwLhHci@nAqq@Jwt@TacA|Ake@rBgVtEa\\\\dNkl@jGiRbOi\\\\tbAkvB|]_`AvLqb@zNkq@~Ua_B~|@onGdTi{AlP{`AfSc{@`Ren@xTqm@vPg`@xRi`@tMmUzVy_@nW}\\\\`TeVn\\\\a\\\\l[mWx[mTj]sR`]iOf]yLrb@wKlVsEpr@gItuAaNli@sDrcAuJpb@iH|h@cMpt@yUpo@kZtn@aa@`_@wY~RiQrZq[vp@qy@thCwnDjZab@|j@eu@dp@cr@je@u`@xf@{\\\\df@_XdgBcz@jrBcaAhd@{Xbe@{^ze@{e@rc@oj@d`@{m@|Xii@jlBkeEjk@onAlXuc@nQkSbQaOvR_MnQeIzO}EbSuDvTsBlY?dT`BzX|F|RdIzMhHnc@hVnFnFvE~HjFrNpHp[lPht@rMjd@|Nt]bRt[jRzUnSfRpQxLpSxJhStG~S~D~QzAjYL`r@iDf\\\\mAdHCz@tAD`ByArBgBq@GcHnBiZ`@sMpBgf@fDwoAd@_cAEc[sC{lBm@gbA^ypA|BcoApHahBnCec@zCea@vFyg@hGg[zGmVxEyMfQkg@b[k|@`|@kdC|g@}rAxRoe@vDeEjSbGlG`CbWdTP`Ap@MCiAnCeM|CsDhEqF\"\n" +
            "         },\n" +
            "         \"summary\" : \"А1\",\n" +
            "         \"warnings\" : [],\n" +
            "         \"waypoint_order\" : []\n" +
            "      }\n" +
            "   ],\n" +
            "   \"status\" : \"OK\"\n" +
            "}\n";

    @Test
    public void testJsonParser() throws Exception {
        Route route = GsonParser.getInstance().parseRoute(json);
        assertEquals("OK", route.getStatus());
        assertTrue(route.getStartLocation().size() > 0);
        assertTrue(route.getEndLocation().size() > 0);
        assertNotNull(route.getPoint());
        System.out.print(route.getPoint());
    }

    @Test
    public void testDecodePolyline() throws Exception {
        Route route = GsonParser.getInstance().parseRoute(json);
        route.setPoints(PolyUtil.decode(route.getPoint()));
        for (LatLng latLng : route.getPoints()) {
            System.out.println(latLng.latitude + " " + latLng.longitude);
        }
    }
}